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
import java.util.Arrays;

import java.util.ArrayList;
import java.util.List;
import boofcv.concurrency.BoofConcurrency;

/**
 * Computes statistical properties of pixels inside an image.
 *
 *
 * <p>DO NOT MODIFY. Automatically generated code created by GenerateImplImageStatistics</p>
 *
 * @author Peter Abeles
 */
@Generated("boofcv.alg.misc.impl.ImplImageStatistics")
public class ImplImageStatistics_MT {

	public static int minU( byte[] array , int startIndex , int rows , int columns , int stride ) {

		final int _output = array[startIndex]& 0xFF;

		return BoofConcurrency.min(0,rows,int.class,y->{
			int output = _output;
			int index = startIndex + y*stride;
			int end = index + columns;

			for( ; index < end; index++ ) {
				int v = array[index] & 0xFF;
				if( v < output )
					output = v;
			}
		return output;}).intValue();
	}

	public static int maxU( byte[] array , int startIndex , int rows , int columns , int stride ) {

		final int _output = array[startIndex]& 0xFF;

		return BoofConcurrency.max(0,rows,int.class,y->{
			int output = _output;
			int index = startIndex + y*stride;
			int end = index + columns;

			for( ; index < end; index++ ) {
				int v = array[index] & 0xFF;
				if( v > output )
					output = v;
			}
		return output;}).intValue();
	}

	public static int maxAbsU( byte[] array , int startIndex , int rows , int columns , int stride ) {

		final int _output = array[startIndex]& 0xFF;

		return BoofConcurrency.max(0,rows,int.class,y->{
			int output = _output;
			int index = startIndex + y*stride;
			int end = index + columns;

			for( ; index < end; index++ ) {
				int v = array[index] & 0xFF;
				if( v > output )
					output = v;
			}
		return output;}).intValue();
	}

	public static double meanDiffSqU(byte []dataA, int startIndexA , int strideA,
									byte []dataB, int startIndexB , int strideB,
									int rows , int columns ) {

		return BoofConcurrency.sum(0,rows,int.class,y->{
			int total = 0;
			int indexA = startIndexA + y * strideA;
			int indexB = startIndexB + y * strideB;
			
			int indexEnd = indexA+columns;
			
			for (; indexA < indexEnd; indexA++,indexB++) {
				int difference = (dataA[indexA]& 0xFF)-(dataB[indexB]& 0xFF);
				total += difference*difference;
			}
		return total;}).intValue()/ (double)(rows*columns);
	}

	public static double meanDiffAbsU(byte []dataA, int startIndexA , int strideA,
									byte []dataB, int startIndexB , int strideB,
									int rows , int columns ) {

		return BoofConcurrency.sum(0,rows,int.class,y->{
			int total = 0;
			int indexA = startIndexA + y * strideA;
			int indexB = startIndexB + y * strideB;
			
			int indexEnd = indexA+columns;
			
			for (; indexA < indexEnd; indexA++,indexB++) {
				int difference = (dataA[indexA]& 0xFF)-(dataB[indexB]& 0xFF);
				total += Math.abs(difference);
			}
		return total;}).intValue()/ (double)(rows*columns);
	}

	public static int sum( GrayU8 img ) {

		final int rows = img.height;
		final int columns = img.width;


		return BoofConcurrency.sum(0,img.height,int.class,y->{
			int total = 0;
			int index = img.startIndex + y * img.stride;
			
			int indexEnd = index+columns;
			for (; index < indexEnd; index++ ) {
				total += img.data[index] & 0xFF;
			}
		return total;}).intValue();
	}

	public static int sum( InterleavedU8 img ) {

		final int rows = img.height;
		final int columns = img.width*img.numBands;


		return BoofConcurrency.sum(0,img.height,int.class,y->{
			int total = 0;
			int index = img.startIndex + y * img.stride;
			
			int indexEnd = index+columns;
			for (; index < indexEnd; index++ ) {
				total += img.data[index] & 0xFF;
			}
		return total;}).intValue();
	}

	public static double variance( GrayU8 img , double mean ) {


		return BoofConcurrency.sum(0,img.height,double.class,y->{
			double total = 0;
			int index = img.getStartIndex() + y * img.getStride();

			int indexEnd = index+img.width;
			// for(int x = 0; x < img.width; x++ ) {
			for (; index < indexEnd; index++ ) {
				double d = (img.data[index]& 0xFF) - mean; 
				total += d*d;
			}
		return total;}).intValue()/(img.width*img.height);
	}

	public static void histogram( GrayU8 input , int minValue , int[] histogram ) {
		Arrays.fill(histogram,0);

		final List<int[]> list = new ArrayList<>();
		BoofConcurrency.loopBlocks(0,input.height,(y0,y1)->{
		final int[] h = new int[histogram.length];
		for( int y = y0; y < y1; y++ ) {
			int index = input.startIndex + y*input.stride;
			int end = index + input.width;

			while( index < end ) {
				h[(input.data[index++]& 0xFF) - minValue ]++;
			}
		}
		synchronized(list){list.add(h);}});
		for (int i = 0; i < list.size(); i++) {
			int[] h = list.get(i);
			for (int j = 0; j < histogram.length; j++) {
				histogram[j] += h[j];
			}
		}
	}

	public static void histogramScaled( GrayU8 input , int minValue , int maxValue, int[] histogram ) {
		Arrays.fill(histogram,0);

		final int histLength = histogram.length;
		final int rangeValue = maxValue-minValue+1;
		
		final List<int[]> list = new ArrayList<>();
		BoofConcurrency.loopBlocks(0,input.height,(y0,y1)->{
		final int[] h = new int[histogram.length];
		for( int y = y0; y < y1; y++ ) {
			int index = input.startIndex + y*input.stride;
			int end = index + input.width;

			while( index < end ) {
				h[(int)(histLength*((input.data[index++]& 0xFF) - minValue)/rangeValue) ]++;
			}
		}
		synchronized(list){list.add(h);}});
		for (int i = 0; i < list.size(); i++) {
			int[] h = list.get(i);
			for (int j = 0; j < histogram.length; j++) {
				histogram[j] += h[j];
			}
		}
	}

