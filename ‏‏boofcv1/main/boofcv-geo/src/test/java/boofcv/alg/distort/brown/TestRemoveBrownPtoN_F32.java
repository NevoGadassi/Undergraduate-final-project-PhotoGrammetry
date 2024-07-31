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

package boofcv.alg.distort.brown;

import javax.annotation.Generated;
import boofcv.alg.distort.Transform2ThenPixel_F32;
import boofcv.alg.geo.PerspectiveOps;
import boofcv.testing.BoofStandardJUnit;
import georegression.geometry.GeometryMath_F32;
import georegression.misc.GrlConstants;
import georegression.struct.point.Point2D_F32;
import org.ejml.data.FMatrixRMaj;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Generated("boofcv.alg.distort.brown.TestRemoveBrownPtoN_F64")
public class TestRemoveBrownPtoN_F32 extends BoofStandardJUnit {

	@Test void checkAgainstAdd() {
		checkAgainstAdd(0,0);
		checkAgainstAdd(0.1f,-0.05f);
	}

	public void checkAgainstAdd( float t1 , float t2 ) {
		float fx = 600;
		float fy = 500;
		float skew = 2;
		float xc = 300;
		float yc = 350;

		/**/double[] radial= new /**/double[]{0.12,-0.13};

		Point2D_F32 point = new Point2D_F32();

		float undistX = 19.5f;
		float undistY = 200.1f;

		AddBrownPtoN_F32 p_to_n = new AddBrownPtoN_F32().setK(fx, fy, skew, xc, yc).setDistortion(radial,t1,t2);
		new Transform2ThenPixel_F32(p_to_n).set(fx, fy, skew, xc, yc).compute(undistX, undistY, point);

		float distX = point.x;
		float distY = point.y;

		RemoveBrownPtoN_F32 alg = new RemoveBrownPtoN_F32().setK(fx,fy,skew,xc,yc).setDistortion(radial,t1,t2);

		alg.compute(distX, distY, point);

		/// go from calibrated coordinates to pixel
		FMatrixRMaj K = PerspectiveOps.pinholeToMatrix(fx, fy, skew, xc, yc);

		GeometryMath_F32.mult(K,point,point);

		assertEquals(undistX,point.x, GrlConstants.TEST_SQ_F32);
		assertEquals(undistY,point.y, GrlConstants.TEST_SQ_F32);
	}
}
