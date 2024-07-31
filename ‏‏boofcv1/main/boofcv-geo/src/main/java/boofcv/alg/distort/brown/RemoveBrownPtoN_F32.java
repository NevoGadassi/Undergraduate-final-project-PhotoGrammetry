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

package boofcv.alg.distort.brown;

import javax.annotation.Generated;
import boofcv.struct.calib.CameraPinholeBrown;
import boofcv.struct.distort.Point2Transform2_F32;
import georegression.misc.GrlConstants;
import georegression.struct.point.Point2D_F32;
import org.jetbrains.annotations.Nullable;

import static boofcv.alg.distort.brown.RemoveBrownNtoN_F32.removeRadial;

/**
 * Converts the observed distorted pixels into normalized image coordinates.
 *
 * @author Peter Abeles
 */
@SuppressWarnings({"NullAway.Init", "Duplicates"})
@Generated("boofcv.alg.distort.brown.RemoveBrownPtoN_F64")
public class RemoveBrownPtoN_F32 implements Point2Transform2_F32 {

	// principle point / image center
	public float cx, cy;
	// other intrinsic parameters
	public float fx, fy, skew;

	// distortion parameters
	public RadialTangential_F32 params;

	// inverse of camera calibration matrix
	// These are the upper triangular elements in a 3x3 matrix
	private float a11, a12, a13, a22, a23;

	private float tol = GrlConstants.FCONV_TOL_A;

	public RemoveBrownPtoN_F32() {}

	public RemoveBrownPtoN_F32( float tol ) {
		this.tol = tol;
	}

	public void setTolerance( float tol ) {
		this.tol = tol;
	}

	/**
	 * Specify camera calibration parameters
	 *
	 * @param fx Focal length x-axis in pixels
	 * @param fy Focal length y-axis in pixels
	 * @param skew skew in pixels
	 * @param cx camera center x-axis in pixels
	 * @param cy center center y-axis in pixels
	 */
	public RemoveBrownPtoN_F32 setK( /**/double fx, /**/double fy, /**/double skew, /**/double cx, /**/double cy ) {
		this.fx = (float)fx;
		this.fy = (float)fy;
		this.skew = (float)skew;
		this.cx = (float)cx;
		this.cy = (float)cy;

		// analytic solution to matrix inverse
		a11 = (float)(1.0f/fx);
		a12 = (float)(-skew/(fx*fy));
		a13 = (float)((skew*cy - cx*fy)/(fx*fy));
		a22 = (float)(1.0f/fy);
		a23 = (float)(-cy/fy);

		return this;
	}

	public RemoveBrownPtoN_F32 setDistortion( @Nullable /**/double[] radial, /**/double t1, /**/double t2 ) {
		if (params != null && radial != null && params.radial.length == radial.length) {
			System.arraycopy(radial, 0, params.radial, 0, radial.length);
			return this;
		}
		params = new RadialTangential_F32(radial, t1, t2);
		return this;
	}

	public RemoveBrownPtoN_F32 setDistortion( /**/double radial1,  /**/double radial2 ) {
		if (params != null && params.radial.length == 2) {
			params.radial[0] = (float)radial1;
			params.radial[1] = (float)radial2;
			return this;
		}
		params = new RadialTangential_F32(new /**/double[]{radial1, radial2}, 0.0f, 0.0f);
		return this;
	}

	/** Convenience function for assigning all parameters from a brown camera model */
	public RemoveBrownPtoN_F32 setParameters( CameraPinholeBrown p ) {
		return setK(p.fx, p.fy, p.skew, p.cx, p.cy).setDistortion(p.radial, p.t1, p.t2);
	}

	/**
	 * Removes radial distortion
	 *
	 * @param x Distorted x-coordinate pixel
	 * @param y Distorted y-coordinate pixel
	 * @param out Undistorted normalized coordinate.
	 */
	@Override public void compute( float x, float y, Point2D_F32 out ) {
		// initial estimate of undistorted point
		out.x = a11*x + a12*y + a13;
		out.y = a22*y + a23;

		removeRadial(out.x, out.y, params.radial, params.t1, params.t2, out, tol);
	}

	@Override public RemoveBrownPtoN_F32 copyConcurrent() {
		var ret = new RemoveBrownPtoN_F32(tol);
		ret.fx = fx; // don't use set since it recomputes it. inputs would be floats not float, so diff solution
		ret.fy = fy;
		ret.skew = skew;
		ret.cx = cx;
		ret.cy = cy;
		ret.a11 = a11;
		ret.a12 = a12;
		ret.a13 = a13;
		ret.a22 = a22;
		ret.a23 = a23;
		ret.params = new RadialTangential_F32(params);
		return ret;
	}

	public void reset() {
		cx = cy = skew = fx = fy = 0;
		a11 = a12 = a13 = a22 = a23 = 0;
	}
}
