/*
 * Copyright (c) 2024, Peter Abeles. All Rights Reserved.
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

package boofcv.alg.misc.impl;

import boofcv.struct.image.*;

import javax.annotation.Generated;
import boofcv.alg.misc.PixelMathLambdas.*;

import boofcv.concurrency.BoofConcurrency;

/**
 * Implementation of algorithms in PixelMath
 *
 * <p>DO NOT MODIFY. Automatically generated code created by GenerateImplPixelMath</p>
 *
 * @author Peter Abeles
 */
@SuppressWarnings("Duplicates")
@Generated("boofcv.alg.misc.impl.ImplPixelMath")
public class ImplPixelMath_MT {

	public static void operator1( byte[] input, int inputStart, int inputStride,
							   byte[] output, int outputStart, int outputStride,
							   int rows, int cols,
							   Function1_I8 function ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				output[indexDst] = function.process(input[indexSrc]);
			}
		});
	}

	public static void operator1( short[] input, int inputStart, int inputStride,
							   short[] output, int outputStart, int outputStride,
							   int rows, int cols,
							   Function1_I16 function ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				output[indexDst] = function.process(input[indexSrc]);
			}
		});
	}

	public static void operator1( int[] input, int inputStart, int inputStride,
							   int[] output, int outputStart, int outputStride,
							   int rows, int cols,
							   Function1_S32 function ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				output[indexDst] = function.process(input[indexSrc]);
			}
		});
	}

	public static void operator1( long[] input, int inputStart, int inputStride,
							   long[] output, int outputStart, int outputStride,
							   int rows, int cols,
							   Function1_S64 function ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				output[indexDst] = function.process(input[indexSrc]);
			}
		});
	}

	public static void operator1( float[] input, int inputStart, int inputStride,
							   float[] output, int outputStart, int outputStride,
							   int rows, int cols,
							   Function1_F32 function ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				output[indexDst] = function.process(input[indexSrc]);
			}
		});
	}

	public static void operator1( double[] input, int inputStart, int inputStride,
							   double[] output, int outputStart, int outputStride,
							   int rows, int cols,
							   Function1_F64 function ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				output[indexDst] = function.process(input[indexSrc]);
			}
		});
	}

	public static void operator2( byte[] inputA, int inputStartA, int inputStrideA,
							   byte[] inputB, int inputStartB, int inputStrideB,
							   byte[] output, int outputStart, int outputStride,
							   int rows, int cols,
							   Function2_I8 function ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexA = inputStartA + y*inputStrideA;
			int indexB = inputStartB + y*inputStrideB;
			int indexDst = outputStart + y*outputStride;
			int end = indexA + cols;

			for (; indexA < end; indexA++, indexB++, indexDst++) {
				output[indexDst] = function.process(inputA[indexA],inputB[indexB]);
			}
		});
	}

	public static void operator2( short[] inputA, int inputStartA, int inputStrideA,
							   short[] inputB, int inputStartB, int inputStrideB,
							   short[] output, int outputStart, int outputStride,
							   int rows, int cols,
							   Function2_I16 function ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexA = inputStartA + y*inputStrideA;
			int indexB = inputStartB + y*inputStrideB;
			int indexDst = outputStart + y*outputStride;
			int end = indexA + cols;

			for (; indexA < end; indexA++, indexB++, indexDst++) {
				output[indexDst] = function.process(inputA[indexA],inputB[indexB]);
			}
		});
	}

	public static void operator2( int[] inputA, int inputStartA, int inputStrideA,
							   int[] inputB, int inputStartB, int inputStrideB,
							   int[] output, int outputStart, int outputStride,
							   int rows, int cols,
							   Function2_S32 function ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexA = inputStartA + y*inputStrideA;
			int indexB = inputStartB + y*inputStrideB;
			int indexDst = outputStart + y*outputStride;
			int end = indexA + cols;

			for (; indexA < end; indexA++, indexB++, indexDst++) {
				output[indexDst] = function.process(inputA[indexA],inputB[indexB]);
			}
		});
	}

	public static void operator2( long[] inputA, int inputStartA, int inputStrideA,
							   long[] inputB, int inputStartB, int inputStrideB,
							   long[] output, int outputStart, int outputStride,
							   int rows, int cols,
							   Function2_S64 function ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexA = inputStartA + y*inputStrideA;
			int indexB = inputStartB + y*inputStrideB;
			int indexDst = outputStart + y*outputStride;
			int end = indexA + cols;

			for (; indexA < end; indexA++, indexB++, indexDst++) {
				output[indexDst] = function.process(inputA[indexA],inputB[indexB]);
			}
		});
	}

	public static void operator2( float[] inputA, int inputStartA, int inputStrideA,
							   float[] inputB, int inputStartB, int inputStrideB,
							   float[] output, int outputStart, int outputStride,
							   int rows, int cols,
							   Function2_F32 function ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexA = inputStartA + y*inputStrideA;
			int indexB = inputStartB + y*inputStrideB;
			int indexDst = outputStart + y*outputStride;
			int end = indexA + cols;

			for (; indexA < end; indexA++, indexB++, indexDst++) {
				output[indexDst] = function.process(inputA[indexA],inputB[indexB]);
			}
		});
	}

	public static void operator2( double[] inputA, int inputStartA, int inputStrideA,
							   double[] inputB, int inputStartB, int inputStrideB,
							   double[] output, int outputStart, int outputStride,
							   int rows, int cols,
							   Function2_F64 function ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexA = inputStartA + y*inputStrideA;
			int indexB = inputStartB + y*inputStrideB;
			int indexDst = outputStart + y*outputStride;
			int end = indexA + cols;

			for (; indexA < end; indexA++, indexB++, indexDst++) {
				output[indexDst] = function.process(inputA[indexA],inputB[indexB]);
			}
		});
	}

	public static void abs( byte[] input, int inputStart, int inputStride,
							   byte[] output, int outputStart, int outputStride,
							   int rows, int cols ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				output[indexDst] = (byte)Math.abs(input[indexSrc]);
			}
		});
	}

	public static void abs( short[] input, int inputStart, int inputStride,
							   short[] output, int outputStart, int outputStride,
							   int rows, int cols ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				output[indexDst] = (short)Math.abs(input[indexSrc]);
			}
		});
	}

	public static void abs( int[] input, int inputStart, int inputStride,
							   int[] output, int outputStart, int outputStride,
							   int rows, int cols ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				output[indexDst] = (int)Math.abs(input[indexSrc]);
			}
		});
	}

	public static void abs( long[] input, int inputStart, int inputStride,
							   long[] output, int outputStart, int outputStride,
							   int rows, int cols ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				output[indexDst] = (long)Math.abs(input[indexSrc]);
			}
		});
	}

	public static void abs( float[] input, int inputStart, int inputStride,
							   float[] output, int outputStart, int outputStride,
							   int rows, int cols ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				output[indexDst] = (float)Math.abs(input[indexSrc]);
			}
		});
	}

	public static void abs( double[] input, int inputStart, int inputStride,
							   double[] output, int outputStart, int outputStride,
							   int rows, int cols ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				output[indexDst] = (double)Math.abs(input[indexSrc]);
			}
		});
	}

	public static void negative( byte[] input, int inputStart, int inputStride,
							   byte[] output, int outputStart, int outputStride,
							   int rows, int cols ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				output[indexDst] = (byte)-input[indexSrc];
			}
		});
	}

	public static void negative( short[] input, int inputStart, int inputStride,
							   short[] output, int outputStart, int outputStride,
							   int rows, int cols ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				output[indexDst] = (short)-input[indexSrc];
			}
		});
	}

	public static void negative( int[] input, int inputStart, int inputStride,
							   int[] output, int outputStart, int outputStride,
							   int rows, int cols ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				output[indexDst] = (int)-input[indexSrc];
			}
		});
	}

	public static void negative( long[] input, int inputStart, int inputStride,
							   long[] output, int outputStart, int outputStride,
							   int rows, int cols ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				output[indexDst] = (long)-input[indexSrc];
			}
		});
	}

	public static void negative( float[] input, int inputStart, int inputStride,
							   float[] output, int outputStart, int outputStride,
							   int rows, int cols ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				output[indexDst] = (float)-input[indexSrc];
			}
		});
	}

	public static void negative( double[] input, int inputStart, int inputStride,
							   double[] output, int outputStart, int outputStride,
							   int rows, int cols ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				output[indexDst] = (double)-input[indexSrc];
			}
		});
	}

	public static void multiplyU_A( byte[] input, int inputStart, int inputStride, 
							   double value,
							   byte[] output, int outputStart, int outputStride,
							   int rows, int cols ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				output[indexDst] = (byte)Math.round((input[indexSrc] & 0xFF) * value);
			}
		});
	}

	public static void multiply_A( byte[] input, int inputStart, int inputStride, 
							   double value,
							   byte[] output, int outputStart, int outputStride,
							   int rows, int cols ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				output[indexDst] = (byte)Math.round((input[indexSrc] ) * value);
			}
		});
	}

	public static void multiplyU_A( short[] input, int inputStart, int inputStride, 
							   double value,
							   short[] output, int outputStart, int outputStride,
							   int rows, int cols ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				output[indexDst] = (short)Math.round((input[indexSrc] & 0xFFFF) * value);
			}
		});
	}

	public static void multiply_A( short[] input, int inputStart, int inputStride, 
							   double value,
							   short[] output, int outputStart, int outputStride,
							   int rows, int cols ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				output[indexDst] = (short)Math.round((input[indexSrc] ) * value);
			}
		});
	}

	public static void multiply_A( int[] input, int inputStart, int inputStride, 
							   double value,
							   int[] output, int outputStart, int outputStride,
							   int rows, int cols ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				output[indexDst] = (int)Math.round((input[indexSrc] ) * value);
			}
		});
	}

	public static void multiply_A( long[] input, int inputStart, int inputStride, 
							   double value,
							   long[] output, int outputStart, int outputStride,
							   int rows, int cols ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				output[indexDst] = (long)Math.round((input[indexSrc] ) * value);
			}
		});
	}

	public static void multiply_A( float[] input, int inputStart, int inputStride, 
							   float value,
							   float[] output, int outputStart, int outputStride,
							   int rows, int cols ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				output[indexDst] = ((input[indexSrc] ) * value);
			}
		});
	}

	public static void multiply_A( double[] input, int inputStart, int inputStride, 
							   double value,
							   double[] output, int outputStart, int outputStride,
							   int rows, int cols ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				output[indexDst] = ((input[indexSrc] ) * value);
			}
		});
	}

	public static void multiplyU_A( byte[] input, int inputStart, int inputStride,
							   double value, int lower, int upper,
							   byte[] output, int outputStart, int outputStride,
							   int rows, int cols ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				int val = (int)Math.round((input[indexSrc] & 0xFF) * value);
				if( val < lower ) val = lower;
				if( val > upper ) val = upper;
				output[indexDst] = (byte)val;
			}
		});
	}

	public static void multiply_A( byte[] input, int inputStart, int inputStride,
							   double value, int lower, int upper,
							   byte[] output, int outputStart, int outputStride,
							   int rows, int cols ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				int val = (int)Math.round((input[indexSrc] ) * value);
				if( val < lower ) val = lower;
				if( val > upper ) val = upper;
				output[indexDst] = (byte)val;
			}
		});
	}

	public static void multiplyU_A( short[] input, int inputStart, int inputStride,
							   double value, int lower, int upper,
							   short[] output, int outputStart, int outputStride,
							   int rows, int cols ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				int val = (int)Math.round((input[indexSrc] & 0xFFFF) * value);
				if( val < lower ) val = lower;
				if( val > upper ) val = upper;
				output[indexDst] = (short)val;
			}
		});
	}

	public static void multiply_A( short[] input, int inputStart, int inputStride,
							   double value, int lower, int upper,
							   short[] output, int outputStart, int outputStride,
							   int rows, int cols ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				int val = (int)Math.round((input[indexSrc] ) * value);
				if( val < lower ) val = lower;
				if( val > upper ) val = upper;
				output[indexDst] = (short)val;
			}
		});
	}

	public static void multiply_A( int[] input, int inputStart, int inputStride,
							   double value, int lower, int upper,
							   int[] output, int outputStart, int outputStride,
							   int rows, int cols ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				int val = (int)Math.round((input[indexSrc] ) * value);
				if( val < lower ) val = lower;
				if( val > upper ) val = upper;
				output[indexDst] = val;
			}
		});
	}

	public static void multiply_A( long[] input, int inputStart, int inputStride,
							   double value, long lower, long upper,
							   long[] output, int outputStart, int outputStride,
							   int rows, int cols ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				long val = (long)Math.round((input[indexSrc] ) * value);
				if( val < lower ) val = lower;
				if( val > upper ) val = upper;
				output[indexDst] = val;
			}
		});
	}

	public static void multiply_A( float[] input, int inputStart, int inputStride,
							   float value, float lower, float upper,
							   float[] output, int outputStart, int outputStride,
							   int rows, int cols ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				float val = ((input[indexSrc] ) * value);
				if( val < lower ) val = lower;
				if( val > upper ) val = upper;
				output[indexDst] = val;
			}
		});
	}

	public static void multiply_A( double[] input, int inputStart, int inputStride,
							   double value, double lower, double upper,
							   double[] output, int outputStart, int outputStride,
							   int rows, int cols ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				double val = ((input[indexSrc] ) * value);
				if( val < lower ) val = lower;
				if( val > upper ) val = upper;
				output[indexDst] = val;
			}
		});
	}

	public static void multiplyU_A( byte[] input, int inputStart, int inputStride, 
							   float value,
							   float[] output, int outputStart, int outputStride,
							   int rows, int cols ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				output[indexDst] = ((input[indexSrc] & 0xFF) * value);
			}
		});
	}

	public static void multiply_A( byte[] input, int inputStart, int inputStride, 
							   float value,
							   float[] output, int outputStart, int outputStride,
							   int rows, int cols ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				output[indexDst] = ((input[indexSrc] ) * value);
			}
		});
	}

	public static void multiplyU_A( short[] input, int inputStart, int inputStride, 
							   float value,
							   float[] output, int outputStart, int outputStride,
							   int rows, int cols ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				output[indexDst] = ((input[indexSrc] & 0xFFFF) * value);
			}
		});
	}

	public static void multiply_A( short[] input, int inputStart, int inputStride, 
							   float value,
							   float[] output, int outputStart, int outputStride,
							   int rows, int cols ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				output[indexDst] = ((input[indexSrc] ) * value);
			}
		});
	}

	public static void multiply_A( int[] input, int inputStart, int inputStride, 
							   float value,
							   float[] output, int outputStart, int outputStride,
							   int rows, int cols ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				output[indexDst] = ((input[indexSrc] ) * value);
			}
		});
	}

	public static void multiply_A( long[] input, int inputStart, int inputStride, 
							   float value,
							   float[] output, int outputStart, int outputStride,
							   int rows, int cols ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				output[indexDst] = ((input[indexSrc] ) * value);
			}
		});
	}

	public static void divideU_A( byte[] input, int inputStart, int inputStride, 
							   double denominator,
							   byte[] output, int outputStart, int outputStride,
							   int rows, int cols ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				output[indexDst] = (byte)Math.round((input[indexSrc] & 0xFF) / denominator);
			}
		});
	}

	public static void divide_A( byte[] input, int inputStart, int inputStride, 
							   double denominator,
							   byte[] output, int outputStart, int outputStride,
							   int rows, int cols ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				output[indexDst] = (byte)Math.round((input[indexSrc] ) / denominator);
			}
		});
	}

	public static void divideU_A( short[] input, int inputStart, int inputStride, 
							   double denominator,
							   short[] output, int outputStart, int outputStride,
							   int rows, int cols ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				output[indexDst] = (short)Math.round((input[indexSrc] & 0xFFFF) / denominator);
			}
		});
	}

	public static void divide_A( short[] input, int inputStart, int inputStride, 
							   double denominator,
							   short[] output, int outputStart, int outputStride,
							   int rows, int cols ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				output[indexDst] = (short)Math.round((input[indexSrc] ) / denominator);
			}
		});
	}

	public static void divide_A( int[] input, int inputStart, int inputStride, 
							   double denominator,
							   int[] output, int outputStart, int outputStride,
							   int rows, int cols ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				output[indexDst] = (int)Math.round((input[indexSrc] ) / denominator);
			}
		});
	}

	public static void divide_A( long[] input, int inputStart, int inputStride, 
							   double denominator,
							   long[] output, int outputStart, int outputStride,
							   int rows, int cols ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				output[indexDst] = (long)Math.round((input[indexSrc] ) / denominator);
			}
		});
	}

	public static void divide_A( float[] input, int inputStart, int inputStride, 
							   float denominator,
							   float[] output, int outputStart, int outputStride,
							   int rows, int cols ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				output[indexDst] = ((input[indexSrc] ) / denominator);
			}
		});
	}

	public static void divide_A( double[] input, int inputStart, int inputStride, 
							   double denominator,
							   double[] output, int outputStart, int outputStride,
							   int rows, int cols ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				output[indexDst] = ((input[indexSrc] ) / denominator);
			}
		});
	}

	public static void divideU_A( byte[] input, int inputStart, int inputStride,
							   double denominator, int lower, int upper,
							   byte[] output, int outputStart, int outputStride,
							   int rows, int cols ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				int val = (int)Math.round((input[indexSrc] & 0xFF) / denominator);
				if( val < lower ) val = lower;
				if( val > upper ) val = upper;
				output[indexDst] = (byte)val;
			}
		});
	}

	public static void divide_A( byte[] input, int inputStart, int inputStride,
							   double denominator, int lower, int upper,
							   byte[] output, int outputStart, int outputStride,
							   int rows, int cols ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				int val = (int)Math.round((input[indexSrc] ) / denominator);
				if( val < lower ) val = lower;
				if( val > upper ) val = upper;
				output[indexDst] = (byte)val;
			}
		});
	}

	public static void divideU_A( short[] input, int inputStart, int inputStride,
							   double denominator, int lower, int upper,
							   short[] output, int outputStart, int outputStride,
							   int rows, int cols ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				int val = (int)Math.round((input[indexSrc] & 0xFFFF) / denominator);
				if( val < lower ) val = lower;
				if( val > upper ) val = upper;
				output[indexDst] = (short)val;
			}
		});
	}

	public static void divide_A( short[] input, int inputStart, int inputStride,
							   double denominator, int lower, int upper,
							   short[] output, int outputStart, int outputStride,
							   int rows, int cols ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				int val = (int)Math.round((input[indexSrc] ) / denominator);
				if( val < lower ) val = lower;
				if( val > upper ) val = upper;
				output[indexDst] = (short)val;
			}
		});
	}

	public static void divide_A( int[] input, int inputStart, int inputStride,
							   double denominator, int lower, int upper,
							   int[] output, int outputStart, int outputStride,
							   int rows, int cols ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				int val = (int)Math.round((input[indexSrc] ) / denominator);
				if( val < lower ) val = lower;
				if( val > upper ) val = upper;
				output[indexDst] = val;
			}
		});
	}

	public static void divide_A( long[] input, int inputStart, int inputStride,
							   double denominator, long lower, long upper,
							   long[] output, int outputStart, int outputStride,
							   int rows, int cols ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				long val = (long)Math.round((input[indexSrc] ) / denominator);
				if( val < lower ) val = lower;
				if( val > upper ) val = upper;
				output[indexDst] = val;
			}
		});
	}

	public static void divide_A( float[] input, int inputStart, int inputStride,
							   float denominator, float lower, float upper,
							   float[] output, int outputStart, int outputStride,
							   int rows, int cols ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				float val = ((input[indexSrc] ) / denominator);
				if( val < lower ) val = lower;
				if( val > upper ) val = upper;
				output[indexDst] = val;
			}
		});
	}

	public static void divide_A( double[] input, int inputStart, int inputStride,
							   double denominator, double lower, double upper,
							   double[] output, int outputStart, int outputStride,
							   int rows, int cols ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				double val = ((input[indexSrc] ) / denominator);
				if( val < lower ) val = lower;
				if( val > upper ) val = upper;
				output[indexDst] = val;
			}
		});
	}

	public static void divideU_A( byte[] input, int inputStart, int inputStride, 
							   float denominator,
							   float[] output, int outputStart, int outputStride,
							   int rows, int cols ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				output[indexDst] = ((input[indexSrc] & 0xFF) / denominator);
			}
		});
	}

	public static void divide_A( byte[] input, int inputStart, int inputStride, 
							   float denominator,
							   float[] output, int outputStart, int outputStride,
							   int rows, int cols ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				output[indexDst] = ((input[indexSrc] ) / denominator);
			}
		});
	}

	public static void divideU_A( short[] input, int inputStart, int inputStride, 
							   float denominator,
							   float[] output, int outputStart, int outputStride,
							   int rows, int cols ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				output[indexDst] = ((input[indexSrc] & 0xFFFF) / denominator);
			}
		});
	}

	public static void divide_A( short[] input, int inputStart, int inputStride, 
							   float denominator,
							   float[] output, int outputStart, int outputStride,
							   int rows, int cols ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				output[indexDst] = ((input[indexSrc] ) / denominator);
			}
		});
	}

	public static void divide_A( int[] input, int inputStart, int inputStride, 
							   float denominator,
							   float[] output, int outputStart, int outputStride,
							   int rows, int cols ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				output[indexDst] = ((input[indexSrc] ) / denominator);
			}
		});
	}

	public static void divide_A( long[] input, int inputStart, int inputStride, 
							   float denominator,
							   float[] output, int outputStart, int outputStride,
							   int rows, int cols ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				output[indexDst] = ((input[indexSrc] ) / denominator);
			}
		});
	}

	public static void plusU_A( byte[] input, int inputStart, int inputStride, 
							   int value,
							   byte[] output, int outputStart, int outputStride,
							   int rows, int cols ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				output[indexDst] = (byte)((input[indexSrc] & 0xFF) + value);
			}
		});
	}

	public static void plus_A( byte[] input, int inputStart, int inputStride, 
							   int value,
							   byte[] output, int outputStart, int outputStride,
							   int rows, int cols ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				output[indexDst] = (byte)((input[indexSrc] ) + value);
			}
		});
	}

	public static void plusU_A( short[] input, int inputStart, int inputStride, 
							   int value,
							   short[] output, int outputStart, int outputStride,
							   int rows, int cols ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				output[indexDst] = (short)((input[indexSrc] & 0xFFFF) + value);
			}
		});
	}

	public static void plus_A( short[] input, int inputStart, int inputStride, 
							   int value,
							   short[] output, int outputStart, int outputStride,
							   int rows, int cols ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				output[indexDst] = (short)((input[indexSrc] ) + value);
			}
		});
	}

	public static void plus_A( int[] input, int inputStart, int inputStride, 
							   int value,
							   int[] output, int outputStart, int outputStride,
							   int rows, int cols ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				output[indexDst] = ((input[indexSrc] ) + value);
			}
		});
	}

	public static void plus_A( long[] input, int inputStart, int inputStride, 
							   long value,
							   long[] output, int outputStart, int outputStride,
							   int rows, int cols ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				output[indexDst] = ((input[indexSrc] ) + value);
			}
		});
	}

	public static void plus_A( float[] input, int inputStart, int inputStride, 
							   float value,
							   float[] output, int outputStart, int outputStride,
							   int rows, int cols ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				output[indexDst] = ((input[indexSrc] ) + value);
			}
		});
	}

	public static void plus_A( double[] input, int inputStart, int inputStride, 
							   double value,
							   double[] output, int outputStart, int outputStride,
							   int rows, int cols ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				output[indexDst] = ((input[indexSrc] ) + value);
			}
		});
	}

	public static void plusU_A( byte[] input, int inputStart, int inputStride,
							   int value, int lower, int upper,
							   byte[] output, int outputStart, int outputStride,
							   int rows, int cols ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				int val = ((input[indexSrc] & 0xFF) + value);
				if( val < lower ) val = lower;
				if( val > upper ) val = upper;
				output[indexDst] = (byte)val;
			}
		});
	}

	public static void plus_A( byte[] input, int inputStart, int inputStride,
							   int value, int lower, int upper,
							   byte[] output, int outputStart, int outputStride,
							   int rows, int cols ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				int val = ((input[indexSrc] ) + value);
				if( val < lower ) val = lower;
				if( val > upper ) val = upper;
				output[indexDst] = (byte)val;
			}
		});
	}

	public static void plusU_A( short[] input, int inputStart, int inputStride,
							   int value, int lower, int upper,
							   short[] output, int outputStart, int outputStride,
							   int rows, int cols ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				int val = ((input[indexSrc] & 0xFFFF) + value);
				if( val < lower ) val = lower;
				if( val > upper ) val = upper;
				output[indexDst] = (short)val;
			}
		});
	}

	public static void plus_A( short[] input, int inputStart, int inputStride,
							   int value, int lower, int upper,
							   short[] output, int outputStart, int outputStride,
							   int rows, int cols ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				int val = ((input[indexSrc] ) + value);
				if( val < lower ) val = lower;
				if( val > upper ) val = upper;
				output[indexDst] = (short)val;
			}
		});
	}

	public static void plus_A( int[] input, int inputStart, int inputStride,
							   int value, int lower, int upper,
							   int[] output, int outputStart, int outputStride,
							   int rows, int cols ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				int val = ((input[indexSrc] ) + value);
				if( val < lower ) val = lower;
				if( val > upper ) val = upper;
				output[indexDst] = val;
			}
		});
	}

	public static void plus_A( long[] input, int inputStart, int inputStride,
							   long value, long lower, long upper,
							   long[] output, int outputStart, int outputStride,
							   int rows, int cols ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				long val = ((input[indexSrc] ) + value);
				if( val < lower ) val = lower;
				if( val > upper ) val = upper;
				output[indexDst] = val;
			}
		});
	}

	public static void plus_A( float[] input, int inputStart, int inputStride,
							   float value, float lower, float upper,
							   float[] output, int outputStart, int outputStride,
							   int rows, int cols ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				float val = ((input[indexSrc] ) + value);
				if( val < lower ) val = lower;
				if( val > upper ) val = upper;
				output[indexDst] = val;
			}
		});
	}

	public static void plus_A( double[] input, int inputStart, int inputStride,
							   double value, double lower, double upper,
							   double[] output, int outputStart, int outputStride,
							   int rows, int cols ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				double val = ((input[indexSrc] ) + value);
				if( val < lower ) val = lower;
				if( val > upper ) val = upper;
				output[indexDst] = val;
			}
		});
	}

	public static void plusU_A( byte[] input, int inputStart, int inputStride, 
							   float value,
							   float[] output, int outputStart, int outputStride,
							   int rows, int cols ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				output[indexDst] = ((input[indexSrc] & 0xFF) + value);
			}
		});
	}

	public static void plus_A( byte[] input, int inputStart, int inputStride, 
							   float value,
							   float[] output, int outputStart, int outputStride,
							   int rows, int cols ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				output[indexDst] = ((input[indexSrc] ) + value);
			}
		});
	}

	public static void plusU_A( short[] input, int inputStart, int inputStride, 
							   float value,
							   float[] output, int outputStart, int outputStride,
							   int rows, int cols ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				output[indexDst] = ((input[indexSrc] & 0xFFFF) + value);
			}
		});
	}

	public static void plus_A( short[] input, int inputStart, int inputStride, 
							   float value,
							   float[] output, int outputStart, int outputStride,
							   int rows, int cols ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				output[indexDst] = ((input[indexSrc] ) + value);
			}
		});
	}

	public static void plus_A( int[] input, int inputStart, int inputStride, 
							   float value,
							   float[] output, int outputStart, int outputStride,
							   int rows, int cols ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				output[indexDst] = ((input[indexSrc] ) + value);
			}
		});
	}

	public static void plus_A( long[] input, int inputStart, int inputStride, 
							   float value,
							   float[] output, int outputStart, int outputStride,
							   int rows, int cols ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				output[indexDst] = ((input[indexSrc] ) + value);
			}
		});
	}

	public static void minusU_A( byte[] input, int inputStart, int inputStride, 
							   int value,
							   byte[] output, int outputStart, int outputStride,
							   int rows, int cols ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				output[indexDst] = (byte)((input[indexSrc] & 0xFF) - value);
			}
		});
	}

	public static void minus_A( byte[] input, int inputStart, int inputStride, 
							   int value,
							   byte[] output, int outputStart, int outputStride,
							   int rows, int cols ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				output[indexDst] = (byte)((input[indexSrc] ) - value);
			}
		});
	}

	public static void minusU_A( short[] input, int inputStart, int inputStride, 
							   int value,
							   short[] output, int outputStart, int outputStride,
							   int rows, int cols ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				output[indexDst] = (short)((input[indexSrc] & 0xFFFF) - value);
			}
		});
	}

	public static void minus_A( short[] input, int inputStart, int inputStride, 
							   int value,
							   short[] output, int outputStart, int outputStride,
							   int rows, int cols ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				output[indexDst] = (short)((input[indexSrc] ) - value);
			}
		});
	}

	public static void minus_A( int[] input, int inputStart, int inputStride, 
							   int value,
							   int[] output, int outputStart, int outputStride,
							   int rows, int cols ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				output[indexDst] = ((input[indexSrc] ) - value);
			}
		});
	}

	public static void minus_A( long[] input, int inputStart, int inputStride, 
							   long value,
							   long[] output, int outputStart, int outputStride,
							   int rows, int cols ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				output[indexDst] = ((input[indexSrc] ) - value);
			}
		});
	}

	public static void minus_A( float[] input, int inputStart, int inputStride, 
							   float value,
							   float[] output, int outputStart, int outputStride,
							   int rows, int cols ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				output[indexDst] = ((input[indexSrc] ) - value);
			}
		});
	}

	public static void minus_A( double[] input, int inputStart, int inputStride, 
							   double value,
							   double[] output, int outputStart, int outputStride,
							   int rows, int cols ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				output[indexDst] = ((input[indexSrc] ) - value);
			}
		});
	}

	public static void minusU_A( byte[] input, int inputStart, int inputStride,
							   int value, int lower, int upper,
							   byte[] output, int outputStart, int outputStride,
							   int rows, int cols ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				int val = ((input[indexSrc] & 0xFF) - value);
				if( val < lower ) val = lower;
				if( val > upper ) val = upper;
				output[indexDst] = (byte)val;
			}
		});
	}

	public static void minus_A( byte[] input, int inputStart, int inputStride,
							   int value, int lower, int upper,
							   byte[] output, int outputStart, int outputStride,
							   int rows, int cols ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				int val = ((input[indexSrc] ) - value);
				if( val < lower ) val = lower;
				if( val > upper ) val = upper;
				output[indexDst] = (byte)val;
			}
		});
	}

	public static void minusU_A( short[] input, int inputStart, int inputStride,
							   int value, int lower, int upper,
							   short[] output, int outputStart, int outputStride,
							   int rows, int cols ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				int val = ((input[indexSrc] & 0xFFFF) - value);
				if( val < lower ) val = lower;
				if( val > upper ) val = upper;
				output[indexDst] = (short)val;
			}
		});
	}

	public static void minus_A( short[] input, int inputStart, int inputStride,
							   int value, int lower, int upper,
							   short[] output, int outputStart, int outputStride,
							   int rows, int cols ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				int val = ((input[indexSrc] ) - value);
				if( val < lower ) val = lower;
				if( val > upper ) val = upper;
				output[indexDst] = (short)val;
			}
		});
	}

	public static void minus_A( int[] input, int inputStart, int inputStride,
							   int value, int lower, int upper,
							   int[] output, int outputStart, int outputStride,
							   int rows, int cols ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				int val = ((input[indexSrc] ) - value);
				if( val < lower ) val = lower;
				if( val > upper ) val = upper;
				output[indexDst] = val;
			}
		});
	}

	public static void minus_A( long[] input, int inputStart, int inputStride,
							   long value, long lower, long upper,
							   long[] output, int outputStart, int outputStride,
							   int rows, int cols ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				long val = ((input[indexSrc] ) - value);
				if( val < lower ) val = lower;
				if( val > upper ) val = upper;
				output[indexDst] = val;
			}
		});
	}

	public static void minus_A( float[] input, int inputStart, int inputStride,
							   float value, float lower, float upper,
							   float[] output, int outputStart, int outputStride,
							   int rows, int cols ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				float val = ((input[indexSrc] ) - value);
				if( val < lower ) val = lower;
				if( val > upper ) val = upper;
				output[indexDst] = val;
			}
		});
	}

	public static void minus_A( double[] input, int inputStart, int inputStride,
							   double value, double lower, double upper,
							   double[] output, int outputStart, int outputStride,
							   int rows, int cols ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				double val = ((input[indexSrc] ) - value);
				if( val < lower ) val = lower;
				if( val > upper ) val = upper;
				output[indexDst] = val;
			}
		});
	}

	public static void minusU_A( byte[] input, int inputStart, int inputStride, 
							   float value,
							   float[] output, int outputStart, int outputStride,
							   int rows, int cols ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				output[indexDst] = ((input[indexSrc] & 0xFF) - value);
			}
		});
	}

	public static void minus_A( byte[] input, int inputStart, int inputStride, 
							   float value,
							   float[] output, int outputStart, int outputStride,
							   int rows, int cols ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				output[indexDst] = ((input[indexSrc] ) - value);
			}
		});
	}

	public static void minusU_A( short[] input, int inputStart, int inputStride, 
							   float value,
							   float[] output, int outputStart, int outputStride,
							   int rows, int cols ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				output[indexDst] = ((input[indexSrc] & 0xFFFF) - value);
			}
		});
	}

	public static void minus_A( short[] input, int inputStart, int inputStride, 
							   float value,
							   float[] output, int outputStart, int outputStride,
							   int rows, int cols ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				output[indexDst] = ((input[indexSrc] ) - value);
			}
		});
	}

	public static void minus_A( int[] input, int inputStart, int inputStride, 
							   float value,
							   float[] output, int outputStart, int outputStride,
							   int rows, int cols ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				output[indexDst] = ((input[indexSrc] ) - value);
			}
		});
	}

	public static void minus_A( long[] input, int inputStart, int inputStride, 
							   float value,
							   float[] output, int outputStart, int outputStride,
							   int rows, int cols ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				output[indexDst] = ((input[indexSrc] ) - value);
			}
		});
	}

	public static void minusU_B( byte[] input, int inputStart, int inputStride, 
							   int value,
							   byte[] output, int outputStart, int outputStride,
							   int rows, int cols ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				output[indexDst] = (byte)(value - (input[indexSrc] & 0xFF));
			}
		});
	}

	public static void minus_B( byte[] input, int inputStart, int inputStride, 
							   int value,
							   byte[] output, int outputStart, int outputStride,
							   int rows, int cols ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				output[indexDst] = (byte)(value - (input[indexSrc] ));
			}
		});
	}

	public static void minusU_B( short[] input, int inputStart, int inputStride, 
							   int value,
							   short[] output, int outputStart, int outputStride,
							   int rows, int cols ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				output[indexDst] = (short)(value - (input[indexSrc] & 0xFFFF));
			}
		});
	}

	public static void minus_B( short[] input, int inputStart, int inputStride, 
							   int value,
							   short[] output, int outputStart, int outputStride,
							   int rows, int cols ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				output[indexDst] = (short)(value - (input[indexSrc] ));
			}
		});
	}

	public static void minus_B( int[] input, int inputStart, int inputStride, 
							   int value,
							   int[] output, int outputStart, int outputStride,
							   int rows, int cols ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				output[indexDst] = (value - (input[indexSrc] ));
			}
		});
	}

	public static void minus_B( long[] input, int inputStart, int inputStride, 
							   long value,
							   long[] output, int outputStart, int outputStride,
							   int rows, int cols ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				output[indexDst] = (value - (input[indexSrc] ));
			}
		});
	}

	public static void minus_B( float[] input, int inputStart, int inputStride, 
							   float value,
							   float[] output, int outputStart, int outputStride,
							   int rows, int cols ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				output[indexDst] = (value - (input[indexSrc] ));
			}
		});
	}

	public static void minus_B( double[] input, int inputStart, int inputStride, 
							   double value,
							   double[] output, int outputStart, int outputStride,
							   int rows, int cols ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				output[indexDst] = (value - (input[indexSrc] ));
			}
		});
	}

	public static void minusU_B( byte[] input, int inputStart, int inputStride,
							   int value, int lower, int upper,
							   byte[] output, int outputStart, int outputStride,
							   int rows, int cols ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				int val = (value - (input[indexSrc] & 0xFF));
				if( val < lower ) val = lower;
				if( val > upper ) val = upper;
				output[indexDst] = (byte)val;
			}
		});
	}

	public static void minus_B( byte[] input, int inputStart, int inputStride,
							   int value, int lower, int upper,
							   byte[] output, int outputStart, int outputStride,
							   int rows, int cols ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				int val = (value - (input[indexSrc] ));
				if( val < lower ) val = lower;
				if( val > upper ) val = upper;
				output[indexDst] = (byte)val;
			}
		});
	}

	public static void minusU_B( short[] input, int inputStart, int inputStride,
							   int value, int lower, int upper,
							   short[] output, int outputStart, int outputStride,
							   int rows, int cols ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				int val = (value - (input[indexSrc] & 0xFFFF));
				if( val < lower ) val = lower;
				if( val > upper ) val = upper;
				output[indexDst] = (short)val;
			}
		});
	}

	public static void minus_B( short[] input, int inputStart, int inputStride,
							   int value, int lower, int upper,
							   short[] output, int outputStart, int outputStride,
							   int rows, int cols ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				int val = (value - (input[indexSrc] ));
				if( val < lower ) val = lower;
				if( val > upper ) val = upper;
				output[indexDst] = (short)val;
			}
		});
	}

	public static void minus_B( int[] input, int inputStart, int inputStride,
							   int value, int lower, int upper,
							   int[] output, int outputStart, int outputStride,
							   int rows, int cols ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				int val = (value - (input[indexSrc] ));
				if( val < lower ) val = lower;
				if( val > upper ) val = upper;
				output[indexDst] = val;
			}
		});
	}

	public static void minus_B( long[] input, int inputStart, int inputStride,
							   long value, long lower, long upper,
							   long[] output, int outputStart, int outputStride,
							   int rows, int cols ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				long val = (value - (input[indexSrc] ));
				if( val < lower ) val = lower;
				if( val > upper ) val = upper;
				output[indexDst] = val;
			}
		});
	}

	public static void minus_B( float[] input, int inputStart, int inputStride,
							   float value, float lower, float upper,
							   float[] output, int outputStart, int outputStride,
							   int rows, int cols ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				float val = (value - (input[indexSrc] ));
				if( val < lower ) val = lower;
				if( val > upper ) val = upper;
				output[indexDst] = val;
			}
		});
	}

	public static void minus_B( double[] input, int inputStart, int inputStride,
							   double value, double lower, double upper,
							   double[] output, int outputStart, int outputStride,
							   int rows, int cols ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				double val = (value - (input[indexSrc] ));
				if( val < lower ) val = lower;
				if( val > upper ) val = upper;
				output[indexDst] = val;
			}
		});
	}

	public static void minusU_B( byte[] input, int inputStart, int inputStride, 
							   float value,
							   float[] output, int outputStart, int outputStride,
							   int rows, int cols ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				output[indexDst] = (value - (input[indexSrc] & 0xFF));
			}
		});
	}

	public static void minus_B( byte[] input, int inputStart, int inputStride, 
							   float value,
							   float[] output, int outputStart, int outputStride,
							   int rows, int cols ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				output[indexDst] = (value - (input[indexSrc] ));
			}
		});
	}

	public static void minusU_B( short[] input, int inputStart, int inputStride, 
							   float value,
							   float[] output, int outputStart, int outputStride,
							   int rows, int cols ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				output[indexDst] = (value - (input[indexSrc] & 0xFFFF));
			}
		});
	}

	public static void minus_B( short[] input, int inputStart, int inputStride, 
							   float value,
							   float[] output, int outputStart, int outputStride,
							   int rows, int cols ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				output[indexDst] = (value - (input[indexSrc] ));
			}
		});
	}

	public static void minus_B( int[] input, int inputStart, int inputStride, 
							   float value,
							   float[] output, int outputStart, int outputStride,
							   int rows, int cols ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				output[indexDst] = (value - (input[indexSrc] ));
			}
		});
	}

	public static void minus_B( long[] input, int inputStart, int inputStride, 
							   float value,
							   float[] output, int outputStart, int outputStride,
							   int rows, int cols ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				output[indexDst] = (value - (input[indexSrc] ));
			}
		});
	}

	public static void boundImage( GrayU8 img, int min, int max ) {
		final int h = img.getHeight();
		final int w = img.getWidth();

		byte[] data = img.data;

		BoofConcurrency.loopFor(0,h,y->{
			int index = img.getStartIndex() + y * img.getStride();
			int indexEnd = index+w;
			// for(int x = 0; x < w; x++ ) {
			for (; index < indexEnd; index++) {
				int value = data[index]& 0xFF;
				if( value < min )
					data[index] = (byte)min;
				else if( value > max )
					data[index] = (byte)max;
			}
		});
	}

	public static void diffAbs( GrayU8 imgA, GrayU8 imgB, GrayU8 output ) {

		final int h = imgA.getHeight();
		final int w = imgA.getWidth();

		BoofConcurrency.loopFor(0,h,y->{
			int indexA = imgA.getStartIndex() + y * imgA.getStride();
			int indexB = imgB.getStartIndex() + y * imgB.getStride();
			int indexDiff = output.getStartIndex() + y * output.getStride();
			
			int indexEnd = indexA+w;
			// for(int x = 0; x < w; x++ ) {
			for (; indexA < indexEnd; indexA++, indexB++, indexDiff++ ) {
				output.data[indexDiff] = (byte)Math.abs((imgA.data[indexA] & 0xFF) - (imgB.data[indexB] & 0xFF));
			}
		});
	}

	public static void boundImage( GrayS8 img, int min, int max ) {
		final int h = img.getHeight();
		final int w = img.getWidth();

		byte[] data = img.data;

		BoofConcurrency.loopFor(0,h,y->{
			int index = img.getStartIndex() + y * img.getStride();
			int indexEnd = index+w;
			// for(int x = 0; x < w; x++ ) {
			for (; index < indexEnd; index++) {
				int value = data[index];
				if( value < min )
					data[index] = (byte)min;
				else if( value > max )
					data[index] = (byte)max;
			}
		});
	}

	public static void diffAbs( GrayS8 imgA, GrayS8 imgB, GrayS8 output ) {

		final int h = imgA.getHeight();
		final int w = imgA.getWidth();

		BoofConcurrency.loopFor(0,h,y->{
			int indexA = imgA.getStartIndex() + y * imgA.getStride();
			int indexB = imgB.getStartIndex() + y * imgB.getStride();
			int indexDiff = output.getStartIndex() + y * output.getStride();
			
			int indexEnd = indexA+w;
			// for(int x = 0; x < w; x++ ) {
			for (; indexA < indexEnd; indexA++, indexB++, indexDiff++ ) {
				output.data[indexDiff] = (byte)Math.abs((imgA.data[indexA] ) - (imgB.data[indexB] ));
			}
		});
	}

	public static void boundImage( GrayU16 img, int min, int max ) {
		final int h = img.getHeight();
		final int w = img.getWidth();

		short[] data = img.data;

		BoofConcurrency.loopFor(0,h,y->{
			int index = img.getStartIndex() + y * img.getStride();
			int indexEnd = index+w;
			// for(int x = 0; x < w; x++ ) {
			for (; index < indexEnd; index++) {
				int value = data[index]& 0xFFFF;
				if( value < min )
					data[index] = (short)min;
				else if( value > max )
					data[index] = (short)max;
			}
		});
	}

	public static void diffAbs( GrayU16 imgA, GrayU16 imgB, GrayU16 output ) {

		final int h = imgA.getHeight();
		final int w = imgA.getWidth();

		BoofConcurrency.loopFor(0,h,y->{
			int indexA = imgA.getStartIndex() + y * imgA.getStride();
			int indexB = imgB.getStartIndex() + y * imgB.getStride();
			int indexDiff = output.getStartIndex() + y * output.getStride();
			
			int indexEnd = indexA+w;
			// for(int x = 0; x < w; x++ ) {
			for (; indexA < indexEnd; indexA++, indexB++, indexDiff++ ) {
				output.data[indexDiff] = (short)Math.abs((imgA.data[indexA] & 0xFFFF) - (imgB.data[indexB] & 0xFFFF));
			}
		});
	}

	public static void boundImage( GrayS16 img, int min, int max ) {
		final int h = img.getHeight();
		final int w = img.getWidth();

		short[] data = img.data;

		BoofConcurrency.loopFor(0,h,y->{
			int index = img.getStartIndex() + y * img.getStride();
			int indexEnd = index+w;
			// for(int x = 0; x < w; x++ ) {
			for (; index < indexEnd; index++) {
				int value = data[index];
				if( value < min )
					data[index] = (short)min;
				else if( value > max )
					data[index] = (short)max;
			}
		});
	}

	public static void diffAbs( GrayS16 imgA, GrayS16 imgB, GrayS16 output ) {

		final int h = imgA.getHeight();
		final int w = imgA.getWidth();

		BoofConcurrency.loopFor(0,h,y->{
			int indexA = imgA.getStartIndex() + y * imgA.getStride();
			int indexB = imgB.getStartIndex() + y * imgB.getStride();
			int indexDiff = output.getStartIndex() + y * output.getStride();
			
			int indexEnd = indexA+w;
			// for(int x = 0; x < w; x++ ) {
			for (; indexA < indexEnd; indexA++, indexB++, indexDiff++ ) {
				output.data[indexDiff] = (short)Math.abs((imgA.data[indexA] ) - (imgB.data[indexB] ));
			}
		});
	}

	public static void boundImage( GrayS32 img, int min, int max ) {
		final int h = img.getHeight();
		final int w = img.getWidth();

		int[] data = img.data;

		BoofConcurrency.loopFor(0,h,y->{
			int index = img.getStartIndex() + y * img.getStride();
			int indexEnd = index+w;
			// for(int x = 0; x < w; x++ ) {
			for (; index < indexEnd; index++) {
				int value = data[index];
				if( value < min )
					data[index] = min;
				else if( value > max )
					data[index] = max;
			}
		});
	}

	public static void diffAbs( GrayS32 imgA, GrayS32 imgB, GrayS32 output ) {

		final int h = imgA.getHeight();
		final int w = imgA.getWidth();

		BoofConcurrency.loopFor(0,h,y->{
			int indexA = imgA.getStartIndex() + y * imgA.getStride();
			int indexB = imgB.getStartIndex() + y * imgB.getStride();
			int indexDiff = output.getStartIndex() + y * output.getStride();
			
			int indexEnd = indexA+w;
			// for(int x = 0; x < w; x++ ) {
			for (; indexA < indexEnd; indexA++, indexB++, indexDiff++ ) {
				output.data[indexDiff] = (int)Math.abs((imgA.data[indexA] ) - (imgB.data[indexB] ));
			}
		});
	}

	public static void boundImage( GrayS64 img, long min, long max ) {
		final int h = img.getHeight();
		final int w = img.getWidth();

		long[] data = img.data;

		BoofConcurrency.loopFor(0,h,y->{
			int index = img.getStartIndex() + y * img.getStride();
			int indexEnd = index+w;
			// for(int x = 0; x < w; x++ ) {
			for (; index < indexEnd; index++) {
				long value = data[index];
				if( value < min )
					data[index] = min;
				else if( value > max )
					data[index] = max;
			}
		});
	}

	public static void diffAbs( GrayS64 imgA, GrayS64 imgB, GrayS64 output ) {

		final int h = imgA.getHeight();
		final int w = imgA.getWidth();

		BoofConcurrency.loopFor(0,h,y->{
			int indexA = imgA.getStartIndex() + y * imgA.getStride();
			int indexB = imgB.getStartIndex() + y * imgB.getStride();
			int indexDiff = output.getStartIndex() + y * output.getStride();
			
			int indexEnd = indexA+w;
			// for(int x = 0; x < w; x++ ) {
			for (; indexA < indexEnd; indexA++, indexB++, indexDiff++ ) {
				output.data[indexDiff] = (long)Math.abs((imgA.data[indexA] ) - (imgB.data[indexB] ));
			}
		});
	}

	public static void boundImage( GrayF32 img, float min, float max ) {
		final int h = img.getHeight();
		final int w = img.getWidth();

		float[] data = img.data;

		BoofConcurrency.loopFor(0,h,y->{
			int index = img.getStartIndex() + y * img.getStride();
			int indexEnd = index+w;
			// for(int x = 0; x < w; x++ ) {
			for (; index < indexEnd; index++) {
				float value = data[index];
				if( value < min )
					data[index] = min;
				else if( value > max )
					data[index] = max;
			}
		});
	}

	public static void diffAbs( GrayF32 imgA, GrayF32 imgB, GrayF32 output ) {

		final int h = imgA.getHeight();
		final int w = imgA.getWidth();

		BoofConcurrency.loopFor(0,h,y->{
			int indexA = imgA.getStartIndex() + y * imgA.getStride();
			int indexB = imgB.getStartIndex() + y * imgB.getStride();
			int indexDiff = output.getStartIndex() + y * output.getStride();
			
			int indexEnd = indexA+w;
			// for(int x = 0; x < w; x++ ) {
			for (; indexA < indexEnd; indexA++, indexB++, indexDiff++ ) {
				output.data[indexDiff] = Math.abs((imgA.data[indexA] ) - (imgB.data[indexB] ));
			}
		});
	}

	public static void boundImage( GrayF64 img, double min, double max ) {
		final int h = img.getHeight();
		final int w = img.getWidth();

		double[] data = img.data;

		BoofConcurrency.loopFor(0,h,y->{
			int index = img.getStartIndex() + y * img.getStride();
			int indexEnd = index+w;
			// for(int x = 0; x < w; x++ ) {
			for (; index < indexEnd; index++) {
				double value = data[index];
				if( value < min )
					data[index] = min;
				else if( value > max )
					data[index] = max;
			}
		});
	}

	public static void diffAbs( GrayF64 imgA, GrayF64 imgB, GrayF64 output ) {

		final int h = imgA.getHeight();
		final int w = imgA.getWidth();

		BoofConcurrency.loopFor(0,h,y->{
			int indexA = imgA.getStartIndex() + y * imgA.getStride();
			int indexB = imgB.getStartIndex() + y * imgB.getStride();
			int indexDiff = output.getStartIndex() + y * output.getStride();
			
			int indexEnd = indexA+w;
			// for(int x = 0; x < w; x++ ) {
			for (; indexA < indexEnd; indexA++, indexB++, indexDiff++ ) {
				output.data[indexDiff] = Math.abs((imgA.data[indexA] ) - (imgB.data[indexB] ));
			}
		});
	}

	public static void add( GrayU8 imgA, GrayU8 imgB, GrayU16 output ) {

		final int h = imgA.getHeight();
		final int w = imgA.getWidth();

		BoofConcurrency.loopFor(0,h,y->{
			int indexA = imgA.getStartIndex() + y * imgA.getStride();
			int indexB = imgB.getStartIndex() + y * imgB.getStride();
			int indexOut = output.getStartIndex() + y * output.getStride();
			
			int indexEnd = indexA+w;
			// for(int x = 0; x < w; x++ ) {
			for (; indexA < indexEnd; indexA++, indexB++, indexOut++ ) {
				output.data[indexOut] = (short)((imgA.data[indexA] & 0xFF) + (imgB.data[indexB] & 0xFF));
			}
		});
	}

	public static void subtract( GrayU8 imgA, GrayU8 imgB, GrayI16 output ) {

		final int h = imgA.getHeight();
		final int w = imgA.getWidth();

		BoofConcurrency.loopFor(0,h,y->{
			int indexA = imgA.getStartIndex() + y * imgA.getStride();
			int indexB = imgB.getStartIndex() + y * imgB.getStride();
			int indexOut = output.getStartIndex() + y * output.getStride();
			
			int indexEnd = indexA+w;
			// for(int x = 0; x < w; x++ ) {
			for (; indexA < indexEnd; indexA++, indexB++, indexOut++ ) {
				output.data[indexOut] = (short)((imgA.data[indexA] & 0xFF) - (imgB.data[indexB] & 0xFF));
			}
		});
	}

	public static void add( GrayS8 imgA, GrayS8 imgB, GrayS16 output ) {

		final int h = imgA.getHeight();
		final int w = imgA.getWidth();

		BoofConcurrency.loopFor(0,h,y->{
			int indexA = imgA.getStartIndex() + y * imgA.getStride();
			int indexB = imgB.getStartIndex() + y * imgB.getStride();
			int indexOut = output.getStartIndex() + y * output.getStride();
			
			int indexEnd = indexA+w;
			// for(int x = 0; x < w; x++ ) {
			for (; indexA < indexEnd; indexA++, indexB++, indexOut++ ) {
				output.data[indexOut] = (short)((imgA.data[indexA] ) + (imgB.data[indexB] ));
			}
		});
	}

	public static void subtract( GrayS8 imgA, GrayS8 imgB, GrayS16 output ) {

		final int h = imgA.getHeight();
		final int w = imgA.getWidth();

		BoofConcurrency.loopFor(0,h,y->{
			int indexA = imgA.getStartIndex() + y * imgA.getStride();
			int indexB = imgB.getStartIndex() + y * imgB.getStride();
			int indexOut = output.getStartIndex() + y * output.getStride();
			
			int indexEnd = indexA+w;
			// for(int x = 0; x < w; x++ ) {
			for (; indexA < indexEnd; indexA++, indexB++, indexOut++ ) {
				output.data[indexOut] = (short)((imgA.data[indexA] ) - (imgB.data[indexB] ));
			}
		});
	}

	public static void add( GrayU16 imgA, GrayU16 imgB, GrayS32 output ) {

		final int h = imgA.getHeight();
		final int w = imgA.getWidth();

		BoofConcurrency.loopFor(0,h,y->{
			int indexA = imgA.getStartIndex() + y * imgA.getStride();
			int indexB = imgB.getStartIndex() + y * imgB.getStride();
			int indexOut = output.getStartIndex() + y * output.getStride();
			
			int indexEnd = indexA+w;
			// for(int x = 0; x < w; x++ ) {
			for (; indexA < indexEnd; indexA++, indexB++, indexOut++ ) {
				output.data[indexOut] = (int)((imgA.data[indexA] & 0xFFFF) + (imgB.data[indexB] & 0xFFFF));
			}
		});
	}

	public static void subtract( GrayU16 imgA, GrayU16 imgB, GrayS32 output ) {

		final int h = imgA.getHeight();
		final int w = imgA.getWidth();

		BoofConcurrency.loopFor(0,h,y->{
			int indexA = imgA.getStartIndex() + y * imgA.getStride();
			int indexB = imgB.getStartIndex() + y * imgB.getStride();
			int indexOut = output.getStartIndex() + y * output.getStride();
			
			int indexEnd = indexA+w;
			// for(int x = 0; x < w; x++ ) {
			for (; indexA < indexEnd; indexA++, indexB++, indexOut++ ) {
				output.data[indexOut] = (int)((imgA.data[indexA] & 0xFFFF) - (imgB.data[indexB] & 0xFFFF));
			}
		});
	}

	public static void add( GrayS16 imgA, GrayS16 imgB, GrayS32 output ) {

		final int h = imgA.getHeight();
		final int w = imgA.getWidth();

		BoofConcurrency.loopFor(0,h,y->{
			int indexA = imgA.getStartIndex() + y * imgA.getStride();
			int indexB = imgB.getStartIndex() + y * imgB.getStride();
			int indexOut = output.getStartIndex() + y * output.getStride();
			
			int indexEnd = indexA+w;
			// for(int x = 0; x < w; x++ ) {
			for (; indexA < indexEnd; indexA++, indexB++, indexOut++ ) {
				output.data[indexOut] = (int)((imgA.data[indexA] ) + (imgB.data[indexB] ));
			}
		});
	}

	public static void subtract( GrayS16 imgA, GrayS16 imgB, GrayS32 output ) {

		final int h = imgA.getHeight();
		final int w = imgA.getWidth();

		BoofConcurrency.loopFor(0,h,y->{
			int indexA = imgA.getStartIndex() + y * imgA.getStride();
			int indexB = imgB.getStartIndex() + y * imgB.getStride();
			int indexOut = output.getStartIndex() + y * output.getStride();
			
			int indexEnd = indexA+w;
			// for(int x = 0; x < w; x++ ) {
			for (; indexA < indexEnd; indexA++, indexB++, indexOut++ ) {
				output.data[indexOut] = (int)((imgA.data[indexA] ) - (imgB.data[indexB] ));
			}
		});
	}

	public static void add( GrayS32 imgA, GrayS32 imgB, GrayS32 output ) {

		final int h = imgA.getHeight();
		final int w = imgA.getWidth();

		BoofConcurrency.loopFor(0,h,y->{
			int indexA = imgA.getStartIndex() + y * imgA.getStride();
			int indexB = imgB.getStartIndex() + y * imgB.getStride();
			int indexOut = output.getStartIndex() + y * output.getStride();
			
			int indexEnd = indexA+w;
			// for(int x = 0; x < w; x++ ) {
			for (; indexA < indexEnd; indexA++, indexB++, indexOut++ ) {
				output.data[indexOut] = (int)((imgA.data[indexA] ) + (imgB.data[indexB] ));
			}
		});
	}

	public static void subtract( GrayS32 imgA, GrayS32 imgB, GrayS32 output ) {

		final int h = imgA.getHeight();
		final int w = imgA.getWidth();

		BoofConcurrency.loopFor(0,h,y->{
			int indexA = imgA.getStartIndex() + y * imgA.getStride();
			int indexB = imgB.getStartIndex() + y * imgB.getStride();
			int indexOut = output.getStartIndex() + y * output.getStride();
			
			int indexEnd = indexA+w;
			// for(int x = 0; x < w; x++ ) {
			for (; indexA < indexEnd; indexA++, indexB++, indexOut++ ) {
				output.data[indexOut] = (int)((imgA.data[indexA] ) - (imgB.data[indexB] ));
			}
		});
	}

	public static void add( GrayS64 imgA, GrayS64 imgB, GrayS64 output ) {

		final int h = imgA.getHeight();
		final int w = imgA.getWidth();

		BoofConcurrency.loopFor(0,h,y->{
			int indexA = imgA.getStartIndex() + y * imgA.getStride();
			int indexB = imgB.getStartIndex() + y * imgB.getStride();
			int indexOut = output.getStartIndex() + y * output.getStride();
			
			int indexEnd = indexA+w;
			// for(int x = 0; x < w; x++ ) {
			for (; indexA < indexEnd; indexA++, indexB++, indexOut++ ) {
				output.data[indexOut] = (long)((imgA.data[indexA] ) + (imgB.data[indexB] ));
			}
		});
	}

	public static void subtract( GrayS64 imgA, GrayS64 imgB, GrayS64 output ) {

		final int h = imgA.getHeight();
		final int w = imgA.getWidth();

		BoofConcurrency.loopFor(0,h,y->{
			int indexA = imgA.getStartIndex() + y * imgA.getStride();
			int indexB = imgB.getStartIndex() + y * imgB.getStride();
			int indexOut = output.getStartIndex() + y * output.getStride();
			
			int indexEnd = indexA+w;
			// for(int x = 0; x < w; x++ ) {
			for (; indexA < indexEnd; indexA++, indexB++, indexOut++ ) {
				output.data[indexOut] = (long)((imgA.data[indexA] ) - (imgB.data[indexB] ));
			}
		});
	}

	public static void add( GrayF32 imgA, GrayF32 imgB, GrayF32 output ) {

		final int h = imgA.getHeight();
		final int w = imgA.getWidth();

		BoofConcurrency.loopFor(0,h,y->{
			int indexA = imgA.getStartIndex() + y * imgA.getStride();
			int indexB = imgB.getStartIndex() + y * imgB.getStride();
			int indexOut = output.getStartIndex() + y * output.getStride();
			
			int indexEnd = indexA+w;
			// for(int x = 0; x < w; x++ ) {
			for (; indexA < indexEnd; indexA++, indexB++, indexOut++ ) {
				output.data[indexOut] = ((imgA.data[indexA] ) + (imgB.data[indexB] ));
			}
		});
	}

	public static void subtract( GrayF32 imgA, GrayF32 imgB, GrayF32 output ) {

		final int h = imgA.getHeight();
		final int w = imgA.getWidth();

		BoofConcurrency.loopFor(0,h,y->{
			int indexA = imgA.getStartIndex() + y * imgA.getStride();
			int indexB = imgB.getStartIndex() + y * imgB.getStride();
			int indexOut = output.getStartIndex() + y * output.getStride();
			
			int indexEnd = indexA+w;
			// for(int x = 0; x < w; x++ ) {
			for (; indexA < indexEnd; indexA++, indexB++, indexOut++ ) {
				output.data[indexOut] = ((imgA.data[indexA] ) - (imgB.data[indexB] ));
			}
		});
	}

	public static void multiply( GrayF32 imgA, GrayF32 imgB, GrayF32 output ) {

		final int h = imgA.getHeight();
		final int w = imgA.getWidth();

		BoofConcurrency.loopFor(0,h,y->{
			int indexA = imgA.getStartIndex() + y * imgA.getStride();
			int indexB = imgB.getStartIndex() + y * imgB.getStride();
			int indexOut = output.getStartIndex() + y * output.getStride();
			
			int indexEnd = indexA+w;
			// for(int x = 0; x < w; x++ ) {
			for (; indexA < indexEnd; indexA++, indexB++, indexOut++ ) {
				output.data[indexOut] = ((imgA.data[indexA] ) * (imgB.data[indexB] ));
			}
		});
	}

	public static void divide( GrayF32 imgA, GrayF32 imgB, GrayF32 output ) {

		final int h = imgA.getHeight();
		final int w = imgA.getWidth();

		BoofConcurrency.loopFor(0,h,y->{
			int indexA = imgA.getStartIndex() + y * imgA.getStride();
			int indexB = imgB.getStartIndex() + y * imgB.getStride();
			int indexOut = output.getStartIndex() + y * output.getStride();
			
			int indexEnd = indexA+w;
			// for(int x = 0; x < w; x++ ) {
			for (; indexA < indexEnd; indexA++, indexB++, indexOut++ ) {
				output.data[indexOut] = ((imgA.data[indexA] ) / (imgB.data[indexB] ));
			}
		});
	}

	public static void add( GrayF64 imgA, GrayF64 imgB, GrayF64 output ) {

		final int h = imgA.getHeight();
		final int w = imgA.getWidth();

		BoofConcurrency.loopFor(0,h,y->{
			int indexA = imgA.getStartIndex() + y * imgA.getStride();
			int indexB = imgB.getStartIndex() + y * imgB.getStride();
			int indexOut = output.getStartIndex() + y * output.getStride();
			
			int indexEnd = indexA+w;
			// for(int x = 0; x < w; x++ ) {
			for (; indexA < indexEnd; indexA++, indexB++, indexOut++ ) {
				output.data[indexOut] = ((imgA.data[indexA] ) + (imgB.data[indexB] ));
			}
		});
	}

	public static void subtract( GrayF64 imgA, GrayF64 imgB, GrayF64 output ) {

		final int h = imgA.getHeight();
		final int w = imgA.getWidth();

		BoofConcurrency.loopFor(0,h,y->{
			int indexA = imgA.getStartIndex() + y * imgA.getStride();
			int indexB = imgB.getStartIndex() + y * imgB.getStride();
			int indexOut = output.getStartIndex() + y * output.getStride();
			
			int indexEnd = indexA+w;
			// for(int x = 0; x < w; x++ ) {
			for (; indexA < indexEnd; indexA++, indexB++, indexOut++ ) {
				output.data[indexOut] = ((imgA.data[indexA] ) - (imgB.data[indexB] ));
			}
		});
	}

	public static void multiply( GrayF64 imgA, GrayF64 imgB, GrayF64 output ) {

		final int h = imgA.getHeight();
		final int w = imgA.getWidth();

		BoofConcurrency.loopFor(0,h,y->{
			int indexA = imgA.getStartIndex() + y * imgA.getStride();
			int indexB = imgB.getStartIndex() + y * imgB.getStride();
			int indexOut = output.getStartIndex() + y * output.getStride();
			
			int indexEnd = indexA+w;
			// for(int x = 0; x < w; x++ ) {
			for (; indexA < indexEnd; indexA++, indexB++, indexOut++ ) {
				output.data[indexOut] = ((imgA.data[indexA] ) * (imgB.data[indexB] ));
			}
		});
	}

	public static void divide( GrayF64 imgA, GrayF64 imgB, GrayF64 output ) {

		final int h = imgA.getHeight();
		final int w = imgA.getWidth();

		BoofConcurrency.loopFor(0,h,y->{
			int indexA = imgA.getStartIndex() + y * imgA.getStride();
			int indexB = imgB.getStartIndex() + y * imgB.getStride();
			int indexOut = output.getStartIndex() + y * output.getStride();
			
			int indexEnd = indexA+w;
			// for(int x = 0; x < w; x++ ) {
			for (; indexA < indexEnd; indexA++, indexB++, indexOut++ ) {
				output.data[indexOut] = ((imgA.data[indexA] ) / (imgB.data[indexB] ));
			}
		});
	}

	public static void log( float[] input, int inputStart, int inputStride,
							   float val,
							   float[] output, int outputStart, int outputStride,
							   int rows, int cols ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				output[indexDst] = (float)Math.log(val + input[indexSrc]);			}
		});
	}

	public static void log( double[] input, int inputStart, int inputStride,
							   double val,
							   double[] output, int outputStart, int outputStride,
							   int rows, int cols ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				output[indexDst] = (double)Math.log(val + input[indexSrc]);			}
		});
	}

	public static void logSign( float[] input, int inputStart, int inputStride,
							   float val,
							   float[] output, int outputStart, int outputStride,
							   int rows, int cols ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				float value = input[indexSrc];
				if( value < 0 ) {
					output[indexDst] = (float)-Math.log(val - value);
				} else {
					output[indexDst] = (float)Math.log(val + value);
				}
			}
		});
	}

	public static void logSign( double[] input, int inputStart, int inputStride,
							   double val,
							   double[] output, int outputStart, int outputStride,
							   int rows, int cols ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				double value = input[indexSrc];
				if( value < 0 ) {
					output[indexDst] = (double)-Math.log(val - value);
				} else {
					output[indexDst] = (double)Math.log(val + value);
				}
			}
		});
	}

	public static void sqrt( float[] input, int inputStart, int inputStride,
							   float[] output, int outputStart, int outputStride,
							   int rows, int cols ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				output[indexDst] = (float)Math.sqrt(input[indexSrc]);
			}
		});
	}

	public static void sqrt( double[] input, int inputStart, int inputStride,
							   double[] output, int outputStart, int outputStride,
							   int rows, int cols ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				output[indexDst] = (double)Math.sqrt(input[indexSrc]);
			}
		});
	}

	public static void pow2( byte[] input, int inputStart, int inputStride,
							   short[] output, int outputStart, int outputStride,
							   int rows, int cols ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				int v = input[indexSrc]& 0xFF;
				output[indexDst] = (short)(v*v);

			}
		});
	}

	public static void pow2( short[] input, int inputStart, int inputStride,
							   int[] output, int outputStart, int outputStride,
							   int rows, int cols ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				int v = input[indexSrc]& 0xFFFF;
				output[indexDst] = (int)(v*v);

			}
		});
	}

	public static void pow2( float[] input, int inputStart, int inputStride,
							   float[] output, int outputStart, int outputStride,
							   int rows, int cols ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				float v = input[indexSrc];
				output[indexDst] = (float)(v*v);

			}
		});
	}

	public static void pow2( double[] input, int inputStart, int inputStride,
							   double[] output, int outputStart, int outputStride,
							   int rows, int cols ) {
		BoofConcurrency.loopFor(0,rows,y->{
			int indexSrc = inputStart + y*inputStride;
			int indexDst = outputStart + y*outputStride;
			int end = indexSrc + cols;

			for (; indexSrc < end; indexSrc++, indexDst++) {
				double v = input[indexSrc];
				output[indexDst] = (double)(v*v);

			}
		});
	}

	public static void stdev( GrayU8 mean, GrayU16 pow2, GrayU8 stdev ) {

		final int h = mean.getHeight();
		final int w = mean.getWidth();

		BoofConcurrency.loopFor(0,h,y->{
			int indexMean  = mean.startIndex  + y * mean.stride;
			int indexPow   = pow2.startIndex  + y * pow2.stride;
			int indexStdev = stdev.startIndex + y * stdev.stride;

			int indexEnd = indexMean+w;
			// for(int x = 0; x < w; x++ ) {
			for (; indexMean < indexEnd; indexMean++, indexPow++, indexStdev++ ) {
				int mu = mean.data[indexMean]& 0xFF;
				int p2 = pow2.data[indexPow]& 0xFFFF;

				stdev.data[indexStdev] = (byte)Math.sqrt(Math.max(0,p2-mu*mu));
			}
		});
	}

	public static void stdev( GrayU16 mean, GrayS32 pow2, GrayU16 stdev ) {

		final int h = mean.getHeight();
		final int w = mean.getWidth();

		BoofConcurrency.loopFor(0,h,y->{
			int indexMean  = mean.startIndex  + y * mean.stride;
			int indexPow   = pow2.startIndex  + y * pow2.stride;
			int indexStdev = stdev.startIndex + y * stdev.stride;

			int indexEnd = indexMean+w;
			// for(int x = 0; x < w; x++ ) {
			for (; indexMean < indexEnd; indexMean++, indexPow++, indexStdev++ ) {
				int mu = mean.data[indexMean]& 0xFFFF;
				int p2 = pow2.data[indexPow];

				stdev.data[indexStdev] = (short)Math.sqrt(Math.max(0,p2-mu*mu));
			}
		});
	}

	public static void stdev( GrayF32 mean, GrayF32 pow2, GrayF32 stdev ) {

		final int h = mean.getHeight();
		final int w = mean.getWidth();

		BoofConcurrency.loopFor(0,h,y->{
			int indexMean  = mean.startIndex  + y * mean.stride;
			int indexPow   = pow2.startIndex  + y * pow2.stride;
			int indexStdev = stdev.startIndex + y * stdev.stride;

			int indexEnd = indexMean+w;
			// for(int x = 0; x < w; x++ ) {
			for (; indexMean < indexEnd; indexMean++, indexPow++, indexStdev++ ) {
				float mu = mean.data[indexMean];
				float p2 = pow2.data[indexPow];

				stdev.data[indexStdev] = (float)Math.sqrt(Math.max(0,p2-mu*mu));
			}
		});
	}

	public static void stdev( GrayF64 mean, GrayF64 pow2, GrayF64 stdev ) {

		final int h = mean.getHeight();
		final int w = mean.getWidth();

		BoofConcurrency.loopFor(0,h,y->{
			int indexMean  = mean.startIndex  + y * mean.stride;
			int indexPow   = pow2.startIndex  + y * pow2.stride;
			int indexStdev = stdev.startIndex + y * stdev.stride;

			int indexEnd = indexMean+w;
			// for(int x = 0; x < w; x++ ) {
			for (; indexMean < indexEnd; indexMean++, indexPow++, indexStdev++ ) {
				double mu = mean.data[indexMean];
				double p2 = pow2.data[indexPow];

				stdev.data[indexStdev] = (double)Math.sqrt(Math.max(0,p2-mu*mu));
			}
		});
	}

}
