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

package boofcv.alg.distort.pinhole;

import javax.annotation.Generated;
import boofcv.testing.BoofStandardJUnit;
import georegression.geometry.GeometryMath_F32;
import georegression.struct.point.Point2D_F32;
import org.ejml.data.FMatrixRMaj;
import org.ejml.dense.row.CommonOps_FDRM;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Generated("boofcv.alg.distort.pinhole.TestPixelTransformPinholeNorm_F64")
class TestPixelTransformPinholeNorm_F32 extends BoofStandardJUnit {
	float fx = 200;
	float fy = 300;
	float skew = 1.2f;
	float x_c = 400;
	float y_c = 450;

	/**
	 * Do the same calculation but using a different but equivalent equation
	 */
	@Test void basic() {
		var alg = new PixelTransformPinholeNorm_F32();
		alg.fset(fx, fy, skew, x_c, y_c);

		int pixX = 100;
		int pixY = 120;
		var in = new Point2D_F32(pixX, pixY);
		var out = new Point2D_F32();

		alg.compute(100, 120, out);

		Point2D_F32 expected = new Point2D_F32();
		FMatrixRMaj K_inv = new FMatrixRMaj(3, 3, true, fx, skew, x_c, 0, fy, y_c, 0, 0, 1);
		CommonOps_FDRM.invert(K_inv);

		GeometryMath_F32.mult(K_inv, in, expected);

		assertEquals(expected.x, out.x, 1e-5);
		assertEquals(expected.y, out.y, 1e-5);
	}
}
