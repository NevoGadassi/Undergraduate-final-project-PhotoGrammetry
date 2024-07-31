/*
 * Copyright (c) 2011-2020, Peter Abeles. All Rights Reserved.
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

package boofcv.alg.filter.convolve.down;

import boofcv.generate.CodeGeneratorBase;

import java.io.FileNotFoundException;

/**
 * Creates several classes that are highly optimized versions of {@link ConvolveDownNoBorderStandard}, where
 * the kernel is stored as local variables the loops unrolled.
 *
 * @author Peter Abeles
 */
public class GenerateConvolveDownNoBorderUnrolled extends CodeGeneratorBase {

	final static int numUnrolled = 5;

	String className;
	String typeKernel;
	String typeInput;
	String typeOutput;
	String dataKernel;
	String dataInput;
	String dataOutput;
	String bitWise;
	String sumType;
	boolean hasDivisor;
	String declareHalf;
	String divide;

	@Override
	public void generateCode() throws FileNotFoundException {
		createF32();
		createU8_I8_Div();
		createU8_I16();
		createS16_I16();
		createS16_I16_Div();
	}

	public void createF32() throws FileNotFoundException {
		className = "ConvolveDownNoBorderUnrolled_F32_F32";
		typeKernel = "F32";
		typeInput = "GrayF32";
		typeOutput = "GrayF32";
		dataKernel = "float";
		dataInput = "float";
		dataOutput = "float";
		sumType = "float";
		bitWise = "";
		hasDivisor = false;

		createFile();
	}

	public void createU8_I8_Div() throws FileNotFoundException {
		className = "ConvolveDownNoBorderUnrolled_U8_I8_Div";
		typeKernel = "I32";
		typeInput = "GrayU8";
		typeOutput = "ImageInt8";
		dataKernel = "int";
		dataInput = "byte";
		dataOutput = "byte";
		sumType = "int";
		bitWise = " & 0xFF";
		hasDivisor = true;

		createFile();
	}

	public void createU8_I16() throws FileNotFoundException {
		className = "ConvolveDownNoBorderUnrolled_U8_I16";
		typeKernel = "I32";
		typeInput = "GrayU8";
		typeOutput = "ImageInt16";
		dataKernel = "int";
		dataInput = "byte";
		dataOutput = "short";
		sumType = "int";
		bitWise = " & 0xFF";
		hasDivisor = false;

		createFile();
	}

	public void createS16_I16() throws FileNotFoundException {
		className = "ConvolveDownNoBorderUnrolled_S16_I16";
		typeKernel = "I32";
		typeInput = "GrayS16";
		typeOutput = "ImageInt16";
		dataKernel = "int";
		dataInput = "short";
		dataOutput = "short";
		sumType = "int";
		bitWise = "";
		hasDivisor = false;

		createFile();
	}

	public void createS16_I16_Div() throws FileNotFoundException {
		className = "ConvolveDownNoBorderUnrolled_S16_I16_Div";
		typeKernel = "I32";
		typeInput = "GrayS16";
		typeOutput = "ImageInt16";
		dataKernel = "int";
		dataInput = "short";
		dataOutput = "short";
		sumType = "int";
		bitWise = "";
		hasDivisor = true;

		createFile();
	}

	public void createFile() throws FileNotFoundException {
		setOutputFile(className);

		printPreamble();
		createMaster("horizontal", 1, hasDivisor);
		createMaster("vertical", 1, hasDivisor);
		createMaster("convolve", 2, hasDivisor);

		for (int i = 0; i < numUnrolled; i++) {
			addHorizontal(3 + i*2, hasDivisor);
		}
		for (int i = 0; i < numUnrolled; i++) {
			addVertical(3 + i*2, hasDivisor);
		}
		for (int i = 0; i < numUnrolled; i++) {
			if (hasDivisor)
				addConvolveDiv(3 + i*2);
			else
				addConvolve(3 + i*2);
		}

		out.println("}");
	}