	public static int min( byte[] array , int startIndex , int rows , int columns , int stride ) {

		final int _output = array[startIndex];

		return BoofConcurrency.min(0,rows,int.class,y->{
			int output = _output;
			int index = startIndex + y*stride;
			int end = index + columns;

			for( ; index < end; index++ ) {
				int v = array[index] ;
				if( v < output )
					output = v;
			}
		return output;}).intValue();
	}

	public static int max( byte[] array , int startIndex , int rows , int columns , int stride ) {

		final int _output = array[startIndex];

		return BoofConcurrency.max(0,rows,int.class,y->{
			int output = _output;
			int index = startIndex + y*stride;
			int end = index + columns;

			for( ; index < end; index++ ) {
				int v = array[index] ;
				if( v > output )
					output = v;
			}
		return output;}).intValue();
	}

	public static int maxAbs( byte[] array , int startIndex , int rows , int columns , int stride ) {

		final int _output = array[startIndex];

		return BoofConcurrency.max(0,rows,int.class,y->{
			int output = _output;
			int index = startIndex + y*stride;
			int end = index + columns;

			for( ; index < end; index++ ) {
				int v = Math.abs(array[index]);
				if( v > output )
					output = v;
			}
		return output;}).intValue();
	}

	public static double meanDiffSq(byte []dataA, int startIndexA , int strideA,
									byte []dataB, int startIndexB , int strideB,
									int rows , int columns ) {

		return BoofConcurrency.sum(0,rows,int.class,y->{
			int total = 0;
			int indexA = startIndexA + y * strideA;
			int indexB = startIndexB + y * strideB;
			
			int indexEnd = indexA+columns;
			
			for (; indexA < indexEnd; indexA++,indexB++) {
				int difference = (dataA[indexA])-(dataB[indexB]);
				total += difference*difference;
			}
		return total;}).intValue()/ (double)(rows*columns);
	}

	public static double meanDiffAbs(byte []dataA, int startIndexA , int strideA,
									byte []dataB, int startIndexB , int strideB,
									int rows , int columns ) {

		return BoofConcurrency.sum(0,rows,int.class,y->{
			int total = 0;
			int indexA = startIndexA + y * strideA;
			int indexB = startIndexB + y * strideB;
			
			int indexEnd = indexA+columns;
			
			for (; indexA < indexEnd; indexA++,indexB++) {
				int difference = (dataA[indexA])-(dataB[indexB]);
				total += Math.abs(difference);
			}
		return total;}).intValue()/ (double)(rows*columns);
	}

	public static int sum( GrayS8 img ) {

		final int rows = img.height;
		final int columns = img.width;


		return BoofConcurrency.sum(0,img.height,int.class,y->{
			int total = 0;
			int index = img.startIndex + y * img.stride;
			
			int indexEnd = index+columns;
			for (; index < indexEnd; index++ ) {
				total += img.data[index] ;
			}
		return total;}).intValue();
	}

	public static int sumAbs( GrayS8 img ) {

		final int rows = img.height;
		final int columns = img.width;


		return BoofConcurrency.sum(0,img.height,int.class,y->{
			int total = 0;
			int index = img.startIndex + y * img.stride;
			
			int indexEnd = index+columns;
			for (; index < indexEnd; index++ ) {
				total += Math.abs(img.data[index] );
			}
		return total;}).intValue();
	}

	public static int sum( InterleavedS8 img ) {

		final int rows = img.height;
		final int columns = img.width*img.numBands;


		return BoofConcurrency.sum(0,img.height,int.class,y->{
			int total = 0;
			int index = img.startIndex + y * img.stride;
			
			int indexEnd = index+columns;
			for (; index < indexEnd; index++ ) {
				total += img.data[index] ;
			}
		return total;}).intValue();
	}

	public static int sumAbs( InterleavedS8 img ) {

		final int rows = img.height;
		final int columns = img.width*img.numBands;


		return BoofConcurrency.sum(0,img.height,int.class,y->{
			int total = 0;
			int index = img.startIndex + y * img.stride;
			
			int indexEnd = index+columns;
			for (; index < indexEnd; index++ ) {
				total += Math.abs(img.data[index] );
			}
		return total;}).intValue();
	}

	public static double variance( GrayS8 img , double mean ) {


		return BoofConcurrency.sum(0,img.height,double.class,y->{
			double total = 0;
			int index = img.getStartIndex() + y * img.getStride();

			int indexEnd = index+img.width;
			// for(int x = 0; x < img.width; x++ ) {
			for (; index < indexEnd; index++ ) {
				double d = (img.data[index]) - mean; 
				total += d*d;
			}
		return total;}).intValue()/(img.width*img.height);
	}

	public static void histogram( GrayS8 input , int minValue , int[] histogram ) {
		Arrays.fill(histogram,0);

		final List<int[]> list = new ArrayList<>();
		BoofConcurrency.loopBlocks(0,input.height,(y0,y1)->{
		final int[] h = new int[histogram.length];
		for( int y = y0; y < y1; y++ ) {
			int index = input.startIndex + y*input.stride;
			int end = index + input.width;

			while( index < end ) {
				h[(input.data[index++]) - minValue ]++;
			}
		}
		synchronized(list){list.add(h);}});
		for (int i = 0; i < list.size(); i++) {
			int[] h = list.get(i);
			for (int j = 0; j < histogram.length; j++) {
				histogram[j] += h[j];
			}
		}
	}

	public static void histogramScaled( GrayS8 input , int minValue , int maxValue, int[] histogram ) {
		Arrays.fill(histogram,0);

		final int histLength = histogram.length;
		final int rangeValue = maxValue-minValue+1;
		
		final List<int[]> list = new ArrayList<>();
		BoofConcurrency.loopBlocks(0,input.height,(y0,y1)->{
		final int[] h = new int[histogram.length];
		for( int y = y0; y < y1; y++ ) {
			int index = input.startIndex + y*input.stride;
			int end = index + input.width;

			while( index < end ) {
				h[(int)(histLength*((input.data[index++]) - minValue)/rangeValue) ]++;
			}
		}
		synchronized(list){list.add(h);}});
		for (int i = 0; i < list.size(); i++) {
			int[] h = list.get(i);
			for (int j = 0; j < histogram.length; j++) {
				histogram[j] += h[j];
			}
		}
	}

