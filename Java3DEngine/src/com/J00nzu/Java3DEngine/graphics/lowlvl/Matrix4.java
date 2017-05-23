package com.J00nzu.Java3DEngine.graphics.lowlvl;

import java.text.NumberFormat;

public class Matrix4 {
	
	private static final int SIZE = 4;
	
	public float[][] matrix = new float[SIZE][SIZE];
	
	public Matrix4(){}
	
	public Matrix4(float[][] matrix){
		try{
			if(matrix.length != SIZE || matrix[0].length != SIZE){
				throw new InvalidMatrixSizeException("Matrix size should be 4x4!");
			}
		}catch(Exception ex){
			throw new InvalidMatrixSizeException("Matrix size should be 4x4!");
		}
		
		this.matrix = matrix;
	}
	
	public Matrix4 Multiply(Matrix4 other){
		Matrix4 result = new Matrix4();
		
		for(int x=0; x < SIZE; x++){
			for(int y=0; y < SIZE; y++){
				for(int i=0; i < SIZE; i++){
					result.matrix[y][x] += this.matrix[y][i] * other.matrix[i][x];
				}
			}
		}
		return result;
	}
	
	public Matrix4 Clone(){
		Matrix4 result = new Matrix4();
		for(int x=0; x < SIZE; x++){
			for(int y=0; y < SIZE; y++){
				result.matrix[y][x] = this.matrix[y][x];
			}
		}
		return result;
	}
	
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		NumberFormat nf = NumberFormat.getInstance();
		
		int digits = 3;
		
		nf.setMaximumFractionDigits(digits);
		nf.setMinimumFractionDigits(digits);
				
		int lineLength = 2 + (2+digits)*SIZE + 3*(SIZE-1);
		
		for(int i=0; i < lineLength; i++){
			sb.append('-');
		}
		sb.append('\n');
		
		for(int y = 0; y < SIZE; y++){
			sb.append('|');
			for(int x = 0; x < SIZE; x++){
				sb.append(nf.format(matrix[y][x]));
				
				if(x < SIZE-1){
					sb.append("   ");
				}
			}
			sb.append("|\n");
		}
		
		for(int i=0; i < lineLength; i++){
			sb.append('-');
		}
		
		return sb.toString();
	}

}
