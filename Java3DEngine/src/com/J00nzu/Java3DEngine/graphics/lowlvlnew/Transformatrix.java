package com.J00nzu.Java3DEngine.graphics.lowlvlnew;

public class Transformatrix {

	/*
	 * 
	 */

	private static final Matrix4 baseMatrix = new Matrix4(new float[][] {
			{ 1, 0, 0, 0 }, { 0, 1, 0, 0 }, { 0, 0, 1, 0 }, { 0, 0, 0, 1 } });

	private static final int X = 0, Y = 1, Z = 2, TRANS = 3;

	Matrix4 mat4;

	public Transformatrix() {
		mat4 = baseMatrix.clone();
	}

	public Transformatrix(Transformatrix clone) {
		mat4 = clone.mat4.clone();
	}
	
	public Transformatrix(Matrix4 matrix) {
		if(matrix!=null){
			mat4 = matrix;
		}
	}
	
	public void multiply(Transformatrix other){
		if(other==null){
			return;
		}
		
		this.mat4 = mat4.Multiply(other.mat4);
	}

	public void translate(Vector3 translation) {
		if(translation==null){
			return;
		}
		if( translation.x==0 && translation.y==0 && translation.z==0){
			return;
		}
		
		Matrix4 transMatrix = baseMatrix.clone();

		transMatrix.matrix[TRANS][X] = translation.x;
		transMatrix.matrix[TRANS][Y] = translation.y;
		transMatrix.matrix[TRANS][Z] = translation.z;

		this.mat4 = mat4.Multiply(transMatrix);
	}

	public void rotate(Vector3 rotation) {
		if(rotation==null){
			return;
		}

		if (rotation.x != 0) {
			Matrix4 rotMatrix = baseMatrix.clone();
			float rot = rotation.x;
			
			rotMatrix.matrix[Y][Y] = (float)Math.cos(rot);
			rotMatrix.matrix[Y][Z] = (float)-Math.sin(rot);
			rotMatrix.matrix[Z][Y] = (float)Math.sin(rot);
			rotMatrix.matrix[Z][Z] = (float)Math.cos(rot);
			
			this.mat4 = mat4.Multiply(rotMatrix);
		}

		if (rotation.y != 0) {
			Matrix4 rotMatrix = baseMatrix.clone();
			float rot = rotation.y;
			
			rotMatrix.matrix[X][X] = (float)Math.cos(rot);
			rotMatrix.matrix[X][Z] = (float)Math.sin(rot);
			rotMatrix.matrix[Z][X] = (float)-Math.sin(rot);
			rotMatrix.matrix[Z][Z] = (float)Math.cos(rot);
			
			this.mat4 = mat4.Multiply(rotMatrix);
		}

		if (rotation.z != 0) {
			Matrix4 rotMatrix = baseMatrix.clone();
			float rot = rotation.z;
			
			rotMatrix.matrix[X][X] = (float)Math.cos(rot);
			rotMatrix.matrix[X][Y] = (float)-Math.sin(rot);
			rotMatrix.matrix[Y][X] = (float)Math.sin(rot);
			rotMatrix.matrix[Y][Y] = (float)Math.cos(rot);
			
			this.mat4 = mat4.Multiply(rotMatrix);
		}
	}

	public void scale(Vector3 scaling) {
		if(scaling==null){
			return;
		}
		
		if( scaling.x==1 && scaling.y==1 && scaling.z==1){
			return;
		}
		
		Matrix4 scaleMatrix = baseMatrix.clone();

		scaleMatrix.matrix[X][X] = scaling.x;
		scaleMatrix.matrix[Y][Y] = scaling.y;
		scaleMatrix.matrix[Z][Z] = scaling.z;

		this.mat4 = mat4.Multiply(scaleMatrix);
	}
	
	public void inverse(){
		for(int y=0; y < 4; y++){
			for(int x=0; x < 4; x++){
				mat4.matrix[y][x] = -mat4.matrix[y][x];
			}
		}
	}
	
	/**
	 * Defaults the matrix to be the basematrix: <br> <br>
	 * <pre>
	 * | 1 0 0 0 |
	 * | 0 1 0 0 |
	 * | 0 0 1 0 |
	 * | 0 0 0 1 |
	 * </pre>
	 */
	public void clear(){
		for(int y=0; y < 4; y++){
			for(int x=0; x < 4; x++){
				mat4.matrix[y][x] = x==y?1:0;
			}
		}
	}
	
	public String toString(){
		
		return mat4.toString();
	}
	
	@Override
	public boolean equals(Object object){
		if(object instanceof Transformatrix){
			Transformatrix other = (Transformatrix)object;
			
			return mat4.equals(other.mat4);
		}else{
			return false;
		}
	}
	
	@Override
	public int hashCode(){
		//inverse hash of mat4 to not mix up with mat4's
		
		int hash = ~mat4.hashCode();
		
		return hash;
	}

}
