package com.J00nzu.Java3DEngine.graphics.lowlvl;

public class Vert3{
	
	public float x, y, z;
	
	public float viewX;
	public float viewY;
	public float viewZ;
	public float viewW = 1;
	
	public Vert3(Vert3 toClone) {
		super();
		this.x = toClone.x;
		this.y = toClone.y;
		this.z = toClone.z;
		
	}
	
	public Vert3(){
		super();
		this.x = 0;
		this.y = 0;
		this.z = 0;
	}
	
	public Vert3(float x, float y, float z) {
		super();
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public void ApplyTransformatrix(Transformatrix transform){
		float[] matrix = {x, y, z, 1};
		float[] result = new float[4];
		
		for(int x = 0; x < 4; x++){
			for(int y = 0; y < 4; y++){
				result[x] += matrix[y] * transform.mat4.matrix[y][x];
			}
		}
		
		//float w = result[3];
		
		
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
		
		this.viewW = result[3];
		
		this.viewX = result[0];
		this.viewY = result[1];
		this.viewZ = result[2];
		

	}
	

	public void ViewTransformFinalize(){
		viewX /= viewW;
		viewY /= viewW;
		viewZ /= viewW;
	}

	@Override
	public String toString() {
		return "Vert3 [viewX=" + viewX + ", viewY=" + viewY + ", viewZ="
				+ viewZ + ", viewW=" + viewW + ", x=" + x + ", y=" + y + ", z="
				+ z + "]";
	}

	public boolean sameAs(Vert3 other){
		if(other==null){
			return false;
		}
		
		if(viewX != other.viewX){
			return false;
		}if(viewY != other.viewY){
			return false;
		}if(viewZ != other.viewZ){
			return false;
		}if(viewW != other.viewW){
			return false;
		}if(x != other.x){
			return false;
		}if(y != other.y){
			return false;
		}if(z != other.z){
			return false;
		}
		
		return true;
	}
	
	
	
	
}
