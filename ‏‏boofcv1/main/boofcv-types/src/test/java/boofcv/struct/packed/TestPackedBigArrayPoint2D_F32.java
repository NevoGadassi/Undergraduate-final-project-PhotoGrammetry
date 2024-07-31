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

package boofcv.struct.packed;

import javax.annotation.Generated;
import boofcv.struct.PackedArray;
import georegression.struct.point.Point2D_F32;
import org.ddogleg.struct.BigDogGrowth;
import org.ejml.UtilEjml;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@Generated("boofcv.struct.packed.TestPackedBigArrayPoint2D_F64")
public class TestPackedBigArrayPoint2D_F32 extends GenericPackedArrayChecks<Point2D_F32> {

	@Override protected PackedArray<Point2D_F32> createAlg() {
		return new PackedBigArrayPoint2D_F32(3, 11, BigDogGrowth.GROW_FIRST);
	}

	@Override protected Point2D_F32 createRandomPoint() {
		var point = new Point2D_F32();
		point.x = (float) rand.nextGaussian();
		point.y = (float) rand.nextGaussian();
		return point;
	}

	@Override protected void checkEquals( Point2D_F32 a, Point2D_F32 b ) {
		assertEquals(0.0f, a.distance(b), UtilEjml.TEST_F32);
	}

	@Override protected void checkNotEquals( Point2D_F32 a, Point2D_F32 b ) {
		assertNotEquals(0.0f, a.distance(b), UtilEjml.TEST_F32);
	}
}
