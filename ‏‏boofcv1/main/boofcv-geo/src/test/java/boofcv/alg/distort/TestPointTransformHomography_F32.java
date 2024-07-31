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

package boofcv.alg.distort;

import javax.annotation.Generated;
import boofcv.testing.BoofStandardJUnit;
import georegression.struct.homography.Homography2D_F32;
import georegression.struct.point.Point2D_F32;
import georegression.transform.homography.HomographyPointOps_F32;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Generated("boofcv.alg.distort.TestPointTransformHomography_F64")
public class TestPointTransformHomography_F32 extends BoofStandardJUnit {
	/**
	 * Directly computes the output
	 */
	@Test void compareToDirect() {
		Point2D_F32 input = new Point2D_F32(50,60);
		Point2D_F32 output = new Point2D_F32();
		Point2D_F32 expected = new Point2D_F32();

		Homography2D_F32 H = new Homography2D_F32(1,2,3,4,5,6,7,8,9);

		HomographyPointOps_F32.transform(H, input, expected);
		PointTransformHomography_F32 alg = new PointTransformHomography_F32();
		alg.set(H);

		alg.compute(input.x,input.y,output);

		assertEquals(expected.x,output.x,1e-4);
		assertEquals(expected.y, output.y, 1e-4);
	}
}
