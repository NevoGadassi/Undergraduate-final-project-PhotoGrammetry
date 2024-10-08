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

package boofcv.alg.distort.universal;

import javax.annotation.Generated;
import boofcv.struct.calib.CameraUniversalOmni;
import boofcv.testing.BoofStandardJUnit;
import georegression.misc.GrlConstants;
import georegression.struct.point.Point2D_F32;
import org.junit.jupiter.api.Test;

import static boofcv.alg.distort.universal.TestUniOmniPtoS_F32.createModel;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Generated("boofcv.alg.distort.universal.TestUniOmniStoP_F64")
public class TestUniOmniStoP_F32 extends BoofStandardJUnit {
	/**
	 * A point in the world center should appear in the image center
	 */
	@Test void worldIsImageCenter() {
		CameraUniversalOmni model = createModel(0.5f);

		UniOmniStoP_F32 alg = new UniOmniStoP_F32();
		alg.setModel(model);

		Point2D_F32 found = new Point2D_F32(10,10);
		alg.compute(0,0,1, found);  // directly forward on unit sphere

		assertEquals(320,found.x, GrlConstants.TEST_F32);
		assertEquals(240,found.y, GrlConstants.TEST_F32);
	}
}
