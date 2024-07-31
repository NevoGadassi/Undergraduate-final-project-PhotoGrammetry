/*
 * Copyright (c) 2022, Peter Abeles. All Rights Reserved.
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

package boofcv.alg.geo.impl;

import javax.annotation.Generated;
import boofcv.alg.distort.pinhole.PinholeNtoP_F32;
import boofcv.alg.distort.pinhole.PinholePtoN_F32;
import boofcv.alg.geo.PerspectiveOps;
import boofcv.factory.distort.LensDistortionFactory;
import boofcv.struct.calib.CameraModel;
import boofcv.struct.calib.CameraPinhole;
import boofcv.struct.distort.Point2Transform2_F32;
import georegression.geometry.GeometryMath_F32;
import georegression.struct.point.Point2D_F32;
import georegression.struct.point.Point3D_F32;
import georegression.struct.point.Point4D_F32;
import georegression.struct.point.Vector3D_F32;
import georegression.struct.se.Se3_F32;
import georegression.transform.se.SePointOps_F32;
import org.ejml.data.FMatrix3x3;
import org.ejml.data.FMatrixRMaj;
import org.ejml.dense.fixed.CommonOps_FDF3;
import org.ejml.dense.row.CommonOps_FDRM;
import org.jetbrains.annotations.Nullable;

/**
 * Implementation of {@link PerspectiveOps} functions for 32-bit floats
 *
 * @author Peter Abeles
 */
@Generated("boofcv.alg.geo.impl.ImplPerspectiveOps_F64")
public class ImplPerspectiveOps_F32 {

	public static <C extends CameraPinhole> C adjustIntrinsic( C parameters,
															   FMatrixRMaj adjustMatrix,
															   @Nullable C adjustedParam ) {
		if (adjustedParam == null)
			adjustedParam = parameters.createLike();
		adjustedParam.setTo(parameters);

		FMatrixRMaj K = ImplPerspectiveOps_F32.pinholeToMatrix(parameters, (FMatrixRMaj)null);
		FMatrixRMaj K_adj = new FMatrixRMaj(3, 3);
		CommonOps_FDRM.mult(adjustMatrix, K, K_adj);

		ImplPerspectiveOps_F32.matrixToPinhole(K_adj, parameters.width, parameters.height, adjustedParam);

		return adjustedParam;
	}

	public static FMatrixRMaj pinholeToMatrix( float fx, float fy, float skew,
											   float cx, float cy, @Nullable FMatrixRMaj K ) {
		if (K == null) {
			K = new FMatrixRMaj(3, 3);
		} else {
			K.reshape(3, 3);
		}

		CommonOps_FDRM.fill(K, 0);

		K.data[0] = fx;
		K.data[1] = skew;
		K.data[2] = cx;
		K.data[4] = fy;
		K.data[5] = cy;
		K.data[8] = 1;

		return K;
	}

	public static FMatrixRMaj pinholeToMatrix( CameraPinhole param, @Nullable FMatrixRMaj K ) {
		return pinholeToMatrix((float)param.fx, (float)param.fy, (float)param.skew, (float)param.cx, (float)param.cy, K);
	}

	public static FMatrix3x3 pinholeToMatrix( CameraPinhole param, @Nullable FMatrix3x3 K ) {

		if (K == null) {
			K = new FMatrix3x3();
		} else {
			CommonOps_FDF3.fill(K, 0);
		}

		K.a11 = (float)param.fx;
		K.a12 = (float)param.skew;
		K.a13 = (float)param.cx;
		K.a22 = (float)param.fy;
		K.a23 = (float)param.cy;
		K.a33 = 1;

		return K;
	}

	public static CameraPinhole matrixToPinhole( FMatrixRMaj K, int width, int height, @Nullable CameraPinhole output ) {

		if (output == null)
			output = new CameraPinhole();

		output.fx = K.get(0, 0);
		output.fy = K.get(1, 1);
		output.skew = K.get(0, 1);
		output.cx = K.get(0, 2);
		output.cy = K.get(1, 2);

		output.width = width;
		output.height = height;

		return output;
	}

	public static Point2D_F32 convertNormToPixel( CameraModel param, float x, float y, @Nullable Point2D_F32 pixel ) {

		if (pixel == null)
			pixel = new Point2D_F32();

		Point2Transform2_F32 normToPixel = LensDistortionFactory.narrow(param).distort_F32(false, true);

		normToPixel.compute(x, y, pixel);

		return pixel;
	}

	public static Point2D_F32 convertNormToPixel( FMatrixRMaj K, Point2D_F32 norm, @Nullable Point2D_F32 pixel ) {
		if (pixel == null)
			pixel = new Point2D_F32();

		PinholeNtoP_F32 alg = new PinholeNtoP_F32();
		alg.setK(K.get(0, 0), K.get(1, 1), K.get(0, 1), K.get(0, 2), K.get(1, 2));

		alg.compute(norm.x, norm.y, pixel);

		return pixel;
	}