	public void printPreamble() {
		out.print("import boofcv.struct.convolve.Kernel1D_" + typeKernel + ";\n");
		out.print("import boofcv.struct.convolve.Kernel2D_" + typeKernel + ";\n");
		out.print("import boofcv.struct.image." + typeInput + ";\n");
		if (typeInput.compareTo(typeOutput) != 0)
			out.print("import boofcv.struct.image." + typeOutput + ";\n");
		out.print("\n" +
				"/**\n" +
				" * <p>\n" +
				" * Unrolls the convolution kernel to improve runtime performance by reducing array accesses.\n" +
				" * </p>\n" +
				" * \n" +
				generateDocString("Peter Abeles") +
				" */\n" +
				"public class " + className + " {\n");
	}

	public void createMaster( String opName, int kernelDOF, boolean hasDivisor ) {
		declareHalf = hasDivisor ? "\t\tint halfDivisor = divisor/2;\n" : "";
		divide = hasDivisor ? "(total+halfDivisor)/divisor" : "total/divisor";
		String kernel = "Kernel" + kernelDOF + "D_" + typeKernel;

		out.print("\tpublic static boolean " + opName + "( " + kernel + " kernel ,\n" +
				"\t\t\t\t\t\t\t\t   " + typeInput + " image, " + typeOutput + " dest ,");

		if (kernelDOF == 1) {
			if (hasDivisor) {
				out.print(" int skip, int divisor) {\n");
			} else {
				out.print(" int skip) {\n");
			}
		} else {
			if (hasDivisor) {
				out.print(" int skip , int divisor ) {\n");
			} else {
				out.print(" int skip ) {\n");
			}
		}

		out.print("\t\tswitch( kernel.width ) {\n");
		for (int i = 0; i < numUnrolled; i++) {
			int num = 3 + i*2;
			out.print("\t\t\tcase " + num + ":\n");
			if (hasDivisor)
				out.print("\t\t\t\t" + opName + num + "(kernel,image,dest,skip,divisor);\n");
			else
				out.print("\t\t\t\t" + opName + num + "(kernel,image,dest,skip);\n");
			out.print("\t\t\t\tbreak;\n" +
					"\n");
		}
		out.print("\t\t\tdefault:\n" +
				"\t\t\t\treturn false;\n" +
				"\t\t}\n" +
				"\t\treturn true;\n" +
				"\t}\n\n");
	}

	public void addHorizontal( int num, boolean hasDivisor ) {
		String typeCast = generateTypeCast();

		out.print("\tpublic static void horizontal" + num + "( Kernel1D_" + typeKernel + " kernel ,\n" +
				"\t\t\t\t\t\t\t\t\t" + typeInput + " input, " + typeOutput + " output ,\n");
		if (hasDivisor)
			out.print("\t\t\t\t\t\t\t\t\tint skip , int divisor ) {\n");
		else
			out.print("\t\t\t\t\t\t\t\t\tint skip ) {\n");

		out.print("\t\tfinal " + dataInput + "[] dataSrc = input.data;\n" +
				"\t\tfinal " + dataOutput + "[] dataDst = output.data;\n" +
				"\n");
		for (int i = 0; i < num; i++) {
			out.printf("\t\tfinal " + dataKernel + " k%d = kernel.data[%d];\n", i + 1, i);
		}
		out.print("\n" +
				"\t\tfinal int radius = kernel.getRadius();\n" +
				"\n" +
				"\t\tfinal int widthEnd = UtilDownConvolve.computeMaxSide(input.width,skip,radius);\n" +
				"\t\tfinal int height = input.getHeight();\n" +
				declareHalf +
				"\n" +
				"\t\tfinal int offsetX = UtilDownConvolve.computeOffset(skip,radius);\n" +
				"\n" +
				"\t\tfor( int i = 0; i < height; i++ ) {\n" +
				"\t\t\tint indexDst = output.startIndex + i*output.stride + offsetX/skip;\n" +
				"\t\t\tint j = input.startIndex + i*input.stride - radius;\n" +
				"\t\t\tfinal int jEnd = j+widthEnd;\n" +
				"\n" +
				"\t\t\tfor( j += offsetX; j <= jEnd; j += skip ) {\n" +
				"\t\t\t\tint indexSrc = j;\n" +
				"\n" +
				"\t\t\t\t" + sumType + " total = (dataSrc[indexSrc++] " + bitWise + ") * k1;\n");
		for (int i = 1; i < num - 1; i++) {
			out.printf("\t\t\t\ttotal += (dataSrc[indexSrc++]" + bitWise + ")*k%d;\n", i + 1);
		}
		out.printf("\t\t\t\ttotal += (dataSrc[indexSrc]" + bitWise + ")*k%d;\n", num);
		out.printf("\n");
		if (hasDivisor) {
			out.print("\t\t\t\tdataDst[indexDst++] = " + typeCast + "(" + divide + ");\n");
		} else {
			out.print("\t\t\t\tdataDst[indexDst++] = " + typeCast + "total;\n");
		}

		out.print("\t\t\t}\n" +
				"\t\t}\n" +
				"\t}\n\n");
	}