	public static int minU( short[] array , int startIndex , int rows , int columns , int stride ) {

		final int _output = array[startIndex]& 0xFFFF;

		return BoofConcurrency.min(0,rows,int.class,y->{
			int output = _output;
			int index = startIndex + y*stride;
			int end = index + columns;

			for( ; index < end; index++ ) {
				int v = array[index] & 0xFFFF;
				if( v < output )
					output = v;
			}
		return output;}).intValue();
	}

	public static int maxU( short[] array , int startIndex , int rows , int columns , int stride ) {

		final int _output = array[startIndex]& 0xFFFF;

		return BoofConcurrency.max(0,rows,int.class,y->{
			int output = _output;
			int index = startIndex + y*stride;
			int end = index + columns;

			for( ; index < end; index++ ) {
				int v = array[index] & 0xFFFF;
				if( v > output )
					output = v;
			}
		return output;}).intValue();
	}

	public static int maxAbsU( short[] array , int startIndex , int rows , int columns , int stride ) {

		final int _output = array[startIndex]& 0xFFFF;

		return BoofConcurrency.max(0,rows,int.class,y->{
			int output = _output;
			int index = startIndex + y*stride;
			int end = index + columns;

			for( ; index < end; index++ ) {
				int v = array[index] & 0xFFFF;
				if( v > output )
					output = v;
			}
		return output;}).intValue();
	}

	public static double meanDiffSqU(short []dataA, int startIndexA , int strideA,
									short []dataB, int startIndexB , int strideB,
									int rows , int columns ) {

		return BoofConcurrency.sum(0,rows,int.class,y->{
			int total = 0;
			int indexA = startIndexA + y * strideA;
			int indexB = startIndexB + y * strideB;
			
			int indexEnd = indexA+columns;
			
			for (; indexA < indexEnd; indexA++,indexB++) {
				int difference = (dataA[indexA]& 0xFFFF)-(dataB[indexB]& 0xFFFF);
				total += difference*difference;
			}
		return total;}).intValue()/ (double)(rows*columns);
	}

	public static double meanDiffAbsU(short []dataA, int startIndexA , int strideA,
									short []dataB, int startIndexB , int strideB,
									int rows , int columns ) {

		return BoofConcurrency.sum(0,rows,int.class,y->{
			int total = 0;
			int indexA = startIndexA + y * strideA;
			int indexB = startIndexB + y * strideB;
			
			int indexEnd = indexA+columns;
			
			for (; indexA < indexEnd; indexA++,indexB++) {
				int difference = (dataA[indexA]& 0xFFFF)-(dataB[indexB]& 0xFFFF);
				total += Math.abs(difference);
			}
		return total;}).intValue()/ (double)(rows*columns);
	}

	public static int sum( GrayU16 img ) {

		final int rows = img.height;
		final int columns = img.width;


		return BoofConcurrency.sum(0,img.height,int.class,y->{
			int total = 0;
			int index = img.startIndex + y * img.stride;
			
			int indexEnd = index+columns;
			for (; index < indexEnd; index++ ) {
				total += img.data[index] & 0xFFFF;
			}
		return total;}).intValue();
	}

	public static int sum( InterleavedU16 img ) {

		final int rows = img.height;
		final int columns = img.width*img.numBands;


		return BoofConcurrency.sum(0,img.height,int.class,y->{
			int total = 0;
			int index = img.startIndex + y * img.stride;
			
			int indexEnd = index+columns;
			for (; index < indexEnd; index++ ) {
				total += img.data[index] & 0xFFFF;
			}
		return total;}).intValue();
	}

	public static double variance( GrayU16 img , double mean ) {


		return BoofConcurrency.sum(0,img.height,double.class,y->{
			double total = 0;
			int index = img.getStartIndex() + y * img.getStride();

			int indexEnd = index+img.width;
			// for(int x = 0; x < img.width; x++ ) {
			for (; index < indexEnd; index++ ) {
				double d = (img.data[index]& 0xFFFF) - mean; 
				total += d*d;
			}
		return total;}).intValue()/(img.width*img.height);
	}

	public static void histogram( GrayU16 input , int minValue , int[] histogram ) {
		Arrays.fill(histogram,0);

		final List<int[]> list = new ArrayList<>();
		BoofConcurrency.loopBlocks(0,input.height,(y0,y1)->{
		final int[] h = new int[histogram.length];
		for( int y = y0; y < y1; y++ ) {
			int index = input.startIndex + y*input.stride;
			int end = index + input.width;

			while( index < end ) {
				h[(input.data[index++]& 0xFFFF) - minValue ]++;
			}
		}
		synchronized(list){list.add(h);}});
		for (int i = 0; i < list.size(); i++) {
			int[] h = list.get(i);
			for (int j = 0; j < histogram.length; j++) {
				histogram[j] += h[j];
			}
		}
	}

	public static void histogramScaled( GrayU16 input , int minValue , int maxValue, int[] histogram ) {
		Arrays.fill(histogram,0);

		final int histLength = histogram.length;
		final int rangeValue = maxValue-minValue+1;
		
		final List<int[]> list = new ArrayList<>();
		BoofConcurrency.loopBlocks(0,input.height,(y0,y1)->{
		final int[] h = new int[histogram.length];
		for( int y = y0; y < y1; y++ ) {
			int index = input.startIndex + y*input.stride;
			int end = index + input.width;

			while( index < end ) {
				h[(int)(histLength*((input.data[index++]& 0xFFFF) - minValue)/rangeValue) ]++;
			}
		}
		synchronized(list){list.add(h);}});
		for (int i = 0; i < list.size(); i++) {
			int[] h = list.get(i);
			for (int j = 0; j < histogram.length; j++) {
				histogram[j] += h[j];
			}
		}
	}

	public static int min( short[] array , int startIndex , int rows , int columns , int stride ) {

		final int _output = array[startIndex];

		return BoofConcurrency.min(0,rows,int.class,y->{
			int output = _output;
			int index = startIndex + y*stride;
			int end = index + columns;

			for( ; index < end; index++ ) {
				int v = array[index] ;
				if( v < output )
					output = v;
			}
		return output;}).intValue();
	}

