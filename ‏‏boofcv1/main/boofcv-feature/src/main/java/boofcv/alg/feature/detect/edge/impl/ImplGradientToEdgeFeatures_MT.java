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

package boofcv.alg.feature.detect.edge.impl;

import boofcv.concurrency.BoofConcurrency;

import javax.annotation.Generated;
import boofcv.struct.image.GrayF32;
import boofcv.struct.image.GrayS16;
import boofcv.struct.image.GrayS32;

/**
 * <p>
 * Implementations of the core algorithms of {@link boofcv.alg.feature.detect.edge.GradientToEdgeFeatures}.
 * </p>
 *
 * <p>
 * WARNING: Do not modify. Automatically generated by GenerateImplGradientToEdgeFeatures.
 * </p>
 *
 * @author Peter Abeles
 */
@SuppressWarnings("Duplicates")
@Generated("boofcv.alg.feature.detect.edge.impl.ImplGradientToEdgeFeatures")
public class ImplGradientToEdgeFeatures_MT {

	static public void intensityE( GrayF32 derivX, GrayF32 derivY, GrayF32 intensity ) {
		final int w = derivX.width;
		final int h = derivY.height;

		BoofConcurrency.loopFor(0,h,y->{
			int indexX = derivX.startIndex + y*derivX.stride;
			int indexY = derivY.startIndex + y*derivY.stride;
			int indexI = intensity.startIndex + y*intensity.stride;

			int end = indexX + w;
			for (; indexX < end; indexX++, indexY++, indexI++) {
				float dx = derivX.data[indexX];
				float dy = derivY.data[indexY];

				intensity.data[indexI] = (float)Math.sqrt(dx*dx + dy*dy);
			}
		});
	}

	static public void intensityAbs( GrayF32 derivX, GrayF32 derivY, GrayF32 intensity ) {
		final int w = derivX.width;
		final int h = derivY.height;

		BoofConcurrency.loopFor(0,h,y->{
			int indexX = derivX.startIndex + y*derivX.stride;
			int indexY = derivY.startIndex + y*derivY.stride;
			int indexI = intensity.startIndex + y*intensity.stride;

			int end = indexX + w;
			for (; indexX < end; indexX++, indexY++, indexI++) {

				intensity.data[indexI] = Math.abs(derivX.data[indexX]) + Math.abs(derivY.data[indexY]);
			}
		});
	}

	static public void direction( GrayF32 derivX, GrayF32 derivY, GrayF32 angle ) {
		final int w = derivX.width;
		final int h = derivY.height;

		BoofConcurrency.loopFor(0,h,y->{
			int indexX = derivX.startIndex + y*derivX.stride;
			int indexY = derivY.startIndex + y*derivY.stride;
			int indexA = angle.startIndex + y*angle.stride;

			int end = indexX + w;
			for (; indexX < end; indexX++, indexY++, indexA++) {
				float dx = derivX.data[indexX];
				float dy = derivY.data[indexY];

				// compute the angle while avoiding divided by zero errors
				angle.data[indexA] = Math.abs(dx) < 1e-10f ? (float)(Math.PI/2.0) : (float)Math.atan(dy/dx);
			}
		});
	}

	static public void direction2( GrayF32 derivX, GrayF32 derivY, GrayF32 angle ) {
		final int w = derivX.width;
		final int h = derivY.height;

		BoofConcurrency.loopFor(0,h,y->{
			int indexX = derivX.startIndex + y*derivX.stride;
			int indexY = derivY.startIndex + y*derivY.stride;
			int indexA = angle.startIndex + y*angle.stride;

			int end = indexX + w;
			for (; indexX < end; indexX++, indexY++, indexA++) {
				float dx = derivX.data[indexX];
				float dy = derivY.data[indexY];

				// compute the angle while avoiding divided by zero errors
				angle.data[indexA] = (float)Math.atan2(dy, dx);
			}
		});
	}

	static public void intensityE( GrayS16 derivX, GrayS16 derivY, GrayF32 intensity ) {
		final int w = derivX.width;
		final int h = derivY.height;

		BoofConcurrency.loopFor(0,h,y->{
			int indexX = derivX.startIndex + y*derivX.stride;
			int indexY = derivY.startIndex + y*derivY.stride;
			int indexI = intensity.startIndex + y*intensity.stride;

			int end = indexX + w;
			for (; indexX < end; indexX++, indexY++, indexI++) {
				int dx = derivX.data[indexX];
				int dy = derivY.data[indexY];

				intensity.data[indexI] = (float)Math.sqrt(dx*dx + dy*dy);
			}
		});
	}

