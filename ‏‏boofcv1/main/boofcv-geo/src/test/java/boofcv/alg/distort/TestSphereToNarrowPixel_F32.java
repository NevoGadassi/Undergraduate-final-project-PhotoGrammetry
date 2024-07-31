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

package boofcv.alg.distort;

import javax.annotation.Generated;
import boofcv.struct.distort.Point2Transform2_F32;
import boofcv.testing.BoofStandardJUnit;
import georegression.struct.point.Point2D_F32;
import org.ejml.UtilEjml;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@Generated("boofcv.alg.distort.TestSphereToNarrowPixel_F64")
public class TestSphereToNarrowPixel_F32 extends BoofStandardJUnit {
	@Test void basic() {
		var alg = new SphereToNarrowPixel_F32(new Dummy());

		var found = new Point2D_F32();
		alg.compute(1,2,3, found);

		assertEquals(1.0f/3.0f,found.x, UtilEjml.TEST_F32);
		assertEquals(2.0f/3.0f,found.y, UtilEjml.TEST_F32);
	}

	/** Special case where z=0. This is a singularity.  */
	@Test void zeroZ() {
		var alg = new SphereToNarrowPixel_F32(new Dummy());

		var found = new Point2D_F32();
		alg.compute(1,2,0, found);

		assertFalse(Float.isNaN(found.x));
		assertFalse(Float.isNaN(found.y));

		assertEquals(1.0f,found.x, UtilEjml.TEST_F32);
		assertEquals(2.0f,found.y, UtilEjml.TEST_F32);
	}

	private static class Dummy implements Point2Transform2_F32 {
		@Override
		public void compute(float x, float y, Point2D_F32 out) {
			out.setTo(x,y);
		}

		@Override
		public Point2Transform2_F32 copyConcurrent() {
			return null;
		}
	}
}
