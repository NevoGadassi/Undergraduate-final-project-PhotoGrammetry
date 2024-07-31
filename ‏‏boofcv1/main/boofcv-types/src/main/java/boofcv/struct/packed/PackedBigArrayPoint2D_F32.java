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

package boofcv.struct.packed;

import javax.annotation.Generated;
import boofcv.misc.BoofLambdas;
import boofcv.struct.PackedArray;
import georegression.struct.point.Point2D_F32;
import org.ddogleg.struct.BigDogArray_F32;
import org.ddogleg.struct.BigDogGrowth;

/**
 * Packed array of {@link Point2D_F32}. Internally the point is stored in an interleaved format.
 *
 * @author Peter Abeles
 */
@Generated("boofcv.struct.packed.PackedBigArrayPoint2D_F64")
public class PackedBigArrayPoint2D_F32 implements PackedArray<Point2D_F32> {
	private static final int DOF = 2;

	// Storage for the raw data in an array
	private final BigDogArray_F32 dog;

	// tuple that the result is temporarily written to
	public final Point2D_F32 temp = new Point2D_F32();

	// Number of points stored in the array
	protected int numPoints;

	/**
	 * Constructor where the default is used for all parameters.
	 */
	public PackedBigArrayPoint2D_F32() {this(10);}

	/**
	 * Constructor where the initial number of points is specified and everything else is default
	 */
	public PackedBigArrayPoint2D_F32( int reservedPoints ) {
		this(reservedPoints, 50_000, BigDogGrowth.GROW_FIRST);
	}

	/**
	 * Constructor which allows access to all array parameters
	 *
	 * @param reservedPoints Reserve space to store this number of points initially
	 * @param blockSize A single block will be able to store this number of points
	 * @param growth Growth strategy to use
	 */
	public PackedBigArrayPoint2D_F32( int reservedPoints, int blockSize, BigDogGrowth growth ) {
		dog = new BigDogArray_F32(reservedPoints*DOF, blockSize*DOF, growth);
	}

	@Override public void reset() {
		dog.reset();
		numPoints = 0;
	}

	@Override public void reserve( int numPoints ) {
		dog.reserve(numPoints*DOF);
	}

	public void append( float x, float y ) {
		dog.add(x);
		dog.add(y);

		numPoints++;
	}

	@Override public void append( Point2D_F32 element ) {
		dog.add(element.x);
		dog.add(element.y);

		numPoints++;
	}

	@Override public Point2D_F32 getTemp( int index ) {
		index *= DOF;
		float[] block = dog.getBlocks().get(index/dog.getBlockSize());
		int element = index%dog.getBlockSize();
		temp.x = block[element];
		temp.y = block[element + 1];

		return temp;
	}

	@Override public void getCopy( int index, Point2D_F32 dst ) {
		index *= DOF;
		float[] block = dog.getBlocks().get(index/dog.getBlockSize());
		int element = index%dog.getBlockSize();
		dst.x = block[element];
		dst.y = block[element + 1];
	}

	@Override public void copy( Point2D_F32 src, Point2D_F32 dst ) {
		dst.setTo(src);
	}

	@Override public int size() {
		return numPoints;
	}

	@Override public Class<Point2D_F32> getElementType() {
		return Point2D_F32.class;
	}

	@Override public void forIdx( int idx0, int idx1, BoofLambdas.ProcessIndex<Point2D_F32> op ) {
		dog.processByBlock(idx0*DOF, idx1*DOF, ( array, arrayIdx0, arrayIdx1, offset ) -> {
			int pointIndex = idx0 + offset/DOF;
			for (int i = arrayIdx0; i < arrayIdx1; i += DOF) {
				temp.x = array[i];
				temp.y = array[i + 1];
				op.process(pointIndex++, temp);
				array[i] = temp.x;
				array[i + 1] = temp.y;
			}
		});
	}
}
