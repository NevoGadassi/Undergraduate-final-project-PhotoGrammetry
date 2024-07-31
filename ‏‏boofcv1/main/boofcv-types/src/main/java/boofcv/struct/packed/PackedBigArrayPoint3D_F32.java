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

package boofcv.struct.packed;

import javax.annotation.Generated;
import boofcv.misc.BoofLambdas;
import boofcv.struct.PackedArray;
import georegression.struct.GeoTuple3D_F32;
import georegression.struct.point.Point3D_F32;
import org.ddogleg.struct.BigDogArray_F32;
import org.ddogleg.struct.BigDogGrowth;

import java.util.Collection;

/**
 * Packed array of {@link Point3D_F32}. Internally the point is stored in an interleaved format.
 *
 * @author Peter Abeles
 */
@Generated("boofcv.struct.packed.PackedBigArrayPoint3D_F64")
public class PackedBigArrayPoint3D_F32 implements PackedArray<Point3D_F32> {
	private static final int DOF = 3;

	// tuple that the result is temporarily written to
	public final Point3D_F32 temp = new Point3D_F32();

	// Storage for the raw data in an array
	private final BigDogArray_F32 dog;

	// Number of points stored in the array
	protected int size;

	/**
	 * Constructor where the default is used for all parameters.
	 */
	public PackedBigArrayPoint3D_F32() {
		this(10);
	}

	/**
	 * Constructor where the initial number of points is specified and everything else is default
	 */
	public PackedBigArrayPoint3D_F32( int reservedPoints ) {
		this(reservedPoints, 50_000, BigDogGrowth.GROW_FIRST);
	}

	/**
	 * Constructor which allows access to all array parameters
	 *
	 * @param reservedPoints Reserve space to store this number of points initially
	 * @param blockSize A single block will be able to store this number of points
	 * @param growth Growth strategy to use
	 */
	public PackedBigArrayPoint3D_F32( int reservedPoints, int blockSize, BigDogGrowth growth ) {
		dog = new BigDogArray_F32(reservedPoints*DOF, blockSize*DOF, growth);
	}

	/**
	 * Makes this array have a value identical to 'src'
	 *
	 * @param src original array being copies
	 * @return Reference to 'this'
	 */
	public PackedBigArrayPoint3D_F32 setTo( PackedBigArrayPoint3D_F32 src ) {
		reset();
		reserve(src.size);
		src.forIdx(0, src.size, ( idx, p ) -> append(p.x, p.y, p.z));
		return this;
	}

	@Override public void reset() {
		dog.reset();
		size = 0;
	}

	@Override public void reserve( int numPoints ) {
		dog.reserve(numPoints*DOF);
	}

	public void append( float x, float y, float z ) {
		dog.add(x);
		dog.add(y);
		dog.add(z);

		size++;
	}

	public void append( GeoTuple3D_F32<?> element ) {
		append(element.x, element.y, element.z);
	}

	/**
	 * Adds all the points in the collection
	 */
	public <T extends GeoTuple3D_F32<T>> void appendAll( Collection<T> collection ) {
		// Preallocate memory for all the items in the list
		reserve(collection.size());

		// Add each element in the list
		for (T p : collection) { // lint:forbidden ignore_line"
			append(p);
		}
	}

	@Override public void append( Point3D_F32 element ) {
		dog.add(element.x);
		dog.add(element.y);
		dog.add(element.z);

		size++;
	}

	@Override public Point3D_F32 getTemp( int index ) {
		index *= DOF;
		float[] block = dog.getBlocks().get(index/dog.getBlockSize());
		int element = index%dog.getBlockSize();
		temp.x = block[element];
		temp.y = block[element + 1];
		temp.z = block[element + 2];

		return temp;
	}

	@Override public void getCopy( int index, Point3D_F32 dst ) {
		getCopy(index, (GeoTuple3D_F32<?>)dst);
	}

	public void getCopy( int index, GeoTuple3D_F32<?> dst ) {
		index *= DOF;
		float[] block = dog.getBlocks().get(index/dog.getBlockSize());
		int element = index%dog.getBlockSize();
		dst.x = block[element];
		dst.y = block[element + 1];
		dst.z = block[element + 2];
	}

	@Override public void copy( Point3D_F32 src, Point3D_F32 dst ) {
		dst.setTo(src);
	}

	@Override public int size() {
		return size;
	}

	@Override public Class<Point3D_F32> getElementType() {
		return Point3D_F32.class;
	}

	@Override public void forIdx( int idx0, int idx1, BoofLambdas.ProcessIndex<Point3D_F32> op ) {
		dog.processByBlock(idx0*3, idx1*3, ( array, arrayIdx0, arrayIdx1, offset ) -> {
			int pointIndex = idx0 + offset/DOF;
			for (int i = arrayIdx0; i < arrayIdx1; i += DOF) {
				temp.x = array[i];
				temp.y = array[i + 1];
				temp.z = array[i + 2];
				op.process(pointIndex++, temp);
				array[i] = temp.x;
				array[i + 1] = temp.y;
				array[i + 2] = temp.z;
			}
		});
	}
}
