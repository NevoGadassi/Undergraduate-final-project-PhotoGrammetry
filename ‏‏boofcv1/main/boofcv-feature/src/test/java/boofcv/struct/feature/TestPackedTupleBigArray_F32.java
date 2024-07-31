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

package boofcv.struct.feature;

import javax.annotation.Generated;
import boofcv.struct.PackedArray;
import boofcv.struct.packed.GenericPackedArrayChecks;
import org.ejml.UtilEjml;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@Generated("boofcv.struct.feature.TestPackedTupleBigArray_F64")
class TestPackedTupleBigArray_F32 extends GenericPackedArrayChecks<TupleDesc_F32> {
	int DOF = 1;

	@Override protected PackedArray<TupleDesc_F32> createAlg() {
		return new PackedTupleBigArray_F32(DOF);
	}

	@Override protected TupleDesc_F32 createRandomPoint() {
		var point = new TupleDesc_F32(DOF);
		point.data[0] = (float) rand.nextGaussian();
		return point;
	}

	@Override protected void checkEquals( TupleDesc_F32 a, TupleDesc_F32 b ) {
		for (int i = 0; i < DOF; i++) {
			assertEquals(a.data[i], b.data[i], UtilEjml.TEST_F32);
		}
	}

	@Override protected void checkNotEquals( TupleDesc_F32 a, TupleDesc_F32 b ) {
		for (int i = 0; i < DOF; i++) {
			if (a.data[i] != b.data[i])
				return;
		}
		fail("The tuples are identical");
	}
}
