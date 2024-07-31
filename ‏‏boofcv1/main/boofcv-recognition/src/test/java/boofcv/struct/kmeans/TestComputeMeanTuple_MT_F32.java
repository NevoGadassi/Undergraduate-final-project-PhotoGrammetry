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

package boofcv.struct.kmeans;

import javax.annotation.Generated;
import boofcv.struct.feature.TupleDesc_F32;
import boofcv.testing.BoofStandardJUnit;
import org.ddogleg.clustering.misc.ListAccessor;
import org.ddogleg.struct.DogArray;
import org.ddogleg.struct.DogArray_I32;
import org.ejml.UtilEjml;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Peter Abeles
 **/
@Generated("boofcv.struct.kmeans.TestComputeMeanTuple_MT_F64")
public class TestComputeMeanTuple_MT_F32 extends BoofStandardJUnit {
	@Test void compare() {
		int DOF = 31;
		int numClusters = 4;

		// Create a list of random points
		List<TupleDesc_F32> list = new ArrayList<>();
		var assignments = new DogArray_I32(1000);
		for (int i = 0; i < 1000; i++) {
			assignments.add(rand.nextInt(numClusters));
			var t = new TupleDesc_F32(DOF);
			for (int j = 0; j < DOF; j++) {
				t.data[j] = rand.nextFloat();
			}
			list.add(t);
		}
		var points = new ListAccessor<>(list, (src,dst)->dst.setTo(src), TupleDesc_F32.class);

		var clustersSingle = new DogArray<>(()->new TupleDesc_F32(DOF));
		var clustersMulti = new DogArray<>(()->new TupleDesc_F32(DOF));
		clustersSingle.resize(numClusters);
		clustersMulti.resize(numClusters);

		var single = new ComputeMeanTuple_F32();
		var multi = new ComputeMeanTuple_MT_F32(DOF);

		single.process(points, assignments, clustersSingle);
		multi.process(points, assignments, clustersMulti);

		assertEquals(clustersSingle.size, clustersMulti.size);
		for (int i = 0; i < numClusters; i++) {
			assertArrayEquals(clustersSingle.get(i).data, clustersMulti.get(i).data, UtilEjml.TEST_F32);
		}
	}
}
