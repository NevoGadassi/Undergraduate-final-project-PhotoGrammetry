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

package boofcv.struct.geo;

import javax.annotation.Generated;
import georegression.struct.point.Point4D_F32;

/**
 * A 4D point with an index associated with it
 *
 * @author Peter Abeles
 */
@Generated("boofcv.struct.geo.PointIndex4D_F64")
public class PointIndex4D_F32 extends PointIndex<PointIndex4D_F32, Point4D_F32> {

	public PointIndex4D_F32( float x, float y, float z, float w, int index ) {
		this();
		setTo(x, y, z, w, index);
	}

	public PointIndex4D_F32( float x, float y, float z, float w ) {
		this();
		setTo(x, y, z, w, 0);
	}

	public PointIndex4D_F32() {super(new Point4D_F32());}

	public PointIndex4D_F32( Point4D_F32 p, int index ) {
		this();
		setTo(p, index);
	}

	public void setTo( float x, float y, float z, float w, int index ) {
		this.p.setTo(x, y, z, w);
		this.index = index;
	}

	@Override
	public PointIndex4D_F32 copy() {
		return new PointIndex4D_F32(p, index);
	}
}
