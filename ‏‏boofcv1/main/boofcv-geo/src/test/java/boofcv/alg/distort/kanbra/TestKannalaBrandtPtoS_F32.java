/*
 * Copyright (c) 2023, Peter Abeles. All Rights Reserved.
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
import boofcv.misc.BoofMiscOps;
import boofcv.struct.calib.CameraKannalaBrandt;
import boofcv.testing.BoofStandardJUnit;
import georegression.struct.point.Point2D_F32;
import georegression.struct.point.Point3D_F32;
import org.ddogleg.optimization.DerivativeChecker;
import org.ddogleg.optimization.functions.FunctionNtoM;
import org.ddogleg.optimization.functions.FunctionNtoMxN;
import org.ejml.UtilEjml;
import org.ejml.data.FMatrix2x2;
import org.ejml.data.DMatrixRMaj;
import org.junit.jupiter.api.Test;

import static boofcv.alg.distort.kanbra.KannalaBrandtUtils_F64.polynomial;
import static boofcv.alg.distort.kanbra.KannalaBrandtUtils_F64.polytrig;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

//CUSTOM ignore KannalaBrandtUtils_F64
//CUSTOM ignore DMatrixRMaj

@Generated("boofcv.alg.distort.kanbra.TestKannalaBrandtPtoS_F64")
class TestKannalaBrandtPtoS_F32 extends BoofStandardJUnit {
	/**
	 * Given spherical coordinates, compute pixel coordinates and see if we can invert them correctly.
	 */
	@Test void simpleSanityCheck_SymmetricOnly() {
		CameraKannalaBrandt model = new CameraKannalaBrandt().fsetK(500, 550, 0.0f, 600, 650);
		model.fsetSymmetric(1.0f, 0.1f);

		var expected = new Point3D_F32(0.1f, -0.05f, 0.8f);
		var pixel = new Point2D_F32();
		var found = new Point3D_F32();

		new KannalaBrandtStoP_F32(model).compute(expected.x, expected.y, expected.z, pixel);
		new KannalaBrandtPtoS_F32(model).compute(pixel.x, pixel.y, found);

		// make sure both have them have a norm of 1
		expected.divideIP(expected.norm());
		found.divideIP(found.norm());

		// This should be very accurate. The inaccurate part isn't being called
		assertEquals(0.0f, expected.distance(found), UtilEjml.TEST_F32);
	}

	/**
	 * The entire motion model will be exercised here
	 */
	@Test void simpleSanityCheck_Everything() {
		CameraKannalaBrandt model = new CameraKannalaBrandt().fsetK(500, 550, 0.0f, 600, 650);
		model.fsetSymmetric(1.0f, 0.4f).fsetRadial(1.1f, 0.2f, -0.01f).fsetTangent(0.5f, -0.1f, 0.06f, 0.12f).
				fsetRadialTrig(0.01f, 0.02f, -0.03f, 0.04f).fsetTangentTrig(0.01f, 0.2f, 0.1f, 0.4f);

		var expected = new Point3D_F32(0.1f, -0.12f, 0.8f);
		var pixel = new Point2D_F32();
		var found = new Point3D_F32();

		new KannalaBrandtStoP_F32(model).compute(expected.x, expected.y, expected.z, pixel);
		new KannalaBrandtPtoS_F32(model).compute(pixel.x, pixel.y, found);

		// make sure both have them have a norm of 1
		expected.divideIP(expected.norm());
		found.divideIP(found.norm());

		// The paper says this will be noisy. Using Newton's method seems to be much more accurate
		assertEquals(0.0f, expected.distance(found), 1e-4);
	}

	/**
	 * Compare to numerical Jacobian
	 */
	@Test void jacobianOfDistorted() {
		CameraKannalaBrandt model = new CameraKannalaBrandt().fsetK(500, 550, 0.0f, 600, 650);
		model.fsetSymmetric(1.0f, 0.4f).fsetRadial(1.1f, 0.2f, -0.01f).fsetTangent(0.5f, -0.1f, 0.06f, 0.12f).
				fsetRadialTrig(0.01f, 0.03f, -0.03f, 0.04f).fsetTangentTrig(0.01f, 0.2f, 0.1f, 0.4f);

		FunctionNtoM function = new FunctionNtoM() {
			@Override public void process( /**/double[] input, /**/double[] output ) {
				float theta = (float) input[0];
				float psi = (float) input[1];

				float r = (float) polynomial(model.symmetric, theta);

				float cospsi = (float)Math.cos(psi);
				float sinpsi = (float)Math.sin(psi);

				// distortion terms. radial and tangential
				float dr = (float) (polynomial(model.radial, theta)*polytrig(model.radialTrig, cospsi, sinpsi));
				float dt = (float) (polynomial(model.tangent, theta)*polytrig(model.tangentTrig, cospsi, sinpsi));

				// put it all together to get normalized image coordinates
				output[0] = (r + dr)*cospsi - dt*sinpsi;
				output[1] = (r + dr)*sinpsi + dt*cospsi;
			}

			@Override public int getNumOfInputsN() {return 2;}

			@Override public int getNumOfOutputsM() {return 2;}
		};

		var kb = new KannalaBrandtPtoS_F32(model);
		FunctionNtoMxN<DMatrixRMaj> jacobian = new FunctionNtoMxN<>() {
			final FMatrix2x2 a = new FMatrix2x2();

			@Override public int getNumOfInputsN() {return 2;}

			@Override public int getNumOfOutputsM() {return 2;}

			@Override public DMatrixRMaj declareMatrixMxN() {return new DMatrixRMaj(2,2);}

			@Override public void process( /**/double[] input, DMatrixRMaj output ) {
				float theta = (float) input[0];
				float psi = (float) input[1];

				float cospsi = (float)Math.cos(psi);
				float sinpsi = (float)Math.sin(psi);

				kb.jacobianOfDistorted(theta, cospsi, sinpsi, a);
				BoofMiscOps.convertMatrix(a, output);
			}
		};

//		DerivativeChecker.jacobianPrint(function, jacobian, new float[]{0.2f, -0.4f}, 1e-4);
		assertTrue(DerivativeChecker.jacobian(function, jacobian, new /**/double[]{0.2, -0.4f},
				UtilEjml.TEST_F32_SQ, (float)Math.sqrt(UtilEjml.F_EPS)));
	}
}