	public void addVertical( int num, boolean hasDivisor ) {
		String typeCast = generateTypeCast();

		out.print("\tpublic static void vertical" + num + "( Kernel1D_" + typeKernel + " kernel,\n" +
				"\t\t\t\t\t\t\t\t " + typeInput + " input, " + typeOutput + " output,\n");
		if (hasDivisor)
			out.print("\t\t\t\t\t\t\t\t\tint skip , int divisor) {\n");
		else
			out.print("\t\t\t\t\t\t\t\t\tint skip ) {\n");
		out.print("\t\tfinal " + dataInput + "[] dataSrc = input.data;\n" +
				"\t\tfinal " + dataOutput + "[] dataDst = output.data;\n" +
				"\n");
		for (int i = 0; i < num; i++) {
			out.printf("\t\tfinal " + dataKernel + " k%d = kernel.data[%d];\n", i + 1, i);
		}

		out.print("\n" +
				"\t\tfinal int radius = kernel.getRadius();\n" +
				"\n" +
				"\t\tfinal int width = input.width;\n" +
				"\t\tfinal int heightEnd = UtilDownConvolve.computeMaxSide(input.height,skip,radius);\n" +
				declareHalf +
				"\n" +
				"\t\tfinal int offsetY = UtilDownConvolve.computeOffset(skip,radius);\n" +
				"\n" +
				"\t\tfor( int y = offsetY; y <= heightEnd; y += skip ) {\n" +
				"\t\t\tint indexDst = output.startIndex + (y/skip)*output.stride;\n" +
				"\t\t\tint i = input.startIndex + (y-radius)*input.stride;\n" +
				"\t\t\tfinal int iEnd = i + width;\n" +
				"\n" +
				"\t\t\tfor( ; i < iEnd; i++ ) {\n" +
				"\t\t\t\tint indexSrc = i;\n" +
				"\t\t\t\t" + sumType + " total = (dataSrc[indexSrc] " + bitWise + ")*k1;\n");
		for (int i = 1; i < num; i++) {
			out.printf("\t\t\t\tindexSrc += input.stride;\n");
			out.printf("\t\t\t\ttotal += (dataSrc[indexSrc]" + bitWise + ")*k%d;\n", i + 1);
		}
		out.print("\n");
		if (hasDivisor)
			out.print("\t\t\t\tdataDst[indexDst++] = " + typeCast + "(" + divide + ");\n");
		else
			out.print("\t\t\t\tdataDst[indexDst++] = " + typeCast + "total;\n");
		out.print("\t\t\t}\n" +
				"\t\t}\n" +
				"\t}\n\n");
	}