	public static int max( short[] array , int startIndex , int rows , int columns , int stride ) {

		final int _output = array[startIndex];

		return BoofConcurrency.max(0,rows,int.class,y->{
			int output = _output;
			int index = startIndex + y*stride;
			int end = index + columns;

			for( ; index < end; index++ ) {
				int v = array[index] ;
				if( v > output )
					output = v;
			}
		return output;}).intValue();
	}

	public static int maxAbs( short[] array , int startIndex , int rows , int columns , int stride ) {

		final int _output = array[startIndex];

		return BoofConcurrency.max(0,rows,int.class,y->{
			int output = _output;
			int index = startIndex + y*stride;
			int end = index + columns;

			for( ; index < end; index++ ) {
				int v = Math.abs(array[index]);
				if( v > output )
					output = v;
			}
		return output;}).intValue();
	}

	public static double meanDiffSq(short []dataA, int startIndexA , int strideA,
									short []dataB, int startIndexB , int strideB,
									int rows , int columns ) {

		return BoofConcurrency.sum(0,rows,int.class,y->{
			int total = 0;
			int indexA = startIndexA + y * strideA;
			int indexB = startIndexB + y * strideB;
			
			int indexEnd = indexA+columns;
			
			for (; indexA < indexEnd; indexA++,indexB++) {
				int difference = (dataA[indexA])-(dataB[indexB]);
				total += difference*difference;
			}
		return total;}).intValue()/ (double)(rows*columns);
	}

	public static double meanDiffAbs(short []dataA, int startIndexA , int strideA,
									short []dataB, int startIndexB , int strideB,
									int rows , int columns ) {

		return BoofConcurrency.sum(0,rows,int.class,y->{
			int total = 0;
			int indexA = startIndexA + y * strideA;
			int indexB = startIndexB + y * strideB;
			
			int indexEnd = indexA+columns;
			
			for (; indexA < indexEnd; indexA++,indexB++) {
				int difference = (dataA[indexA])-(dataB[indexB]);
				total += Math.abs(difference);
			}
		return total;}).intValue()/ (double)(rows*columns);
	}

	public static int sum( GrayS16 img ) {

		final int rows = img.height;
		final int columns = img.width;


		return BoofConcurrency.sum(0,img.height,int.class,y->{
			int total = 0;
			int index = img.startIndex + y * img.stride;
			
			int indexEnd = index+columns;
			for (; index < indexEnd; index++ ) {
				total += img.data[index] ;
			}
		return total;}).intValue();
	}

	public static int sumAbs( GrayS16 img ) {

		final int rows = img.height;
		final int columns = img.width;


		return BoofConcurrency.sum(0,img.height,int.class,y->{
			int total = 0;
			int index = img.startIndex + y * img.stride;
			
			int indexEnd = index+columns;
			for (; index < indexEnd; index++ ) {
				total += Math.abs(img.data[index] );
			}
		return total;}).intValue();
	}

	public static int sum( InterleavedS16 img ) {

		final int rows = img.height;
		final int columns = img.width*img.numBands;


		return BoofConcurrency.sum(0,img.height,int.class,y->{
			int total = 0;
			int index = img.startIndex + y * img.stride;
			
			int indexEnd = index+columns;
			for (; index < indexEnd; index++ ) {
				total += img.data[index] ;
			}
		return total;}).intValue();
	}

	public static int sumAbs( InterleavedS16 img ) {

		final int rows = img.height;
		final int columns = img.width*img.numBands;


		return BoofConcurrency.sum(0,img.height,int.class,y->{
			int total = 0;
			int index = img.startIndex + y * img.stride;
			
			int indexEnd = index+columns;
			for (; index < indexEnd; index++ ) {
				total += Math.abs(img.data[index] );
			}
		return total;}).intValue();
	}

	public static double variance( GrayS16 img , double mean ) {


		return BoofConcurrency.sum(0,img.height,double.class,y->{
			double total = 0;
			int index = img.getStartIndex() + y * img.getStride();

			int indexEnd = index+img.width;
			// for(int x = 0; x < img.width; x++ ) {
			for (; index < indexEnd; index++ ) {
				double d = (img.data[index]) - mean; 
				total += d*d;
			}
		return total;}).intValue()/(img.width*img.height);
	}

	public static void histogram( GrayS16 input , int minValue , int[] histogram ) {
		Arrays.fill(histogram,0);

		final List<int[]> list = new ArrayList<>();
		BoofConcurrency.loopBlocks(0,input.height,(y0,y1)->{
		final int[] h = new int[histogram.length];
		for( int y = y0; y < y1; y++ ) {
			int index = input.startIndex + y*input.stride;
			int end = index + input.width;

			while( index < end ) {
				h[(input.data[index++]) - minValue ]++;
			}
		}
		synchronized(list){list.add(h);}});
		for (int i = 0; i < list.size(); i++) {
			int[] h = list.get(i);
			for (int j = 0; j < histogram.length; j++) {
				histogram[j] += h[j];
			}
		}
	}

	public static void histogramScaled( GrayS16 input , int minValue , int maxValue, int[] histogram ) {
		Arrays.fill(histogram,0);

		final int histLength = histogram.length;
		final int rangeValue = maxValue-minValue+1;
		
		final List<int[]> list = new ArrayList<>();
		BoofConcurrency.loopBlocks(0,input.height,(y0,y1)->{
		final int[] h = new int[histogram.length];
		for( int y = y0; y < y1; y++ ) {
			int index = input.startIndex + y*input.stride;
			int end = index + input.width;

			while( index < end ) {
				h[(int)(histLength*((input.data[index++]) - minValue)/rangeValue) ]++;
			}
		}
		synchronized(list){list.add(h);}});
		for (int i = 0; i < list.size(); i++) {
			int[] h = list.get(i);
			for (int j = 0; j < histogram.length; j++) {
				histogram[j] += h[j];
			}
		}
	}

	public static int min( int[] array , int startIndex , int rows , int columns , int stride ) {

		final int _output = array[startIndex];

		return BoofConcurrency.min(0,rows,int.class,y->{
			int output = _output;
			int index = startIndex + y*stride;
			int end = index + columns;

			for( ; index < end; index++ ) {
				int v = array[index] ;
				if( v < output )
					output = v;
			}
		return output;}).intValue();
	}

