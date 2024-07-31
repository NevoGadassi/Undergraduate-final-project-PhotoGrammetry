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

package boofcv.struct.kmeans;

import javax.annotation.Generated;
import boofcv.concurrency.BoofConcurrency;
import boofcv.struct.feature.TupleDesc_F32;
import lombok.Getter;
import lombok.Setter;
import org.ddogleg.clustering.ComputeMeanClusters;
import org.ddogleg.struct.DogArray;
import org.ddogleg.struct.DogArray_I32;
import org.ddogleg.struct.FastAccess;
import org.ddogleg.struct.LArrayAccessor;
import pabeles.concurrency.GrowArray;

/**
 * Concurrent implementation of {@link ComputeMeanTuple_F32}
 *
 * @author Peter Abeles
 */
@Generated("boofcv.struct.kmeans.ComputeMeanTuple_MT_F64")
public class ComputeMeanTuple_MT_F32 extends ComputeMeanTuple_F32 {

	/**
	 * Minimum list size for it to use concurrent code. If a list is small it will run slower than the single
	 * thread version. By default this is zero since the optimal value is use case specific.
	 */
	@Getter @Setter int minimumForConcurrent = 0;

	final int tupleDof;

	GrowArray<ThreadData> threadData;

	public ComputeMeanTuple_MT_F32( int numElements) {
		tupleDof = numElements;
		threadData = new GrowArray<>(ThreadData::new);
	}

	@Override public void process( LArrayAccessor<TupleDesc_F32> points,
								   DogArray_I32 assignments,
								   FastAccess<TupleDesc_F32> clusters) {
		// see if it should run the single thread version instead
		if (points.size() < minimumForConcurrent) {
			super.process(points, assignments, clusters);
			return;
		}

		if (assignments.size != points.size())
			throw new IllegalArgumentException("Points and assignments need to be the same size");

		// Compute the sum of all points in each cluster
		BoofConcurrency.loopBlocks(0, points.size(), threadData, (data,idx0,idx1)->{
			final TupleDesc_F32 tuple = data.point;
			final DogArray<TupleDesc_F32> sums = data.clusterSums;
			sums.resize(clusters.size);
			for (int i = 0; i < sums.size; i++) {
				sums.get(i).fill(0.0f);
			}
			final DogArray_I32 counts = data.counts;
			counts.reset().resize(sums.size, 0);

			for (int pointIdx = idx0; pointIdx < idx1; pointIdx++) {
				points.getCopy(pointIdx, tuple);
				final float[] point = tuple.data;

				int clusterIdx = assignments.get(pointIdx);
				counts.data[clusterIdx]++;
				float[] cluster = sums.get(clusterIdx).data;
				for (int i = 0; i < point.length; i++) {
					cluster[i] += point[i];
				}
			}
		});

		// Stitch results from threads back together
		counts.reset().resize(clusters.size, 0);
		for (int i = 0; i < clusters.size; i++) {
			clusters.get(i).fill(0.0f);
		}
		for (int threadIdx = 0; threadIdx < threadData.size(); threadIdx++) {
			ThreadData data = threadData.get(threadIdx);
			for (int clusterIdx = 0; clusterIdx < clusters.size; clusterIdx++) {
				TupleDesc_F32 a = data.clusterSums.get(clusterIdx);
				TupleDesc_F32 b = clusters.get(clusterIdx);

				for (int i = 0; i < b.size(); i++) {
					b.data[i] += a.data[i];
				}
				counts.data[clusterIdx] += data.counts.data[clusterIdx];
			}
		}

		// Divide to get the average value in each cluster
		for (int clusterIdx = 0; clusterIdx < clusters.size; clusterIdx++) {
			float[] cluster = clusters.get(clusterIdx).data;
			float divisor = counts.get(clusterIdx);
			for (int i = 0; i < cluster.length; i++) {
				cluster[i] /= divisor;
			}
		}
	}

	@Override public ComputeMeanClusters<TupleDesc_F32> newInstanceThread() {
		return new ComputeMeanTuple_MT_F32(tupleDof);
	}

	class ThreadData {
		TupleDesc_F32 point = new TupleDesc_F32(tupleDof);
		DogArray_I32 counts = new DogArray_I32();
		DogArray<TupleDesc_F32> clusterSums = new DogArray<>(()->new TupleDesc_F32(tupleDof));
	}
}