	public void addConvolve( int num ) {
		String typeCast = generateTypeCast();

		out.print("\tpublic static void convolve" + num + "( Kernel2D_" + typeKernel + " kernel, " + typeInput + " input, " + typeOutput + " output, int skip )\n");

		out.print("\t{\n" +
				"\t\tfinal " + dataInput + "[] dataSrc = input.data;\n" +
				"\t\tfinal " + dataOutput + "[] dataDst = output.data;\n" +
				"\n" +
				"\t\tfinal int radius = kernel.getRadius();\n" +
				"\t\tfinal int widthEnd = UtilDownConvolve.computeMaxSide(input.width,skip,radius);\n" +
				"\t\tfinal int heightEnd = UtilDownConvolve.computeMaxSide(input.height,skip,radius);\n" +
				"\n" +
				"\t\tfinal int offset = UtilDownConvolve.computeOffset(skip,radius);\n" +
				"\n" +
				"\t\tfor( int y = offset; y <= heightEnd; y += skip) {\n" +
				"\n" +
				"\t\t\t// first time through the value needs to be set\n");
		for (int i = 0; i < num; i++) {
			out.print("\t\t\t" + sumType + " k" + (i + 1) + " = kernel.data[" + i + "];\n");
		}
		out.print("\n" +
				"\t\t\tint indexDst = output.startIndex + (y/skip)*output.stride + offset/skip;\n" +
				"\t\t\tint indexSrcRow = input.startIndex + (y-radius)*input.stride - radius;\n" +
				"\t\t\tfor( int x = offset; x <= widthEnd; x += skip ) {\n" +
				"\t\t\t\tint indexSrc = indexSrcRow + x;\n" +
				"\n" +
				"\t\t\t\t" + sumType + " total = 0;\n");
		for (int i = 0; i < num - 1; i++) {
			out.print("\t\t\t\ttotal += (dataSrc[indexSrc++] " + bitWise + ")* k" + (i + 1) + ";\n");
		}
		out.print("\t\t\t\ttotal += (dataSrc[indexSrc] " + bitWise + ")* k" + num + ";\n");
		out.print("\n" +
				"\t\t\t\tdataDst[indexDst++] = " + typeCast + "total;\n" +
				"\t\t\t}\n" +
				"\n" +
				"\t\t\t// rest of the convolution rows are an addition\n" +
				"\t\t\tfor( int i = 1; i < " + num + "; i++ ) {\n" +
				"\t\t\t\tindexDst = output.startIndex + (y/skip)*output.stride + offset/skip;\n" +
				"\t\t\t\tindexSrcRow = input.startIndex + (y+i-radius)*input.stride - radius;\n" +
				"\t\t\t\t\n");
		for (int i = 0; i < num; i++) {
			out.print("\t\t\t\tk" + (i + 1) + " = kernel.data[i*" + num + " + " + i + "];\n");
		}
		out.print("\n" +
				"\t\t\t\tfor( int x = offset; x <= widthEnd; x += skip ) {\n" +
				"\t\t\t\t\tint indexSrc = indexSrcRow+x;\n" +
				"\n" +
				"\t\t\t\t\t" + sumType + " total = 0;\n");
		for (int i = 0; i < num - 1; i++) {
			out.print("\t\t\t\t\ttotal += (dataSrc[indexSrc++] " + bitWise + ")* k" + (i + 1) + ";\n");
		}
		out.print("\t\t\t\t\ttotal += (dataSrc[indexSrc] " + bitWise + ")* k" + num + ";\n");
		out.print("\n" +
				"\t\t\t\t\tdataDst[indexDst++] += " + typeCast + "total;\n" +
				"\t\t\t\t}\n" +
				"\t\t\t}\n" +
				"\t\t}\n" +
				"\t}\n\n");
	}

