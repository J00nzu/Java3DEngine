package com.J00nzu.Java3DEngine.graphics.lowlvl;

public class Transformatrix {

	/*
	 * 
	 */

	private static final Matrix4 baseMatrix = new Matrix4(new float[][] {
			{ 1, 0, 0, 0 }, { 0, 1, 0, 0 }, { 0, 0, 1, 0 }, { 0, 0, 0, 1 } });

	private static final int X = 0, Y = 1, Z = 2, TRANS = 3;

	Matrix4 mat4;

	public Transformatrix() {
		mat4 = baseMatrix.Clone();
	}

	public Transformatrix(Transformatrix clone) {
		mat4 = clone.mat4.Clone();
	}
	
	public Transformatrix(Matrix4 matrix) {
		mat4 = matrix;
	}

	public void translate(Vector3 translation) {
		Matrix4 transMatrix = baseMatrix.Clone();

		transMatrix.matrix[TRANS][X] = translation.x;
		transMatrix.matrix[TRANS][Y] = translation.y;
		transMatrix.matrix[TRANS][Z] = translation.z;

		this.mat4 = mat4.Multiply(transMatrix);
	}

	public void rotate(Vector3 rotation) {

		if (rotation.x != 0) {
			Matrix4 rotMatrix = baseMatrix.Clone();
			float rot = rotation.x;
			
			rotMatrix.matrix[Y][Y] = (float)Math.cos(rot);
			rotMatrix.matrix[Y][Z] = (float)-Math.sin(rot);
			rotMatrix.matrix[Z][Y] = (float)Math.sin(rot);
			rotMatrix.matrix[Z][Z] = (float)Math.cos(rot);
			
			this.mat4 = mat4.Multiply(rotMatrix);
		}

		if (rotation.y != 0) {
			Matrix4 rotMatrix = baseMatrix.Clone();
			float rot = rotation.y;
			
			rotMatrix.matrix[X][X] = (float)Math.cos(rot);
			rotMatrix.matrix[X][Z] = (float)Math.sin(rot);
			rotMatrix.matrix[Z][X] = (float)-Math.sin(rot);
			rotMatrix.matrix[Z][Z] = (float)Math.cos(rot);
			
			this.mat4 = mat4.Multiply(rotMatrix);
		}

		if (rotation.z != 0) {
			Matrix4 rotMatrix = baseMatrix.Clone();
			float rot = rotation.z;
			
			rotMatrix.matrix[X][X] = (float)Math.cos(rot);
			rotMatrix.matrix[X][Y] = (float)-Math.sin(rot);
			rotMatrix.matrix[Y][X] = (float)Math.sin(rot);
			rotMatrix.matrix[Y][Y] = (float)Math.cos(rot);
			
			this.mat4 = mat4.Multiply(rotMatrix);
		}
	}

	public void scale(Vector3 scaling) {
		Matrix4 scaleMatrix = baseMatrix.Clone();

		scaleMatrix.matrix[X][X] = scaling.x;
		scaleMatrix.matrix[Y][Y] = scaling.y;
		scaleMatrix.matrix[Z][Z] = scaling.z;

		this.mat4 = mat4.Multiply(scaleMatrix);
	}
	
	public String toString(){
		
		return mat4.toString();
	}

}
