/*
 * Copyright (c) 2022, Peter Abeles. All Rights Reserved.
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

package boofcv.alg.distort.division;

import javax.annotation.Generated;
import boofcv.struct.distort.Point2Transform2_F32;
import georegression.struct.point.Point2D_F32;

/**
 * Converts the undistorted normalized coordinate into normalized pixel coordinates.
 *
 * @author Peter Abeles
 */
@SuppressWarnings({"NullAway.Init"})
@Generated("boofcv.alg.distort.division.AddDivisionNtoN_F64")
public class AddDivisionNtoN_F32 implements Point2Transform2_F32 {

	// lens distortion
	public float radial;

	/** Convergence tolerance */
	public float tol = (float)1e-8;
	/** Maximum number of iterations */
	public int maxIterations = 500;

	public AddDivisionNtoN_F32() {}

	public AddDivisionNtoN_F32 setRadial( float radial ) {
		this.radial = radial;
		return this;
	}

	/**
	 * Adds radial distortion
	 *
	 * @param x Undistorted x-coordinate pixel image coordinates
	 * @param y Undistorted y-coordinate pixel image coordinates
	 * @param out Distorted normalized image coordinate.
	 */
	@Override
	public void compute( float x, float y, Point2D_F32 out ) {
		float origX = x;
		float origY = y;

		float prevR2 = 0;

		for (int iter = 0; iter < maxIterations; iter++) {
			float r2 = x*x + y*y;

			x = origX*(1.0f + radial*r2);
			y = origY*(1.0f + radial*r2);

			if (Math.abs(r2 - prevR2) <= tol) {
				break;
			} else {
				prevR2 = r2;
			}
		}
		out.setTo(x, y);
	}

	@Override
	public AddDivisionNtoN_F32 copyConcurrent() {
		var ret = new AddDivisionNtoN_F32();
		ret.radial = radial;
		return ret;
	}
}
