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

package boofcv.alg.geo.robust;

import org.ddogleg.fitting.modelset.ModelMatcher;

/**
 * {@link ModelMatcher} with view specific information
 *
 * @author Peter Abeles
 */
public interface ModelMatcherViews<Model, Point, Camera> extends ModelMatcher<Model, Point> {

	/**
	 * Specify parameters for a particular view
	 *
	 * @param view which view this belongs to
	 * @param viewInfo Information about this view. E.g. image dimension, intrinsic parameters, ...etc
	 */
	void setView( int view, Camera viewInfo );

	/**
	 * Returns the new of views which need to be set
	 */
	int getNumberOfViews();
}
