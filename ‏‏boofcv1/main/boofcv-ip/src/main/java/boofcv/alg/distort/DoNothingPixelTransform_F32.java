/*
 * Copyright (c) 2021, Peter Abeles. All Rights Reserved.
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

package boofcv.alg.distort;

import javax.annotation.Generated;
import boofcv.struct.distort.PixelTransform;
import georegression.struct.point.Point2D_F32;

/**
 * Pixel transform which sets the output to be exactly the same as the input
 *
 * @author Peter Abeles
 */
@Generated("boofcv.alg.distort.DoNothingPixelTransform_F64")
public class DoNothingPixelTransform_F32 implements PixelTransform<Point2D_F32> {
	@Override
	public void compute( int x, int y, Point2D_F32 output ) {
		output.x = x;
		output.y = y;
	}

	@Override
	public DoNothingPixelTransform_F32 copyConcurrent() {
		return new DoNothingPixelTransform_F32();
	}
}
