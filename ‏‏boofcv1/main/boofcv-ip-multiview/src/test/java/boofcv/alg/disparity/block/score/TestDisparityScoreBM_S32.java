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

package boofcv.alg.disparity.block.score;

import boofcv.alg.disparity.DisparityBlockMatch;
import boofcv.alg.disparity.block.BlockRowScore;
import boofcv.alg.disparity.block.DisparitySelect;
import boofcv.factory.disparity.DisparityError;
import boofcv.struct.image.GrayS16;
import boofcv.struct.image.GrayU8;
import boofcv.struct.image.ImageType;

public class TestDisparityScoreBM_S32 extends ChecksDisparityBM<GrayS16, GrayU8> {

	TestDisparityScoreBM_S32() {
		super(0, 2000, DisparityError.SAD, GrayS16.class, GrayU8.class);
	}

	@Override
	protected DisparityBlockMatch<GrayS16, GrayU8>
	createAlg( int radiusX, int radiusY, BlockRowScore scoreRow, DisparitySelect compDisp ) {
		return new DisparityScoreBM_S32<>(radiusX, radiusY, scoreRow, compDisp, ImageType.SB_S16);
	}
}
