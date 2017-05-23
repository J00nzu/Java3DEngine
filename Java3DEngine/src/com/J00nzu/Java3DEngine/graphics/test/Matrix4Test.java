package com.J00nzu.Java3DEngine.graphics.test;

import com.J00nzu.Java3DEngine.graphics.lowlvl.*;

public class Matrix4Test {
	
	public static void main(String[] args){
		
		Matrix4 testMatrix = new Matrix4();
		Matrix4 testMatrix2 = new Matrix4();
		
		setTestNums(testMatrix);
		setTestNums(testMatrix2);
		
		Matrix4 multiplied = testMatrix.Multiply(testMatrix2);
		
		
		
		System.out.println(testMatrix);
		
		System.out.println(testMatrix2);
		
		System.out.println(multiplied);
		
		
		Transformatrix transform = new Transformatrix();
		
		transform.translate(new Vector3(5,0,0));
		transform.scale(new Vector3(5,0,0));
		transform.rotate(new Vector3(1,1,1));
		
		System.out.println(transform);

	} 
	
	public static void setTestNums(Matrix4 toSet){
		int i=1;
		
		for(int y = 0; y < 4; y++){
			for(int x = 0; x < 4; x++){
				toSet.matrix[y][x] = i++;
			}
		}
	}

}
