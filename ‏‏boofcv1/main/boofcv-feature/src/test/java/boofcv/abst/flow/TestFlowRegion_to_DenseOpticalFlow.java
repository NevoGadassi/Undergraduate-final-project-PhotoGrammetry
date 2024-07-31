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

package boofcv.abst.flow;

import boofcv.factory.flow.FactoryDenseOpticalFlow;
import boofcv.struct.image.GrayF32;
import boofcv.struct.image.GrayU8;
import boofcv.testing.BoofStandardJUnit;

public class TestFlowRegion_to_DenseOpticalFlow extends BoofStandardJUnit {

	public void allTests() {
		Class imageTypes[] = new Class[]{GrayU8.class,GrayF32.class};

		for( Class it : imageTypes ) {
			new GeneralDenseOpticalFlowChecks(it) {
				@Override
				public DenseOpticalFlow createAlg(Class imageType) {
					return FactoryDenseOpticalFlow.region(null,imageType);
				}
			}.allTests();
		}
	}

}
