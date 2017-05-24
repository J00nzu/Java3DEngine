package com.J00nzu.Java3DEngine.graphics.lowlvl;

public class Line3 {
	
	public Vector3 a;
	public Vector3 b;
	
	public Line3(){
		a = new Vector3();
		b = new Vector3();
	}
	
	public Line3(Vector3 a, Vector3 b){
		this.a = new Vector3(a);
		this.b = new Vector3(b);
	}
	
	public Line3(float a_x, float a_y, float a_z, float b_x, float b_y, float b_z){
		a = new Vector3(a_x, a_y, a_z);
		b = new Vector3(b_x, b_y, b_z);
	}

}