	public void addConvolveDiv( int num ) {
		String typeCast = generateTypeCast();

		out.print("\tpublic static void convolve" + num + "( Kernel2D_" + typeKernel + " kernel, " + typeInput + " input, " + typeOutput + " output, int skip , int divisor )\n");

		out.print("\t{\n" +
				"\t\tfinal " + dataInput + "[] dataSrc = input.data;\n" +
				"\t\tfinal " + dataOutput + "[] dataDst = output.data;\n" +
				"\n" +
				"\t\tfinal int radius = kernel.getRadius();\n" +
				"\t\tfinal int widthEnd = UtilDownConvolve.computeMaxSide(input.width,skip,radius);\n" +
				"\t\tfinal int heightEnd = UtilDownConvolve.computeMaxSide(input.height,skip,radius);\n" +
				declareHalf +
				"\n" +
				"\t\tfinal " + sumType + " totalRow[] = new int[ widthEnd+1 ];\n" +
				"\n" +
				"\t\tfinal int offset = UtilDownConvolve.computeOffset(skip,radius);\n" +
				"\n" +
				"\t\tfor( int y = offset; y <= heightEnd; y += skip) {\n" +
				"\n" +
				"\t\t\t// first time through the value needs to be set\n");
		for (int i = 0; i < num; i++) {
			out.print("\t\t\t" + sumType + " k" + (i + 1) + " = kernel.data[" + i + "];\n");
		}
		out.print("\n" +
				"\t\t\tint indexSrcRow = input.startIndex + (y-radius)*input.stride - radius;\n" +
				"\t\t\tfor( int x = offset; x <= widthEnd; x += skip ) {\n" +
				"\t\t\t\tint indexSrc = indexSrcRow + x;\n" +
				"\n" +
				"\t\t\t\t" + sumType + " total = 0;\n");
		for (int i = 0; i < num - 1; i++) {
			out.print("\t\t\t\ttotal += (dataSrc[indexSrc++] " + bitWise + ")* k" + (i + 1) + ";\n");
		}
		out.print("\t\t\t\ttotal += (dataSrc[indexSrc] " + bitWise + ")* k" + num + ";\n");
		out.print("\n" +
				"\t\t\t\ttotalRow[x] = total;\n" +
				"\t\t\t}\n" +
				"\n" +
				"\t\t\t// rest of the convolution rows are an addition\n" +
				"\t\t\tfor( int i = 1; i < " + num + "; i++ ) {\n" +
				"\t\t\t\tindexSrcRow = input.startIndex + (y+i-radius)*input.stride - radius;\n" +
				"\t\t\t\t\n");
		for (int i = 0; i < num; i++) {
			out.print("\t\t\t\tk" + (i + 1) + " = kernel.data[i*" + num + " + " + i + "];\n");
		}
		out.print("\n" +
				"\t\t\t\tfor( int x = offset; x <= widthEnd; x += skip ) {\n" +
				"\t\t\t\t\tint indexSrc = indexSrcRow+x;\n" +
				"\n" +
				"\t\t\t\t\t" + sumType + " total = 0;\n");
		for (int i = 0; i < num - 1; i++) {
			out.print("\t\t\t\t\ttotal += (dataSrc[indexSrc++] " + bitWise + ")* k" + (i + 1) + ";\n");
		}
		out.print("\t\t\t\t\ttotal += (dataSrc[indexSrc] " + bitWise + ")* k" + num + ";\n");
		out.print("\n" +
				"\t\t\t\t\ttotalRow[x] += total;\n" +
				"\t\t\t\t}\n" +
				"\t\t\t}\n" +
				"\t\t\tint indexDst = output.startIndex + (y/skip)*output.stride + offset/skip;\n" +
				"\t\t\tfor( int x = offset; x <= widthEnd; x += skip ) {\n" +
				"\t\t\t\tdataDst[indexDst++] = " + typeCast + "((totalRow[x] + halfDivisor) / divisor);\n" +
				"\t\t\t}\n" +
				"\t\t}\n" +
				"\t}\n\n");
	}

	private String generateTypeCast() {
		return sumType.compareTo(dataOutput) == 0 ? "" : "( " + dataOutput + " )";
	}

	public static void main( String[] args ) throws FileNotFoundException {
		GenerateConvolveDownNoBorderUnrolled a = new GenerateConvolveDownNoBorderUnrolled();

		a.generateCode();
	}
}
