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

package boofcv.alg.filter.misc;

import boofcv.struct.image.*;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Generated;

import boofcv.concurrency.BoofConcurrency;
import boofcv.alg.filter.misc.ImageLambdaFilters.*;

/**
 * Image filters which have been abstracted using lambdas. In most situations the 'src' image is assumed to be
 * passed in directory to the lambda, along with any other input parameters. What's given to the lambda
 * are parameters which define the local region. For inner functions, it can be assumed that all pixel values passed
 * in have a region contained entirely inside the region.
 *
 * <ol>
 *     <li>rectangle-center: filter that's applied to a local rectangular region centered on a pixel</li>
 * </ol>
 *
 * <p>DO NOT MODIFY. Automatically generated code created by GenerateImageLambdaFilters</p>
 *
 * @author Peter Abeles
 */
@Generated("boofcv.alg.filter.misc.ImageLambdaFilters")
public class ImageLambdaFilters_MT {

	public static void filterRectCenterInner( GrayI8 src, int radiusX, int radiusY, GrayI8 dst,
											  @Nullable Object workspace, RectCenter_S32 filter ) {
		final int y0 = radiusY;
		final int y1 = src.height - radiusY;

		// Go through all inner pixels

		BoofConcurrency.loopFor(y0, y1 , y -> {
			int indexSrc = src.startIndex + y*src.stride + radiusX;
			int indexDst = dst.startIndex + y*dst.stride + radiusX;

			// index of last pixel along x-axis it should process
			int end = src.startIndex + y*src.stride + src.width - radiusX;
			while (indexSrc < end) {
				// Apply the transform. It's assumed that the src image has been passed into the lambda
				dst.data[indexDst++] = (byte)filter.apply(indexSrc++, workspace);
			}
		});
	}

	public static void filterRectCenterInner( GrayI16 src, int radiusX, int radiusY, GrayI16 dst,
											  @Nullable Object workspace, RectCenter_S32 filter ) {
		final int y0 = radiusY;
		final int y1 = src.height - radiusY;

		// Go through all inner pixels

		BoofConcurrency.loopFor(y0, y1 , y -> {
			int indexSrc = src.startIndex + y*src.stride + radiusX;
			int indexDst = dst.startIndex + y*dst.stride + radiusX;

			// index of last pixel along x-axis it should process
			int end = src.startIndex + y*src.stride + src.width - radiusX;
			while (indexSrc < end) {
				// Apply the transform. It's assumed that the src image has been passed into the lambda
				dst.data[indexDst++] = (short)filter.apply(indexSrc++, workspace);
			}
		});
	}

	public static void filterRectCenterInner( GrayS32 src, int radiusX, int radiusY, GrayS32 dst,
											  @Nullable Object workspace, RectCenter_S32 filter ) {
		final int y0 = radiusY;
		final int y1 = src.height - radiusY;

		// Go through all inner pixels

		BoofConcurrency.loopFor(y0, y1 , y -> {
			int indexSrc = src.startIndex + y*src.stride + radiusX;
			int indexDst = dst.startIndex + y*dst.stride + radiusX;

			// index of last pixel along x-axis it should process
			int end = src.startIndex + y*src.stride + src.width - radiusX;
			while (indexSrc < end) {
				// Apply the transform. It's assumed that the src image has been passed into the lambda
				dst.data[indexDst++] = (int)filter.apply(indexSrc++, workspace);
			}
		});
	}

	public static void filterRectCenterInner( GrayS64 src, int radiusX, int radiusY, GrayS64 dst,
											  @Nullable Object workspace, RectCenter_S64 filter ) {
		final int y0 = radiusY;
		final int y1 = src.height - radiusY;

		// Go through all inner pixels

		BoofConcurrency.loopFor(y0, y1 , y -> {
			int indexSrc = src.startIndex + y*src.stride + radiusX;
			int indexDst = dst.startIndex + y*dst.stride + radiusX;

			// index of last pixel along x-axis it should process
			int end = src.startIndex + y*src.stride + src.width - radiusX;
			while (indexSrc < end) {
				// Apply the transform. It's assumed that the src image has been passed into the lambda
				dst.data[indexDst++] = (long)filter.apply(indexSrc++, workspace);
			}
		});
	}

	public static void filterRectCenterInner( GrayF32 src, int radiusX, int radiusY, GrayF32 dst,
											  @Nullable Object workspace, RectCenter_F32 filter ) {
		final int y0 = radiusY;
		final int y1 = src.height - radiusY;

		// Go through all inner pixels

		BoofConcurrency.loopFor(y0, y1 , y -> {
			int indexSrc = src.startIndex + y*src.stride + radiusX;
			int indexDst = dst.startIndex + y*dst.stride + radiusX;

			// index of last pixel along x-axis it should process
			int end = src.startIndex + y*src.stride + src.width - radiusX;
			while (indexSrc < end) {
				// Apply the transform. It's assumed that the src image has been passed into the lambda
				dst.data[indexDst++] = (float)filter.apply(indexSrc++, workspace);
			}
		});
	}

	public static void filterRectCenterInner( GrayF64 src, int radiusX, int radiusY, GrayF64 dst,
											  @Nullable Object workspace, RectCenter_F64 filter ) {
		final int y0 = radiusY;
		final int y1 = src.height - radiusY;

		// Go through all inner pixels

		BoofConcurrency.loopFor(y0, y1 , y -> {
			int indexSrc = src.startIndex + y*src.stride + radiusX;
			int indexDst = dst.startIndex + y*dst.stride + radiusX;

			// index of last pixel along x-axis it should process
			int end = src.startIndex + y*src.stride + src.width - radiusX;
			while (indexSrc < end) {
				// Apply the transform. It's assumed that the src image has been passed into the lambda
				dst.data[indexDst++] = (double)filter.apply(indexSrc++, workspace);
			}
		});
	}

}