	public static Point2D_F32 convertPixelToNorm( CameraModel param, Point2D_F32 pixel, @Nullable Point2D_F32 norm ) {
		if (norm == null)
			norm = new Point2D_F32();

		Point2Transform2_F32 pixelToNorm = LensDistortionFactory.narrow(param).undistort_F32(true, false);

		pixelToNorm.compute(pixel.x, pixel.y, norm);

		return norm;
	}

	public static Point2D_F32 convertPixelToNorm( FMatrixRMaj K, Point2D_F32 pixel, @Nullable Point2D_F32 norm ) {
		if (norm == null)
			norm = new Point2D_F32();

		var alg = new PinholePtoN_F32();
		alg.setK(K.get(0, 0), K.get(1, 1), K.get(0, 1), K.get(0, 2), K.get(1, 2));

		alg.compute(pixel.x, pixel.y, norm);

		return norm;
	}

	public static Point2D_F32 convertPixelToNorm( CameraPinhole intrinsic, float pixelX, float pixelY, @Nullable Point2D_F32 norm ) {
		if (norm == null)
			norm = new Point2D_F32();

		float a11 = (float)(1.0f/intrinsic.fx);
		float a12 = (float)(-intrinsic.skew/(intrinsic.fx*intrinsic.fy));
		float a13 = (float)((intrinsic.skew*intrinsic.cy - intrinsic.cx*intrinsic.fy)/(intrinsic.fx*intrinsic.fy));
		float a22 = (float)(1.0f/intrinsic.fy);
		float a23 = (float)(-intrinsic.cy/intrinsic.fy);

		norm.x = a11*pixelX + a12*pixelY + a13;
		norm.y = a22*pixelY + a23;

		return norm;
	}

	public static @Nullable Point2D_F32 renderPixel( Se3_F32 worldToCamera,
													 @Nullable FMatrixRMaj K, Point3D_F32 X,
													 @Nullable Point2D_F32 pixel ) {
		FMatrixRMaj R = worldToCamera.R;
		Vector3D_F32 T = worldToCamera.T;

		// [R T]*X
		float x = R.data[0]*X.x + R.data[1]*X.y + R.data[2]*X.z + T.x;
		float y = R.data[3]*X.x + R.data[4]*X.y + R.data[5]*X.z + T.y;
		float z = R.data[6]*X.x + R.data[7]*X.y + R.data[8]*X.z + T.z;

		// see if it's behind the camera
		if (z <= 0)
			return null;

		if (pixel == null)
			pixel = new Point2D_F32();

		pixel.setTo(x/z, y/z);

		if (K == null)
			return pixel;

		// convert into pixel coordinates
		return GeometryMath_F32.mult(K, pixel, pixel);
	}

	public static @Nullable Point2D_F32 renderPixel( Se3_F32 worldToCamera,
													 float fx, float skew, float cx, float fy, float cy,
													 Point3D_F32 X, @Nullable Point2D_F32 pixel ) {
		var X_cam = new Point3D_F32();

		SePointOps_F32.transform(worldToCamera, X, X_cam);

		// see if it's behind the camera
		if (X_cam.z <= 0)
			return null;

		if (pixel == null)
			pixel = new Point2D_F32();

		float xx = X_cam.x/X_cam.z;
		float yy = X_cam.y/X_cam.z;

		pixel.x = fx*xx + skew*yy + cx;
		pixel.y = fy*yy + cy;

		return pixel;
	}

	public static Point3D_F32 renderPointing( Se3_F32 worldToCamera,
											  float fx, float skew, float cx, float fy, float cy,
											  Point3D_F32 X, @Nullable Point3D_F32 pixel ) {
		var X_cam = new Point3D_F32();

		SePointOps_F32.transform(worldToCamera, X, X_cam);

		if (pixel == null)
			pixel = new Point3D_F32();

		// Make sure the norm of the point is 1 to avoid numerical issues
		float n = X_cam.norm();
		float xx = X_cam.x/n;
		float yy = X_cam.y/n;
		float zz = X_cam.z/n;

		pixel.x = fx*xx + skew*yy + cx*zz;
		pixel.y = fy*yy + cy*zz;
		pixel.z = zz;

		return pixel;
	}

	public static @Nullable Point2D_F32 renderPixel( Se3_F32 worldToCamera,
													 float fx, float skew, float cx, float fy, float cy,
													 Point4D_F32 X, @Nullable Point2D_F32 pixel ) {

		FMatrixRMaj R = worldToCamera.R;
		Vector3D_F32 T = worldToCamera.T;

		// [R T]*X
		float x = R.data[0]*X.x + R.data[1]*X.y + R.data[2]*X.z + T.x*X.w;
		float y = R.data[3]*X.x + R.data[4]*X.y + R.data[5]*X.z + T.y*X.w;
		float z = R.data[6]*X.x + R.data[7]*X.y + R.data[8]*X.z + T.z*X.w;

		// see if it's behind the camera
		if (z <= 0)
			return null;

		if (pixel == null)
			pixel = new Point2D_F32();

		float xx = x/z;
		float yy = y/z;

		pixel.x = fx*xx + skew*yy + cx;
		pixel.y = fy*yy + cy;

		return pixel;
	}

