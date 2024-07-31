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

package boofcv.struct.distort;

import javax.annotation.Generated;
import boofcv.testing.BoofStandardJUnit;
import georegression.struct.point.Point2D_F32;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Generated("boofcv.struct.distort.TestPointToPixelTransform_F64")
public class TestPointToPixelTransform_F32 extends BoofStandardJUnit {

	@Test
	void manual() {
		Dummy p = new Dummy();
		PointToPixelTransform_F32 alg = new PointToPixelTransform_F32(p);

		Point2D_F32 distorted = new Point2D_F32();
		alg.compute(1, 2, distorted);
		Point2D_F32 expected = new Point2D_F32();
		p.compute(1, 2, expected);

		assertEquals(expected.x, distorted.x, 1e-6);
		assertEquals(expected.y, distorted.y, 1e-6);
	}

	private static class Dummy implements Point2Transform2_F32 {

		@Override
		public void compute( float x, float y, Point2D_F32 out ) {
			out.x = x + 0.1f;
			out.y = y + 0.2f;
		}

		@Override
		public Point2Transform2_F32 copyConcurrent() {
			return null;
		}
	}
}
