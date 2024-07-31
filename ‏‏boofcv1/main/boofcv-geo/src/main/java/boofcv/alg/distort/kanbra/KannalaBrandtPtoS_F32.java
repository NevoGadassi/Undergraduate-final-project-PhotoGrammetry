/*
 * Copyright (c) 2021, Peter Abeles. All Rights Reserved.
 *
 * This file is part of BoofCV (http://boofcv.org).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package boofcv.alg.distort.kanbra;

import javax.annotation.Generated;
import boofcv.alg.distort.pinhole.PinholePtoN_F32;
import boofcv.misc.BoofMiscOps;
import boofcv.misc.ConfigConverge;
import boofcv.struct.calib.CameraKannalaBrandt;
import boofcv.struct.distort.Point2Transform3_F32;
import georegression.struct.point.Point2D_F32;
import georegression.struct.point.Point3D_F32;
import org.ddogleg.solver.Polynomial;
import org.ddogleg.solver.PolynomialOps;
import org.ddogleg.solver.PolynomialRoots;
import org.ddogleg.solver.RootFinderType;
import org.ddogleg.struct.VerbosePrint;
import org.ejml.UtilEjml;
import org.ejml.data.Complex_F64;
import org.ejml.data.FMatrix2x2;
import org.ejml.dense.fixed.CommonOps_FDF2;
import org.ejml.dense.fixed.MatrixFeatures_FDF2;
import org.jetbrains.annotations.Nullable;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static boofcv.alg.distort.kanbra.KannalaBrandtUtils_F64.*;

//CUSTOM ignore Complex_F64
//CUSTOM ignore KannalaBrandtUtils_F64

/**
 * Backwards project from a distorted 2D pixel to 3D unit sphere coordinate using the {@link CameraKannalaBrandt} model.
 *
 * Newton's method is used to invert the distorted coordinates [dx, dy] (Eq. 10). This is different from the original
 * paper which uses a taylor series to approximate the function and no iteration.
 *
 * @author Peter Abeles
 */
@SuppressWarnings({"NullAway.Init"})
@Generated("boofcv.alg.distort.kanbra.KannalaBrandtPtoS_F64")
public class KannalaBrandtPtoS_F32 implements Point2Transform3_F32, VerbosePrint {

	/** A complex number is considered real if the imaginary component has a magnitude &le; this value */
	public float realNumberTol = UtilEjml.TEST_F32;

	/** Specifies convergence criteria */
	public final ConfigConverge converge = new ConfigConverge(1e-6, 1e-6, 20);

	// The camera model
	protected final CameraKannalaBrandt model;

	// Used to convert from pixels to normalized image coordinates using a pinhole model
	PinholePtoN_F32 pinholePtoN = new PinholePtoN_F32();

	// "normalized" image coordinate from pinhole model
	Point2D_F32 norm = new Point2D_F32();

	/** Used to solve for theta */
	public PolynomialRoots rootFinder;
	// Storage for the polynomial it will find the roots inside of
	private final Polynomial polynomial = new Polynomial(5);

	// Updated values using newton's method
	float updatedTheta, updatedphi;
	// Jacobian of distorted coordinates
	FMatrix2x2 jacobian = new FMatrix2x2();
	FMatrix2x2 jacobianInv = new FMatrix2x2();

	@Nullable PrintStream verbose;

	public KannalaBrandtPtoS_F32( CameraKannalaBrandt model ) {
		BoofMiscOps.checkTrue(model.radialTrig.length == 0 || model.radialTrig.length == 4);

		this.model = new CameraKannalaBrandt(model);
		pinholePtoN.setK(model);
		int numCoef = 1 + model.symmetric.length*2;
		rootFinder = PolynomialOps.createRootFinder(numCoef, RootFinderType.EVD);
		polynomial.resize(numCoef);
	}

	@Override
	public void compute( float x, float y, Point3D_F32 out ) {
		// Undo projection on to pixels and into normalized image coordinates
		pinholePtoN.compute(x, y, norm);
		// norm now is the observed distorted coordinates

		// Compute initial estimate of r, phi, and theta, norm=X_d (in paper)
		float r = norm.norm();
		float phi = (float)Math.atan2(norm.y, norm.x);
		float theta = computeTheta(r);

		if (model.isAsymmetricModel()) {
			newtonsMethodUpdateThetaphi(theta, phi, r);
			phi = updatedphi;
			theta = updatedTheta;
		}

		if (theta == Float.MAX_VALUE) {
			// It failed, so who give it a value that seems "reasonable" for failing and return
			out.setTo(0.0f, 0.0f, 0.0f);
			return;
		}

		// go from angles to sphere point vector
		float sintheta = (float)Math.sin(theta);
		out.x = sintheta*(float)Math.cos(phi);
		out.y = sintheta*(float)Math.sin(phi);
		out.z = (float)Math.cos(theta);
	}

	/**
	 * Compute theta by solving for the polynomial's roots. Go with the smallest real root
	 */
	float computeTheta( float r ) {
		// Pass in the polynomials coefficient
		// r = k1*theta + k2*theta**3 + k3*theta**5 ...
		Arrays.fill(polynomial.c, 0.0f);
		polynomial.c[0] = -r;
		for (int i = 0; i < model.symmetric.length; i++) {
			polynomial.c[i*2 + 1] = model.symmetric[i];
		}
		if (!rootFinder.process(polynomial)) {
			// failed. not sure what else to do here
			return Float.MAX_VALUE;
		}

		// Pick the smallest real value of theta
		float theta = Float.MAX_VALUE;
		List<Complex_F64> roots = rootFinder.getRoots();
		for (int i = 0; i < roots.size(); i++) {
			Complex_F64 root = roots.get(i);
			if (Math.abs(root.imaginary) <= realNumberTol && theta > root.real && root.real > -realNumberTol) {
				theta = (float)root.real;
			}
		}

		return (float)Math.max(0.0f, theta);
	}

