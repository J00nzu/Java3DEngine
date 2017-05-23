package com.J00nzu.Java3DEngine.graphics.lowlvl;

public class Vert3 {
	
	public float x, y, z;
	
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


	public Vert3 Add(Vert3 other){
		Vert3 out = new Vert3(this);
		out.x += other.x;
		out.y += other.y;
		out.z += other.z;
		
		return out;
	}
	
	public Vert3 Subtract(Vert3 other){
		Vert3 out = new Vert3(this);
		out.x -= other.x;
		out.y -= other.y;
		out.z -= other.z;
		
		return out;
	}
	
	public Vert3 Multiply(Vert3 other){
		Vert3 out = new Vert3(this);
		out.x *= other.x;
		out.y *= other.y;
		out.z *= other.z;
		
		return out;
	}
	
	public Vert3 Divide(Vert3 other){
		Vert3 out = new Vert3(this);
		out.x /= other.x;
		out.y /= other.y;
		out.z /= other.z;
		
		return out;
	}

}