	public static int max( int[] array , int startIndex , int rows , int columns , int stride ) {

		final int _output = array[startIndex];

		return BoofConcurrency.max(0,rows,int.class,y->{
			int output = _output;
			int index = startIndex + y*stride;
			int end = index + columns;

			for( ; index < end; index++ ) {
				int v = array[index] ;
				if( v > output )
					output = v;
			}
		return output;}).intValue();
	}

	public static int maxAbs( int[] array , int startIndex , int rows , int columns , int stride ) {

		final int _output = array[startIndex];

		return BoofConcurrency.max(0,rows,int.class,y->{
			int output = _output;
			int index = startIndex + y*stride;
			int end = index + columns;

			for( ; index < end; index++ ) {
				int v = Math.abs(array[index]);
				if( v > output )
					output = v;
			}
		return output;}).intValue();
	}

	public static double meanDiffSq(int []dataA, int startIndexA , int strideA,
									int []dataB, int startIndexB , int strideB,
									int rows , int columns ) {

		return BoofConcurrency.sum(0,rows,int.class,y->{
			int total = 0;
			int indexA = startIndexA + y * strideA;
			int indexB = startIndexB + y * strideB;
			
			int indexEnd = indexA+columns;
			
			for (; indexA < indexEnd; indexA++,indexB++) {
				int difference = (dataA[indexA])-(dataB[indexB]);
				total += difference*difference;
			}
		return total;}).intValue()/ (double)(rows*columns);
	}

	public static double meanDiffAbs(int []dataA, int startIndexA , int strideA,
									int []dataB, int startIndexB , int strideB,
									int rows , int columns ) {

		return BoofConcurrency.sum(0,rows,int.class,y->{
			int total = 0;
			int indexA = startIndexA + y * strideA;
			int indexB = startIndexB + y * strideB;
			
			int indexEnd = indexA+columns;
			
			for (; indexA < indexEnd; indexA++,indexB++) {
				int difference = (dataA[indexA])-(dataB[indexB]);
				total += Math.abs(difference);
			}
		return total;}).intValue()/ (double)(rows*columns);
	}

	public static int sum( GrayS32 img ) {

		final int rows = img.height;
		final int columns = img.width;


		return BoofConcurrency.sum(0,img.height,int.class,y->{
			int total = 0;
			int index = img.startIndex + y * img.stride;
			
			int indexEnd = index+columns;
			for (; index < indexEnd; index++ ) {
				total += img.data[index] ;
			}
		return total;}).intValue();
	}

	public static int sumAbs( GrayS32 img ) {

		final int rows = img.height;
		final int columns = img.width;


		return BoofConcurrency.sum(0,img.height,int.class,y->{
			int total = 0;
			int index = img.startIndex + y * img.stride;
			
			int indexEnd = index+columns;
			for (; index < indexEnd; index++ ) {
				total += Math.abs(img.data[index] );
			}
		return total;}).intValue();
	}

	public static int sum( InterleavedS32 img ) {

		final int rows = img.height;
		final int columns = img.width*img.numBands;


		return BoofConcurrency.sum(0,img.height,int.class,y->{
			int total = 0;
			int index = img.startIndex + y * img.stride;
			
			int indexEnd = index+columns;
			for (; index < indexEnd; index++ ) {
				total += img.data[index] ;
			}
		return total;}).intValue();
	}

	public static int sumAbs( InterleavedS32 img ) {

		final int rows = img.height;
		final int columns = img.width*img.numBands;


		return BoofConcurrency.sum(0,img.height,int.class,y->{
			int total = 0;
			int index = img.startIndex + y * img.stride;
			
			int indexEnd = index+columns;
			for (; index < indexEnd; index++ ) {
				total += Math.abs(img.data[index] );
			}
		return total;}).intValue();
	}

	public static double variance( GrayS32 img , double mean ) {


		return BoofConcurrency.sum(0,img.height,double.class,y->{
			double total = 0;
			int index = img.getStartIndex() + y * img.getStride();

			int indexEnd = index+img.width;
			// for(int x = 0; x < img.width; x++ ) {
			for (; index < indexEnd; index++ ) {
				double d = (img.data[index]) - mean; 
				total += d*d;
			}
		return total;}).intValue()/(img.width*img.height);
	}

	public static void histogram( GrayS32 input , int minValue , int[] histogram ) {
		Arrays.fill(histogram,0);

		final List<int[]> list = new ArrayList<>();
		BoofConcurrency.loopBlocks(0,input.height,(y0,y1)->{
		final int[] h = new int[histogram.length];
		for( int y = y0; y < y1; y++ ) {
			int index = input.startIndex + y*input.stride;
			int end = index + input.width;

			while( index < end ) {
				h[(input.data[index++]) - minValue ]++;
			}
		}
		synchronized(list){list.add(h);}});
		for (int i = 0; i < list.size(); i++) {
			int[] h = list.get(i);
			for (int j = 0; j < histogram.length; j++) {
				histogram[j] += h[j];
			}
		}
	}

	public static void histogramScaled( GrayS32 input , int minValue , int maxValue, int[] histogram ) {
		Arrays.fill(histogram,0);

		final int histLength = histogram.length;
		final int rangeValue = maxValue-minValue+1;
		
		final List<int[]> list = new ArrayList<>();
		BoofConcurrency.loopBlocks(0,input.height,(y0,y1)->{
		final int[] h = new int[histogram.length];
		for( int y = y0; y < y1; y++ ) {
			int index = input.startIndex + y*input.stride;
			int end = index + input.width;

			while( index < end ) {
				h[(int)(histLength*((input.data[index++]) - minValue)/rangeValue) ]++;
			}
		}
		synchronized(list){list.add(h);}});
		for (int i = 0; i < list.size(); i++) {
			int[] h = list.get(i);
			for (int j = 0; j < histogram.length; j++) {
				histogram[j] += h[j];
			}
		}
	}

	public static long min( long[] array , int startIndex , int rows , int columns , int stride ) {

		final long _output = array[startIndex];

		return BoofConcurrency.min(0,rows,long.class,y->{
			long output = _output;
			int index = startIndex + y*stride;
			int end = index + columns;

			for( ; index < end; index++ ) {
				long v = array[index] ;
				if( v < output )
					output = v;
			}
		return output;}).longValue();
	}

