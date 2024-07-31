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

package boofcv.struct.border;

import javax.annotation.Generated;
import boofcv.struct.image.ImageType;
import boofcv.struct.image.InterleavedF32;

@Generated("boofcv.struct.border.TestImageBorder1D_IL_F64")
public class TestImageBorder1D_IL_F32 extends GenericImageBorder1DTests<InterleavedF32> {

	public TestImageBorder1D_IL_F32() {
		super(ImageType.il(2, InterleavedF32.class));
	}

	@Override
	public ImageBorder<InterleavedF32> wrap( InterleavedF32 image ) {
		ImageBorder1D_IL_F32 ret = new ImageBorder1D_IL_F32(DummyBorderIndex1D_Wrap::new);
		ret.setImage(image);
		return ret;
	}
}
