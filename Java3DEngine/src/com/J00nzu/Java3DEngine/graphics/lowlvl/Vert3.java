package com.J00nzu.Java3DEngine.graphics.lowlvl;

public class Vert3 extends Vector3{
	
	float viewX;
	float viewY;
	float viewZ;
	
	public Vert3(Vector3 toClone) {
		super(toClone);
	}
	
	public Vert3(float x, float y, float z) {
		super(x,y,z);
	}
	
	public Vert3(){
		super();
	}
	
	public void ApplyTransformatrix(Transformatrix transform){
		float[] matrix = {x, y, z, 1};
		float[] result = new float[4];
		
		for(int x = 0; x < 4; x++){
			for(int y = 0; y < 4; y++){
				result[x] += matrix[y] * transform.mat4.matrix[y][x];
			}
		}
		
		this.x = result[0];
		this.y = result[1];
		this.z = result[2];

	}
	
	public void ViewTransform(Transformatrix transform){
		float[] matrix = {x, y, z, 1};
		float[] result = new float[4];
		
		for(int x = 0; x < 4; x++){
			for(int y = 0; y < 4; y++){
				result[x] += matrix[y] * transform.mat4.matrix[y][x];
			}
		}
		
		this.viewX = result[0];
		this.viewY = result[1];
		this.viewZ = result[2];
	}
}