	static public void intensityAbs( GrayS16 derivX, GrayS16 derivY, GrayF32 intensity ) {
		final int w = derivX.width;
		final int h = derivY.height;

		BoofConcurrency.loopFor(0,h,y->{
			int indexX = derivX.startIndex + y*derivX.stride;
			int indexY = derivY.startIndex + y*derivY.stride;
			int indexI = intensity.startIndex + y*intensity.stride;

			int end = indexX + w;
			for (; indexX < end; indexX++, indexY++, indexI++) {

				intensity.data[indexI] = Math.abs(derivX.data[indexX]) + Math.abs(derivY.data[indexY]);
			}
		});
	}

	static public void direction( GrayS16 derivX, GrayS16 derivY, GrayF32 angle ) {
		final int w = derivX.width;
		final int h = derivY.height;

		BoofConcurrency.loopFor(0,h,y->{
			int indexX = derivX.startIndex + y*derivX.stride;
			int indexY = derivY.startIndex + y*derivY.stride;
			int indexA = angle.startIndex + y*angle.stride;

			int end = indexX + w;
			for (; indexX < end; indexX++, indexY++, indexA++) {
				int dx = derivX.data[indexX];
				int dy = derivY.data[indexY];

				// compute the angle while avoiding divided by zero errors
				angle.data[indexA] = dx == 0 ? (float)(Math.PI/2.0) : (float)Math.atan((double)dy/(double)dx);
			}
		});
	}

	static public void direction2( GrayS16 derivX, GrayS16 derivY, GrayF32 angle ) {
		final int w = derivX.width;
		final int h = derivY.height;

		BoofConcurrency.loopFor(0,h,y->{
			int indexX = derivX.startIndex + y*derivX.stride;
			int indexY = derivY.startIndex + y*derivY.stride;
			int indexA = angle.startIndex + y*angle.stride;

			int end = indexX + w;
			for (; indexX < end; indexX++, indexY++, indexA++) {
				int dx = derivX.data[indexX];
				int dy = derivY.data[indexY];

				// compute the angle while avoiding divided by zero errors
				angle.data[indexA] = (float)Math.atan2(dy, dx);
			}
		});
	}

	static public void intensityE( GrayS32 derivX, GrayS32 derivY, GrayF32 intensity ) {
		final int w = derivX.width;
		final int h = derivY.height;

		BoofConcurrency.loopFor(0,h,y->{
			int indexX = derivX.startIndex + y*derivX.stride;
			int indexY = derivY.startIndex + y*derivY.stride;
			int indexI = intensity.startIndex + y*intensity.stride;

			int end = indexX + w;
			for (; indexX < end; indexX++, indexY++, indexI++) {
				int dx = derivX.data[indexX];
				int dy = derivY.data[indexY];

				intensity.data[indexI] = (float)Math.sqrt(dx*dx + dy*dy);
			}
		});
	}

	static public void intensityAbs( GrayS32 derivX, GrayS32 derivY, GrayF32 intensity ) {
		final int w = derivX.width;
		final int h = derivY.height;

		BoofConcurrency.loopFor(0,h,y->{
			int indexX = derivX.startIndex + y*derivX.stride;
			int indexY = derivY.startIndex + y*derivY.stride;
			int indexI = intensity.startIndex + y*intensity.stride;

			int end = indexX + w;
			for (; indexX < end; indexX++, indexY++, indexI++) {

				intensity.data[indexI] = Math.abs(derivX.data[indexX]) + Math.abs(derivY.data[indexY]);
			}
		});
	}

	static public void direction( GrayS32 derivX, GrayS32 derivY, GrayF32 angle ) {
		final int w = derivX.width;
		final int h = derivY.height;

		BoofConcurrency.loopFor(0,h,y->{
			int indexX = derivX.startIndex + y*derivX.stride;
			int indexY = derivY.startIndex + y*derivY.stride;
			int indexA = angle.startIndex + y*angle.stride;

			int end = indexX + w;
			for (; indexX < end; indexX++, indexY++, indexA++) {
				int dx = derivX.data[indexX];
				int dy = derivY.data[indexY];

				// compute the angle while avoiding divided by zero errors
				angle.data[indexA] = dx == 0 ? (float)(Math.PI/2.0) : (float)Math.atan((double)dy/(double)dx);
			}
		});
	}

	static public void direction2( GrayS32 derivX, GrayS32 derivY, GrayF32 angle ) {
		final int w = derivX.width;
		final int h = derivY.height;

		BoofConcurrency.loopFor(0,h,y->{
			int indexX = derivX.startIndex + y*derivX.stride;
			int indexY = derivY.startIndex + y*derivY.stride;
			int indexA = angle.startIndex + y*angle.stride;

			int end = indexX + w;
			for (; indexX < end; indexX++, indexY++, indexA++) {
				int dx = derivX.data[indexX];
				int dy = derivY.data[indexY];

				// compute the angle while avoiding divided by zero errors
				angle.data[indexA] = (float)Math.atan2(dy, dx);
			}
		});
	}
}
