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
import boofcv.struct.distort.Point2Transform2Model_F32;
import boofcv.struct.distort.Point2Transform2_F32;
import georegression.struct.affine.Affine2D_F32;
import georegression.struct.point.Point2D_F32;
import georegression.transform.affine.AffinePointOps_F32;

/**
 * Applies an affine transform to a 2D point.
 *
 * @author Peter Abeles
 */
@Generated("boofcv.alg.distort.PointTransformAffine_F64")
public class PointTransformAffine_F32 implements Point2Transform2Model_F32<Affine2D_F32> {

	protected final Affine2D_F32 model = new Affine2D_F32();

	public PointTransformAffine_F32( Affine2D_F32 affine ) {
		this.model.setTo(affine);
	}

	public PointTransformAffine_F32() {}

	@Override public void compute( float x, float y, Point2D_F32 out ) {
		AffinePointOps_F32.transform(model, x, y, out);
	}

	@Override public Point2Transform2_F32 copyConcurrent() {
		return new PointTransformAffine_F32(model);
	}

	@Override public void setModel( Affine2D_F32 model ) {
		this.model.setTo(model);
	}

	@Override public Affine2D_F32 getModel() {
		return model;
	}

	@Override public Affine2D_F32 newInstanceModel() {
		return new Affine2D_F32();
	}
}
