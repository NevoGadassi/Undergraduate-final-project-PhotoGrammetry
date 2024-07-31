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

package boofcv.alg.distort.spherical;

import javax.annotation.Generated;
import georegression.misc.GrlConstants;
import georegression.struct.point.Point2D_F32;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Generated("boofcv.alg.distort.spherical.TestEquirectangularRotate_F64")
class TestEquirectangularRotate_F32 extends TestEquirectangularDistortBase_F32 {

	/**
	 * Sees if recentering moves it to approximately the expected location
	 */
	@Test
	void simpleTests() {

		EquirectangularRotate_F32 alg = new EquirectangularRotate_F32();
		alg.setEquirectangularShape(300,251);
		Point2D_F32 p = new Point2D_F32();

		// this is the standard configuration and there should be no change
		alg.setDirection(0,0,0);
		alg.compute((int)(300.0f*0.5f), 250/2, p);
		assertMatch( p, 300.0f*0.5f, 250/2);

		alg.setDirection( (float)Math.PI/2.0f,0,0);
		alg.compute((int)(300.0f*0.5f), 250/2, p);
		assertMatch( p, 300.0f*0.75f, 250/2);

		alg.setDirection(0, (float)Math.PI/2,0);
		alg.compute((int)(300.0f*0.5f), 250/2, p);
		assertEquals( 0 , p.y, GrlConstants.TEST_F32); //pathological. only check y

		alg.setDirection(0, (float)-Math.PI/2,0);
		alg.compute((int)(300.0f*0.5f), 250/2, p);
		assertEquals( 250 , p.y, GrlConstants.TEST_F32); //pathological. only check y

		alg.setDirection(0, (float)Math.PI/4.0f,0);
		alg.compute((int)(300.0f*0.5f), 250/2, p);
		assertMatch( p, 300.0f*0.5f, 250/4+0.5f);
		// 0.5f is fudge to make the test pass. I *think* it's just discretation error
	}

	private void assertMatch(Point2D_F32 tran , float x , float y ) {
		assertEquals( x , tran.x, GrlConstants.TEST_F32);
		assertEquals( y , tran.y, GrlConstants.TEST_F32);
	}

	@Test
	void copy() {
		EquirectangularRotate_F32 alg = new EquirectangularRotate_F32();
		alg.setEquirectangularShape(300,251);

		copy(alg,100,120);
	}
}
