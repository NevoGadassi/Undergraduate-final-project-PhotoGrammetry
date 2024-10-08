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

package boofcv.struct.distort;

import javax.annotation.Generated;
/**
 * Extends {@link Point2Transform2_F32} and adds the ability to change the motion model
 *
 * @author Peter Abeles
 */
@Generated("boofcv.struct.distort.Point2Transform2Model_F64")
public interface Point2Transform2Model_F32<Model> extends Point2Transform2_F32 {

	/**
	 * Specifies the distortion model used by the transform
	 *
	 * @param model Distortion model
	 */
	void setModel( Model model );

	/**
	 * Returns the active motion model
	 *
	 * @return motion model
	 */
	Model getModel();

	/**
	 * Returns a new instance of the motion model
	 *
	 * @return new instance
	 */
	Model newInstanceModel();
}