	public static long max( long[] array , int startIndex , int rows , int columns , int stride ) {

		final long _output = array[startIndex];

		return BoofConcurrency.max(0,rows,long.class,y->{
			long output = _output;
			int index = startIndex + y*stride;
			int end = index + columns;

			for( ; index < end; index++ ) {
				long v = array[index] ;
				if( v > output )
					output = v;
			}
		return output;}).longValue();
	}

	public static long maxAbs( long[] array , int startIndex , int rows , int columns , int stride ) {

		final long _output = array[startIndex];

		return BoofConcurrency.max(0,rows,long.class,y->{
			long output = _output;
			int index = startIndex + y*stride;
			int end = index + columns;

			for( ; index < end; index++ ) {
				long v = Math.abs(array[index]);
				if( v > output )
					output = v;
			}
		return output;}).longValue();
	}

	public static double meanDiffSq(long []dataA, int startIndexA , int strideA,
									long []dataB, int startIndexB , int strideB,
									int rows , int columns ) {

		return BoofConcurrency.sum(0,rows,long.class,y->{
			long total = 0;
			int indexA = startIndexA + y * strideA;
			int indexB = startIndexB + y * strideB;
			
			int indexEnd = indexA+columns;
			
			for (; indexA < indexEnd; indexA++,indexB++) {
				long difference = (dataA[indexA])-(dataB[indexB]);
				total += difference*difference;
			}
		return total;}).longValue()/ (double)(rows*columns);
	}

	public static double meanDiffAbs(long []dataA, int startIndexA , int strideA,
									long []dataB, int startIndexB , int strideB,
									int rows , int columns ) {

		return BoofConcurrency.sum(0,rows,long.class,y->{
			long total = 0;
			int indexA = startIndexA + y * strideA;
			int indexB = startIndexB + y * strideB;
			
			int indexEnd = indexA+columns;
			
			for (; indexA < indexEnd; indexA++,indexB++) {
				long difference = (dataA[indexA])-(dataB[indexB]);
				total += Math.abs(difference);
			}
		return total;}).longValue()/ (double)(rows*columns);
	}

	public static long sum( GrayS64 img ) {

		final int rows = img.height;
		final int columns = img.width;


		return BoofConcurrency.sum(0,img.height,long.class,y->{
			long total = 0;
			int index = img.startIndex + y * img.stride;
			
			int indexEnd = index+columns;
			for (; index < indexEnd; index++ ) {
				total += img.data[index] ;
			}
		return total;}).longValue();
	}

	public static long sumAbs( GrayS64 img ) {

		final int rows = img.height;
		final int columns = img.width;


		return BoofConcurrency.sum(0,img.height,long.class,y->{
			long total = 0;
			int index = img.startIndex + y * img.stride;
			
			int indexEnd = index+columns;
			for (; index < indexEnd; index++ ) {
				total += Math.abs(img.data[index] );
			}
		return total;}).longValue();
	}

	public static long sum( InterleavedS64 img ) {

		final int rows = img.height;
		final int columns = img.width*img.numBands;


		return BoofConcurrency.sum(0,img.height,long.class,y->{
			long total = 0;
			int index = img.startIndex + y * img.stride;
			
			int indexEnd = index+columns;
			for (; index < indexEnd; index++ ) {
				total += img.data[index] ;
			}
		return total;}).longValue();
	}

	public static long sumAbs( InterleavedS64 img ) {

		final int rows = img.height;
		final int columns = img.width*img.numBands;


		return BoofConcurrency.sum(0,img.height,long.class,y->{
			long total = 0;
			int index = img.startIndex + y * img.stride;
			
			int indexEnd = index+columns;
			for (; index < indexEnd; index++ ) {
				total += Math.abs(img.data[index] );
			}
		return total;}).longValue();
	}

	public static double variance( GrayS64 img , double mean ) {


		return BoofConcurrency.sum(0,img.height,double.class,y->{
			double total = 0;
			int index = img.getStartIndex() + y * img.getStride();

			int indexEnd = index+img.width;
			// for(int x = 0; x < img.width; x++ ) {
			for (; index < indexEnd; index++ ) {
				double d = (img.data[index]) - mean; 
				total += d*d;
			}
		return total;}).longValue()/(img.width*img.height);
	}

	public static void histogram( GrayS64 input , long minValue , int[] histogram ) {
		Arrays.fill(histogram,0);

		final List<int[]> list = new ArrayList<>();
		BoofConcurrency.loopBlocks(0,input.height,(y0,y1)->{
		final int[] h = new int[histogram.length];
		for( int y = y0; y < y1; y++ ) {
			int index = input.startIndex + y*input.stride;
			int end = index + input.width;

			while( index < end ) {
				h[(int)(input.data[index++] - minValue)]++;
			}
		}
		synchronized(list){list.add(h);}});
		for (int i = 0; i < list.size(); i++) {
			int[] h = list.get(i);
			for (int j = 0; j < histogram.length; j++) {
				histogram[j] += h[j];
			}
		}
	}

	public static void histogramScaled( GrayS64 input , long minValue , long maxValue, int[] histogram ) {
		Arrays.fill(histogram,0);

		final long histLength = histogram.length;
		final long rangeValue = maxValue-minValue+1;
		
		final List<int[]> list = new ArrayList<>();
		BoofConcurrency.loopBlocks(0,input.height,(y0,y1)->{
		final int[] h = new int[histogram.length];
		for( int y = y0; y < y1; y++ ) {
			int index = input.startIndex + y*input.stride;
			int end = index + input.width;

			while( index < end ) {
				h[(int)(histLength*(input.data[index++] - minValue)/rangeValue)]++;
			}
		}
		synchronized(list){list.add(h);}});
		for (int i = 0; i < list.size(); i++) {
			int[] h = list.get(i);
			for (int j = 0; j < histogram.length; j++) {
				histogram[j] += h[j];
			}
		}
	}

	public static float min( float[] array , int startIndex , int rows , int columns , int stride ) {

		final float _output = array[startIndex];

		return BoofConcurrency.min(0,rows,float.class,y->{
			float output = _output;
			int index = startIndex + y*stride;
			int end = index + columns;

			for( ; index < end; index++ ) {
				float v = array[index] ;
				if( v < output )
					output = v;
			}
		return output;}).floatValue();
	}

