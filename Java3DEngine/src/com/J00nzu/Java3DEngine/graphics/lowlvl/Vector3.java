package com.J00nzu.Java3DEngine.graphics.lowlvl;

public class Vector3 {
	public float x, y, z;
	
	public Vector3(Vector3 toClone) {
		super();
		this.x = toClone.x;
		this.y = toClone.y;
		this.z = toClone.z;
		
	}
	
	public Vector3(){
		super();
		this.x = 0;
		this.y = 0;
		this.z = 0;
	}
	
	public Vector3(float x, float y, float z) {
		super();
		this.x = x;
		this.y = y;
		this.z = z;
	}


	public Vector3 Add(Vert3 other){
		Vector3 out = new Vector3(this);
		out.x += other.x;
		out.y += other.y;
		out.z += other.z;
		
		return out;
	}
	
	public Vector3 Subtract(Vert3 other){
		Vector3 out = new Vector3(this);
		out.x -= other.x;
		out.y -= other.y;
		out.z -= other.z;
		
		return out;
	}
	
	public Vector3 Multiply(Vert3 other){
		Vector3 out = new Vector3(this);
		out.x *= other.x;
		out.y *= other.y;
		out.z *= other.z;
		
		return out;
	}
	
	public Vector3 Divide(Vert3 other){
		Vector3 out = new Vector3(this);
		out.x /= other.x;
		out.y /= other.y;
		out.z /= other.z;
		
		return out;
	}

}
