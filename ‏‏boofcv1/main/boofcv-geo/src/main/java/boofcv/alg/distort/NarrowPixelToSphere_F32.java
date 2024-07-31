/*
 * Copyright (c) 2020, Peter Abeles. All Rights Reserved.
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
import boofcv.struct.distort.Point2Transform2_F32;
import boofcv.struct.distort.Point2Transform3_F32;
import georegression.struct.point.Point2D_F32;
import georegression.struct.point.Point3D_F32;

/**
 * Given a transform from pixels to normalized image coordinate it will output unit sphere coordinates.
 *
 * @author Peter Abeles
 */
@Generated("boofcv.alg.distort.NarrowPixelToSphere_F64")
public class NarrowPixelToSphere_F32 implements Point2Transform3_F32 {

	Point2Transform2_F32 pixelToNorm;
	Point2D_F32 projected = new Point2D_F32();

	public NarrowPixelToSphere_F32( Point2Transform2_F32 pixelToNorm ) {
		this.pixelToNorm = pixelToNorm;
	}

	@Override
	public void compute( float x, float y, Point3D_F32 out ) {
		pixelToNorm.compute(x, y, projected);

		out.setTo(projected.x, projected.y, 1);
		out.scale(1.0f/out.norm());
	}

	@Override
	public Point2Transform3_F32 copyConcurrent() {
		return new NarrowPixelToSphere_F32(this.pixelToNorm.copyConcurrent());
	}
}
