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
import boofcv.struct.distort.Point2Transform2_F32;
import georegression.struct.point.Point2D_F32;

/**
 * Applies a transform which outputs normalized image coordinates then converts that into
 * pixel coordinates
 *
 * @author Peter Abeles
 */
@SuppressWarnings({"NullAway.Init", "Duplicates"})
@Generated("boofcv.alg.distort.Transform2ThenPixel_F64")
public class Transform2ThenPixel_F32 implements Point2Transform2_F32 {

	float fx, fy, skew, cx, cy;
	Point2Transform2_F32 first;

	public Transform2ThenPixel_F32( Point2Transform2_F32 first ) {
		this.first = first;
	}

	Transform2ThenPixel_F32() {}

	public Point2Transform2_F32 set( /**/double fx, /**/double fy, /**/double skew, /**/double cx, /**/double cy ) {
		this.fx = (float)fx;
		this.fy = (float)fy;
		this.skew = (float)skew;
		this.cx = (float)cx;
		this.cy = (float)cy;

		return this;
	}

	@Override
	public void compute( float x, float y, Point2D_F32 out ) {
		first.compute(x, y, out);
		x = out.x;
		y = out.y;
		out.x = fx*x + skew*y + cx;
		out.y = fy*y + cy;
	}

	@Override
	public Transform2ThenPixel_F32 copyConcurrent() {
		Transform2ThenPixel_F32 ret = new Transform2ThenPixel_F32();
		ret.first = this.first.copyConcurrent();
		ret.fx = fx;
		ret.fy = fy;
		ret.skew = skew;
		ret.cx = cx;
		ret.cy = cy;
		return ret;
	}
}
