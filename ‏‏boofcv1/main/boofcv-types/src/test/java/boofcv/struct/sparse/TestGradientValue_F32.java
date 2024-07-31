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

package boofcv.struct.sparse;

import javax.annotation.Generated;
import boofcv.testing.BoofStandardJUnit;
import org.ejml.UtilEjml;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Generated("boofcv.struct.sparse.TestGradientValue_F64")
class TestGradientValue_F32 extends BoofStandardJUnit {
	@Test
	void set_get() {
		var alg = new GradientValue_F32();
		alg.setTo(2.3f,4.5f);
		assertEquals(2.3f, alg.getX(), UtilEjml.TEST_F32);
		assertEquals(4.5f, alg.getY(), UtilEjml.TEST_F32);
	}
}
