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

package boofcv.struct.sparse;

import javax.annotation.Generated;
/**
 * Specifies a pixel's gradient using float values.
 *
 * @author Peter Abeles
 */
@Generated("boofcv.struct.sparse.GradientValue_F64")
public class GradientValue_F32 implements GradientValue {

	public float x;
	public float y;

	@Override
	public void setTo( /**/double dx, /**/double dy ) {
		this.x = (float)dx;
		this.y = (float)dy;
	}

	@Override
	public /**/double getX() {
		return x;
	}

	@Override
	public /**/double getY() {
		return y;
	}
}
