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

package boofcv.struct.calib;

import org.ejml.UtilEjml;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class TestCameraKannalaBrandt extends CommonCameraChecks {
	@Test void fsetSymmetric() {
		CameraKannalaBrandt cam = new CameraKannalaBrandt().fsetSymmetric(0.1, 0.2);
		assertArrayEquals(new double[]{0.1, 0.2}, cam.symmetric, UtilEjml.TEST_F64);
	}

	@Test void fsetDistRadial() {
		CameraKannalaBrandt cam = new CameraKannalaBrandt().fsetRadial(0.1, 0.2);
		assertArrayEquals(new double[]{0.1, 0.2}, cam.radial, UtilEjml.TEST_F64);
	}

	@Test void fsetDistTangent() {
		CameraKannalaBrandt cam = new CameraKannalaBrandt().fsetTangent(0.1, 0.2);
		assertArrayEquals(new double[]{0.1, 0.2}, cam.tangent, UtilEjml.TEST_F64);
	}
}