	public static float max( float[] array , int startIndex , int rows , int columns , int stride ) {

		final float _output = array[startIndex];

		return BoofConcurrency.max(0,rows,float.class,y->{
			float output = _output;
			int index = startIndex + y*stride;
			int end = index + columns;

			for( ; index < end; index++ ) {
				float v = array[index] ;
				if( v > output )
					output = v;
			}
		return output;}).floatValue();
	}

	public static float maxAbs( float[] array , int startIndex , int rows , int columns , int stride ) {

		final float _output = array[startIndex];

		return BoofConcurrency.max(0,rows,float.class,y->{
			float output = _output;
			int index = startIndex + y*stride;
			int end = index + columns;

			for( ; index < end; index++ ) {
				float v = Math.abs(array[index]);
				if( v > output )
					output = v;
			}
		return output;}).floatValue();
	}

	public static double meanDiffSq(float []dataA, int startIndexA , int strideA,
									float []dataB, int startIndexB , int strideB,
									int rows , int columns ) {

		return BoofConcurrency.sum(0,rows,float.class,y->{
			float total = 0;
			int indexA = startIndexA + y * strideA;
			int indexB = startIndexB + y * strideB;
			
			int indexEnd = indexA+columns;
			
			for (; indexA < indexEnd; indexA++,indexB++) {
				float difference = (dataA[indexA])-(dataB[indexB]);
				total += difference*difference;
			}
		return total;}).floatValue()/ (double)(rows*columns);
	}

	public static double meanDiffAbs(float []dataA, int startIndexA , int strideA,
									float []dataB, int startIndexB , int strideB,
									int rows , int columns ) {

		return BoofConcurrency.sum(0,rows,float.class,y->{
			float total = 0;
			int indexA = startIndexA + y * strideA;
			int indexB = startIndexB + y * strideB;
			
			int indexEnd = indexA+columns;
			
			for (; indexA < indexEnd; indexA++,indexB++) {
				float difference = (dataA[indexA])-(dataB[indexB]);
				total += Math.abs(difference);
			}
		return total;}).floatValue()/ (double)(rows*columns);
	}

	public static float sum( GrayF32 img ) {

		final int rows = img.height;
		final int columns = img.width;


		return BoofConcurrency.sum(0,img.height,float.class,y->{
			float total = 0;
			int index = img.startIndex + y * img.stride;
			
			int indexEnd = index+columns;
			for (; index < indexEnd; index++ ) {
				total += img.data[index] ;
			}
		return total;}).floatValue();
	}

	public static float sumAbs( GrayF32 img ) {

		final int rows = img.height;
		final int columns = img.width;


		return BoofConcurrency.sum(0,img.height,float.class,y->{
			float total = 0;
			int index = img.startIndex + y * img.stride;
			
			int indexEnd = index+columns;
			for (; index < indexEnd; index++ ) {
				total += Math.abs(img.data[index] );
			}
		return total;}).floatValue();
	}

	public static float sum( InterleavedF32 img ) {

		final int rows = img.height;
		final int columns = img.width*img.numBands;


		return BoofConcurrency.sum(0,img.height,float.class,y->{
			float total = 0;
			int index = img.startIndex + y * img.stride;
			
			int indexEnd = index+columns;
			for (; index < indexEnd; index++ ) {
				total += img.data[index] ;
			}
		return total;}).floatValue();
	}

	public static float sumAbs( InterleavedF32 img ) {

		final int rows = img.height;
		final int columns = img.width*img.numBands;


		return BoofConcurrency.sum(0,img.height,float.class,y->{
			float total = 0;
			int index = img.startIndex + y * img.stride;
			
			int indexEnd = index+columns;
			for (; index < indexEnd; index++ ) {
				total += Math.abs(img.data[index] );
			}
		return total;}).floatValue();
	}

	public static float variance( GrayF32 img , float mean ) {


		return BoofConcurrency.sum(0,img.height,float.class,y->{
			float total = 0;
			int index = img.getStartIndex() + y * img.getStride();

			int indexEnd = index+img.width;
			// for(int x = 0; x < img.width; x++ ) {
			for (; index < indexEnd; index++ ) {
				float d = (img.data[index]) - mean; 
				total += d*d;
			}
		return total;}).floatValue()/(img.width*img.height);
	}

	public static void histogram( GrayF32 input , float minValue , int[] histogram ) {
		Arrays.fill(histogram,0);

		final List<int[]> list = new ArrayList<>();
		BoofConcurrency.loopBlocks(0,input.height,(y0,y1)->{
		final int[] h = new int[histogram.length];
		for( int y = y0; y < y1; y++ ) {
			int index = input.startIndex + y*input.stride;
			int end = index + input.width;

			while( index < end ) {
				h[(int)(input.data[index++] - minValue)]++;
			}
		}
		synchronized(list){list.add(h);}});
		for (int i = 0; i < list.size(); i++) {
			int[] h = list.get(i);
			for (int j = 0; j < histogram.length; j++) {
				histogram[j] += h[j];
			}
		}
	}

	public static void histogramScaled( GrayF32 input , float minValue , float maxValue, int[] histogram ) {
		Arrays.fill(histogram,0);

		final float histLength = histogram.length;
		final float rangeValue = maxValue-minValue+1;
		
		final List<int[]> list = new ArrayList<>();
		BoofConcurrency.loopBlocks(0,input.height,(y0,y1)->{
		final int[] h = new int[histogram.length];
		for( int y = y0; y < y1; y++ ) {
			int index = input.startIndex + y*input.stride;
			int end = index + input.width;

			while( index < end ) {
				h[(int)(histLength*(input.data[index++] - minValue)/rangeValue)]++;
			}
		}
		synchronized(list){list.add(h);}});
		for (int i = 0; i < list.size(); i++) {
			int[] h = list.get(i);
			for (int j = 0; j < histogram.length; j++) {
				histogram[j] += h[j];
			}
		}
	}

