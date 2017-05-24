package com.J00nzu.Java3DEngine.graphics.lowlvl;

public class Vert3 extends Vector3{
	
	public float viewX;
	public float viewY;
	public float viewZ;
	
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
		
		float w = result[3];
		
		this.x = result[0]/w;
		this.y = result[1]/w;
		this.z = result[2]/w;

	}
	
	public void ViewTransform(Transformatrix transform){
		float[] matrix = {x, y, z, 1};
		float[] result = new float[4];
		
		for(int x = 0; x < 4; x++){
			for(int y = 0; y < 4; y++){
				result[x] += matrix[y] * transform.mat4.matrix[y][x];
			}
		}
		
		float w = result[3];
		
		this.viewX = result[0]/w;
		this.viewY = result[1]/w;
		this.viewZ = result[2]/w;
	}

	@Override
	public String toString() {
		return "Vert3 [viewX=" + viewX + ", viewY=" + viewY + ", viewZ="
				+ viewZ + ", x=" + x + ", y=" + y + ", z=" + z + "]";
	}
	
	
}
