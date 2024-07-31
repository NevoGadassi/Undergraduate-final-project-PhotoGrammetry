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
import boofcv.testing.BoofStandardJUnit;
import georegression.struct.point.Point2D_F32;
import org.ejml.UtilEjml;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Generated("boofcv.alg.distort.division.TestRemoveDivisionNtoN_F64")
class TestRemoveDivisionNtoN_F32 extends BoofStandardJUnit {
	@Test void compareManual() {
		float radial = 0.00002f;

		float x_dist = 40;
		float y_dist = 52;
		float r = x_dist*x_dist + y_dist*y_dist;

		float x_undist = x_dist/(1.0f + radial*r);
		float y_undist = y_dist/(1.0f + radial*r);

		var found = new Point2D_F32();
		new RemoveDivisionNtoN_F32().setRadial(radial).compute(x_dist, y_dist, found);

		assertEquals(0.0f, found.distance(x_undist, y_undist), UtilEjml.TEST_F32);
	}
}