	/**
	 * Invert the function using Newton's method. This is different from the original paper which approximated the
	 * original function using multiple taylor series expansions and no iteration. This function can only improve
	 * the estimate of theta and phi. If the answer ever gets worse it aborts.
	 */
	protected void newtonsMethodUpdateThetaphi( float theta, float phi, float r ) {
		// Use Newton's method to refine the estimate. Different from how it was done in the paper
		float previousError = Float.MAX_VALUE;

		// Update the estimates for all the parameters
		updatedTheta = theta;
		updatedphi = phi;
		float updatedR = r;

		// Iterate until it converges or hits the maximum number of iterations
		for (int iteration = 0; iteration < converge.maxIterations; iteration++) {
			float cosphi = (float)Math.cos(updatedphi);
			float sinphi = (float)Math.sin(updatedphi);

			// distortion terms. radial and tangential
			float disRad = (float)(polynomial(model.radial, updatedTheta)*polytrig(model.radialTrig, cosphi, sinphi));
			float disTan = (float)(polynomial(model.tangent, updatedTheta)*polytrig(model.tangentTrig, cosphi, sinphi));

			// put it all together to get distorted (normalized) coordinates
			float dx = (updatedR + disRad)*cosphi - disTan*sinphi;
			float dy = (updatedR + disRad)*sinphi + disTan*cosphi;

			// Compute the error between the true and estimated parameters
			float error = norm.distance(dx, dy);

			if (verbose != null)
				verbose.printf("[%3d] error=%.2e theta=%.4f phi=%.4f\n", iteration, error, updatedTheta, updatedphi);

			// If the error got worse, abort
			if (error > previousError) {
				if (verbose != null) verbose.println("converged: error > previousError");
				break;
			}

			// Check for convergence based on the error being very small
			if (Math.abs(error) <= converge.ftol) {
				if (verbose != null) verbose.println("converged: ftol");
				break;
			}

			// Check for convergence based on the improvement being too small
			if (Math.abs(error - previousError)/Math.max(error, previousError) <= converge.gtol) {
				if (verbose != null) verbose.println("converged: gtol");
				break;
			}
			previousError = error;

			// Save the new better estimates of (theta, phi)
			theta = updatedTheta;
			phi = updatedphi;

			// Compute the next update

			// inv(Jacobian)*[X_d - F(x)] = delta
			jacobianOfDistorted(theta, cosphi, sinphi, jacobian);
			if (!CommonOps_FDF2.invert(jacobian, jacobianInv) || MatrixFeatures_FDF2.hasUncountable(jacobianInv)) {
				if (verbose != null) verbose.println("Bad matrix inverse");
				break;
			}

			// Difference between the observed and predicted
			dx = norm.x - dx;
			dy = norm.y - dy;

			// Compute the delta for phi and theta
			float deltaTheta = jacobianInv.a11*dx + jacobianInv.a12*dy;
			float deltaphi = jacobianInv.a21*dx + jacobianInv.a22*dy;

			// Update the estimates for all the parameters
			updatedTheta = theta + deltaTheta;
			updatedphi = phi + deltaphi;
			updatedR = (float)polynomial(model.symmetric, updatedTheta);
		}
	}

	/**
	 * Computes the Jacobian of the distorted coordinates [dx, dy].
	 */
	protected void jacobianOfDistorted( float theta, float cosphi, float sinphi, FMatrix2x2 gradient ) {
		// Distorted Coordinates
		// Xd = [dx, dy] = r(theta)*ur(phi) + deltaR(theta,phi)*ur(phi) + deltaT(theta,phi)*ut(phi)

		// partials and forward functions
		float sym = (float)polynomial(model.symmetric, theta);
		float sym_dtheta = (float)polynomialDerivative(model.symmetric, theta);

		float deltaR = (float)(polynomial(model.radial, theta)*polytrig(model.radialTrig, cosphi, sinphi));
		float deltaR_dtheta = (float)(polynomialDerivative(model.radial, theta)*polytrig(model.radialTrig, cosphi, sinphi));
		float deltaR_dphi = (float)(polynomial(model.radial, theta)*polytrigDerivative(model.radialTrig, cosphi, sinphi));

		float deltaT = (float)(polynomial(model.tangent, theta)*polytrig(model.tangentTrig, cosphi, sinphi));
		float deltaT_dtheta = (float)(polynomialDerivative(model.tangent, theta)*polytrig(model.tangentTrig, cosphi, sinphi));
		float deltaT_dphi = (float)(polynomial(model.tangent, theta)*polytrigDerivative(model.tangentTrig, cosphi, sinphi));

		// dx/dtheta
		gradient.a11 = (sym_dtheta + deltaR_dtheta)*cosphi - deltaT_dtheta*sinphi;
		// dx/dphi
		gradient.a12 = -sym*sinphi + deltaR_dphi*cosphi - deltaR*sinphi - deltaT_dphi*sinphi - deltaT*cosphi;
		// dy/dtheta
		gradient.a21 = (sym_dtheta + deltaR_dtheta)*sinphi + deltaT_dtheta*cosphi;
		// dy/dphi
		gradient.a22 = sym*cosphi + deltaR_dphi*sinphi + deltaR*cosphi + deltaT_dphi*cosphi - deltaT*sinphi;
	}

	@Override
	public Point2Transform3_F32 copyConcurrent() {
		return new KannalaBrandtPtoS_F32(model);
	}

	@Override public void setVerbose( @Nullable PrintStream out, @Nullable Set<String> configuration ) {
		verbose = BoofMiscOps.addPrefix(this, out);
	}
}
