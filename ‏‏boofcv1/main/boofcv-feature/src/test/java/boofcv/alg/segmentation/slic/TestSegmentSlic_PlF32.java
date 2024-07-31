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

package boofcv.alg.segmentation.slic;

import boofcv.struct.ConnectRule;
import boofcv.struct.image.GrayF32;
import boofcv.struct.image.ImageType;
import boofcv.struct.image.Planar;

public class TestSegmentSlic_PlF32 extends GeneralSegmentSlicColorChecks<Planar<GrayF32>> {

	public TestSegmentSlic_PlF32() {
		super(ImageType.pl(3, GrayF32.class));
	}

	@Override
	public SegmentSlic<Planar<GrayF32>> createAlg(int numberOfRegions, float m, int totalIterations, ConnectRule rule) {
		return new SegmentSlic_PlF32(numberOfRegions,m,totalIterations,rule,3);
	}

}