	public static double min( double[] array , int startIndex , int rows , int columns , int stride ) {

		final double _output = array[startIndex];

		return BoofConcurrency.min(0,rows,double.class,y->{
			double output = _output;
			int index = startIndex + y*stride;
			int end = index + columns;

			for( ; index < end; index++ ) {
				double v = array[index] ;
				if( v < output )
					output = v;
			}
		return output;}).doubleValue();
	}

	public static double max( double[] array , int startIndex , int rows , int columns , int stride ) {

		final double _output = array[startIndex];

		return BoofConcurrency.max(0,rows,double.class,y->{
			double output = _output;
			int index = startIndex + y*stride;
			int end = index + columns;

			for( ; index < end; index++ ) {
				double v = array[index] ;
				if( v > output )
					output = v;
			}
		return output;}).doubleValue();
	}

	public static double maxAbs( double[] array , int startIndex , int rows , int columns , int stride ) {

		final double _output = array[startIndex];

		return BoofConcurrency.max(0,rows,double.class,y->{
			double output = _output;
			int index = startIndex + y*stride;
			int end = index + columns;

			for( ; index < end; index++ ) {
				double v = Math.abs(array[index]);
				if( v > output )
					output = v;
			}
		return output;}).doubleValue();
	}

	public static double meanDiffSq(double []dataA, int startIndexA , int strideA,
									double []dataB, int startIndexB , int strideB,
									int rows , int columns ) {

		return BoofConcurrency.sum(0,rows,double.class,y->{
			double total = 0;
			int indexA = startIndexA + y * strideA;
			int indexB = startIndexB + y * strideB;
			
			int indexEnd = indexA+columns;
			
			for (; indexA < indexEnd; indexA++,indexB++) {
				double difference = (dataA[indexA])-(dataB[indexB]);
				total += difference*difference;
			}
		return total;}).doubleValue()/ (double)(rows*columns);
	}

	public static double meanDiffAbs(double []dataA, int startIndexA , int strideA,
									double []dataB, int startIndexB , int strideB,
									int rows , int columns ) {

		return BoofConcurrency.sum(0,rows,double.class,y->{
			double total = 0;
			int indexA = startIndexA + y * strideA;
			int indexB = startIndexB + y * strideB;
			
			int indexEnd = indexA+columns;
			
			for (; indexA < indexEnd; indexA++,indexB++) {
				double difference = (dataA[indexA])-(dataB[indexB]);
				total += Math.abs(difference);
			}
		return total;}).doubleValue()/ (double)(rows*columns);
	}

	public static double sum( GrayF64 img ) {

		final int rows = img.height;
		final int columns = img.width;


		return BoofConcurrency.sum(0,img.height,double.class,y->{
			double total = 0;
			int index = img.startIndex + y * img.stride;
			
			int indexEnd = index+columns;
			for (; index < indexEnd; index++ ) {
				total += img.data[index] ;
			}
		return total;}).doubleValue();
	}

	public static double sumAbs( GrayF64 img ) {

		final int rows = img.height;
		final int columns = img.width;


		return BoofConcurrency.sum(0,img.height,double.class,y->{
			double total = 0;
			int index = img.startIndex + y * img.stride;
			
			int indexEnd = index+columns;
			for (; index < indexEnd; index++ ) {
				total += Math.abs(img.data[index] );
			}
		return total;}).doubleValue();
	}

	public static double sum( InterleavedF64 img ) {

		final int rows = img.height;
		final int columns = img.width*img.numBands;


		return BoofConcurrency.sum(0,img.height,double.class,y->{
			double total = 0;
			int index = img.startIndex + y * img.stride;
			
			int indexEnd = index+columns;
			for (; index < indexEnd; index++ ) {
				total += img.data[index] ;
			}
		return total;}).doubleValue();
	}

	public static double sumAbs( InterleavedF64 img ) {

		final int rows = img.height;
		final int columns = img.width*img.numBands;


		return BoofConcurrency.sum(0,img.height,double.class,y->{
			double total = 0;
			int index = img.startIndex + y * img.stride;
			
			int indexEnd = index+columns;
			for (; index < indexEnd; index++ ) {
				total += Math.abs(img.data[index] );
			}
		return total;}).doubleValue();
	}

	public static double variance( GrayF64 img , double mean ) {


		return BoofConcurrency.sum(0,img.height,double.class,y->{
			double total = 0;
			int index = img.getStartIndex() + y * img.getStride();

			int indexEnd = index+img.width;
			// for(int x = 0; x < img.width; x++ ) {
			for (; index < indexEnd; index++ ) {
				double d = (img.data[index]) - mean; 
				total += d*d;
			}
		return total;}).doubleValue()/(img.width*img.height);
	}

	public static void histogram( GrayF64 input , double minValue , int[] histogram ) {
		Arrays.fill(histogram,0);

		final List<int[]> list = new ArrayList<>();
		BoofConcurrency.loopBlocks(0,input.height,(y0,y1)->{
		final int[] h = new int[histogram.length];
		for( int y = y0; y < y1; y++ ) {
			int index = input.startIndex + y*input.stride;
			int end = index + input.width;

			while( index < end ) {
				h[(int)(input.data[index++] - minValue)]++;
			}
		}
		synchronized(list){list.add(h);}});
		for (int i = 0; i < list.size(); i++) {
			int[] h = list.get(i);
			for (int j = 0; j < histogram.length; j++) {
				histogram[j] += h[j];
			}
		}
	}

	public static void histogramScaled( GrayF64 input , double minValue , double maxValue, int[] histogram ) {
		Arrays.fill(histogram,0);

		final double histLength = histogram.length;
		final double rangeValue = maxValue-minValue+1;
		
		final List<int[]> list = new ArrayList<>();
		BoofConcurrency.loopBlocks(0,input.height,(y0,y1)->{
		final int[] h = new int[histogram.length];
		for( int y = y0; y < y1; y++ ) {
			int index = input.startIndex + y*input.stride;
			int end = index + input.width;

			while( index < end ) {
				h[(int)(histLength*(input.data[index++] - minValue)/rangeValue)]++;
			}
		}
		synchronized(list){list.add(h);}});
		for (int i = 0; i < list.size(); i++) {
			int[] h = list.get(i);
			for (int j = 0; j < histogram.length; j++) {
				histogram[j] += h[j];
			}
		}
	}

}
