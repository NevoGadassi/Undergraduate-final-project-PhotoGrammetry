/*
 * Copyright (c) 2020, Peter Abeles. All Rights Reserved.
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
import georegression.struct.affine.Affine2D_F32;
import georegression.struct.point.Point2D_F32;
import org.ejml.UtilEjml;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Generated("boofcv.alg.distort.TestPointTransformAffine_F64")
public class TestPointTransformAffine_F32 extends BoofStandardJUnit {
	@Test void known() {
		var affine = new Affine2D_F32(1,0,0,2,-1,2);
		var alg = new PointTransformAffine_F32(affine);

		var found = new Point2D_F32();
		alg.compute(5,9,found);

		assertEquals(4, found.x, UtilEjml.TEST_F32);
		assertEquals(20, found.y, UtilEjml.TEST_F32);
	}
}