	public static void renderPixel( FMatrixRMaj worldToCamera, Point3D_F32 X, Point3D_F32 pixelH ) {
		FMatrixRMaj P = worldToCamera;

		pixelH.x = P.data[0]*X.x + P.data[1]*X.y + P.data[2]*X.z + P.data[3];
		pixelH.y = P.data[4]*X.x + P.data[5]*X.y + P.data[6]*X.z + P.data[7];
		pixelH.z = P.data[8]*X.x + P.data[9]*X.y + P.data[10]*X.z + P.data[11];
	}

	public static void renderPixel( FMatrixRMaj worldToCamera, Point3D_F32 X, Point2D_F32 pixel ) {
		FMatrixRMaj P = worldToCamera;

		float x = P.data[0]*X.x + P.data[1]*X.y + P.data[2]*X.z + P.data[3];
		float y = P.data[4]*X.x + P.data[5]*X.y + P.data[6]*X.z + P.data[7];
		float z = P.data[8]*X.x + P.data[9]*X.y + P.data[10]*X.z + P.data[11];

		pixel.x = x/z;
		pixel.y = y/z;
	}

	public static void renderPixel( FMatrixRMaj cameraMatrix, Point4D_F32 X, Point3D_F32 pixelH ) {
		FMatrixRMaj P = cameraMatrix;

		// @formatter:off
		pixelH.x = P.data[0]*X.x + P.data[1]*X.y + P.data[2 ]*X.z + P.data[3 ]*X.w;
		pixelH.y = P.data[4]*X.x + P.data[5]*X.y + P.data[6 ]*X.z + P.data[7 ]*X.w;
		pixelH.z = P.data[8]*X.x + P.data[9]*X.y + P.data[10]*X.z + P.data[11]*X.w;
		// @formatter:on
	}

	public static void renderPixel( FMatrixRMaj cameraMatrix, Point4D_F32 X, Point2D_F32 pixel ) {
		FMatrixRMaj P = cameraMatrix;

		float x = P.data[0]*X.x + P.data[1]*X.y + P.data[2]*X.z + P.data[3]*X.w;
		float y = P.data[4]*X.x + P.data[5]*X.y + P.data[6]*X.z + P.data[7]*X.w;
		float z = P.data[8]*X.x + P.data[9]*X.y + P.data[10]*X.z + P.data[11]*X.w;

		pixel.x = x/z;
		pixel.y = y/z;
	}

	public static float distance3DvsH( Point3D_F32 a, Point4D_F32 b, float tol ) {
		// convert the homogenous point into a 3D point.
		float x = b.x;
		float y = b.y;
		float z = b.z;

		float r = (float)Math.sqrt(x*x + y*y + z*z);

		// See if the homogenous point is at infinity, within tolerance
		if (r*tol > (float)Math.abs(b.w)) {
			return Float.POSITIVE_INFINITY;
		}

		// Finish the conversion to 3D
		x /= b.w;
		y /= b.w;
		z /= b.w;

		return a.distance(x, y, z);
	}

	public static float distance( Point4D_F32 a, Point4D_F32 b ) {

		float na = a.norm();
		float nb = b.norm();

		if (na == 0.0f || nb == 0.0f)
			return a.distance(b);

		// take in account sign ambiguity
		return (float)Math.sqrt(Math.min(distance(a, b, na, nb), distance(a, b, -na, nb)));
	}

	public static float distance( Point4D_F32 a, Point4D_F32 b, float na, float nb ) {
		float xa = a.x/na;
		float ya = a.y/na;
		float za = a.z/na;
		float wa = a.w/na;

		float xb = b.x/nb;
		float yb = b.y/nb;
		float zb = b.z/nb;
		float wb = b.w/nb;

		float dx = xa - xb;
		float dy = ya - yb;
		float dz = za - zb;
		float dw = wa - wb;

		return dx*dx + dy*dy + dz*dz + dw*dw;
	}

	public static FMatrixRMaj createCameraMatrix( FMatrixRMaj R, Vector3D_F32 T,
												  @Nullable FMatrixRMaj K,
												  @Nullable FMatrixRMaj ret ) {
		if (ret == null)
			ret = new FMatrixRMaj(3, 4);

		CommonOps_FDRM.insert(R, ret, 0, 0);

		ret.data[3] = T.x;
		ret.data[7] = T.y;
		ret.data[11] = T.z;

		if (K == null)
			return ret;

		FMatrixRMaj temp = new FMatrixRMaj(3, 4);
		CommonOps_FDRM.mult(K, ret, temp);

		ret.setTo(temp);

		return ret;
	}
}
