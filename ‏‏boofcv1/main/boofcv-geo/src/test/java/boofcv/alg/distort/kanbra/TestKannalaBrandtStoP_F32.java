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

package boofcv.alg.distort.kanbra;

import javax.annotation.Generated;
import boofcv.struct.calib.CameraKannalaBrandt;
import boofcv.testing.BoofStandardJUnit;
import georegression.struct.point.Point2D_F32;
import georegression.struct.point.Point3D_F32;
import org.ejml.UtilEjml;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Generated("boofcv.alg.distort.kanbra.TestKannalaBrandtStoP_F64")
class TestKannalaBrandtStoP_F32 extends BoofStandardJUnit {
	/**
	 * pass in variable parameter lengths and see if bad stuff happens
	 */
	@Test void doesItBlowUp() {
		List<CameraKannalaBrandt> models = new ArrayList<>();

		models.add(new CameraKannalaBrandt().fsetK(500, 550, 0.0f, 600, 650));
		models.add(new CameraKannalaBrandt().fsetK(500, 550, 0.1f, 600, 650));
		models.add(new CameraKannalaBrandt().fsetK(500, 550, 0.0f, 600, 650).fsetSymmetric(1.0f,0.1f));
		models.add(new CameraKannalaBrandt().fsetK(500, 550, 0.0f, 600, 650).fsetRadial(1.0f,0.1f));
		models.add(new CameraKannalaBrandt().fsetK(500, 550, 0.0f, 600, 650).fsetTangent(1.0f,0.1f));
		models.add(new CameraKannalaBrandt().fsetK(500, 550, 0.0f, 600, 650).
				fsetSymmetric(1.0f,0.1f).fsetRadial(1.0f,0.1f).fsetTangent(1.0f,0.1f));


		Point3D_F32 P3 = new Point3D_F32(0.1f, -0.05f, 0.8f);
		Point2D_F32 pixel = new Point2D_F32();

		for (CameraKannalaBrandt camera : models) {
			new KannalaBrandtStoP_F32(camera).compute(P3.x, P3.y, P3.z, pixel);
			assertFalse(UtilEjml.isUncountable(pixel.normSq()));
		}
	}

	/**
	 * Qualitative checks for when there's only symmetric distortion
	 */
	@Test void onlySymmetric() {
		// different symmetric coefficient that has a known behavior to the distortion
		CameraKannalaBrandt fish1 = new CameraKannalaBrandt().fsetK(500, 550, 0.0f, 600, 650).fsetSymmetric(1.0f,0.1f);
		CameraKannalaBrandt fish2 = new CameraKannalaBrandt().fsetK(500, 550, 0.0f, 600, 650).fsetSymmetric(1.0f,0.4f);

		Point2D_F32 pixel1 = new Point2D_F32();
		Point2D_F32 pixel2 = new Point2D_F32();

		// rotate around the circle. This should work in every direction
		for (int i = 0; i < 20; i++ ) {
			float theta = (float)Math.PI*2.0f*i/20.0f;
			float r = 0.5f;
			float x = r * (float)Math.cos(theta);
			float y = r * (float)Math.sin(theta);

			Point3D_F32 P3 = new Point3D_F32(x, y, 0.8f);

			new KannalaBrandtStoP_F32(fish1).compute(P3.x, P3.y, P3.z, pixel1);
			new KannalaBrandtStoP_F32(fish2).compute(P3.x, P3.y, P3.z, pixel2);

			pixel1.x -= (float) fish1.cx;
			pixel1.y -= (float) fish1.cy;

			pixel2.x -= (float) fish2.cx;
			pixel2.y -= (float) fish2.cy;

			// NOTE: The norm changes because fx != fy
//			System.out.printf("angle=%.3f (%.2f %.2f) n=%.2f %.2f\n", theta, x,y,pixel1.norm(), pixel2.norm());

			// larger positive coefficients should push points out farther in the radial direction
			assertTrue(pixel1.norm() < pixel2.norm());
		}
	}
}
