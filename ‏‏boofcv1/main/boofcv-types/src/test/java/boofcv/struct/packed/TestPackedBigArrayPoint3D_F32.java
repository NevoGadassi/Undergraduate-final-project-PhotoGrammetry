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
import georegression.struct.point.Point3D_F32;
import org.ddogleg.struct.BigDogGrowth;
import org.ejml.UtilEjml;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@Generated("boofcv.struct.packed.TestPackedBigArrayPoint3D_F64")
public class TestPackedBigArrayPoint3D_F32 extends GenericPackedArrayChecks<Point3D_F32> {

	@Override protected PackedArray<Point3D_F32> createAlg() {
		return new PackedBigArrayPoint3D_F32(3, 11, BigDogGrowth.GROW_FIRST);
	}

	@Override protected Point3D_F32 createRandomPoint() {
		var point = new Point3D_F32();
		point.x = (float)rand.nextGaussian();
		point.y = (float)rand.nextGaussian();
		point.z = (float)rand.nextGaussian();
		return point;
	}

	@Override protected void checkEquals( Point3D_F32 a, Point3D_F32 b ) {
		assertEquals(0.0f, a.distance(b), UtilEjml.TEST_F32);
	}

	@Override protected void checkNotEquals( Point3D_F32 a, Point3D_F32 b ) {
		assertNotEquals(0.0f, a.distance(b), UtilEjml.TEST_F32);
	}

	@Test public void appendAll() {
		var points = new ArrayList<Point3D_F32>();
		points.add(new Point3D_F32(1, 2, 3));
		points.add(new Point3D_F32(-1, -2, -3));
		points.add(new Point3D_F32(3, 4, 5));

		var alg = new PackedBigArrayPoint3D_F32();
		alg.appendAll(points);

		assertEquals(points.size(), alg.size);

		for (int i = 0; i < points.size(); i++) {
			assertEquals(0.0f, points.get(i).distance(alg.getTemp(i)));
		}
	}
}
