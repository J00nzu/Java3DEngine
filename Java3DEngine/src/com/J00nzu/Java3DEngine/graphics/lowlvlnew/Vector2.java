package com.J00nzu.Java3DEngine.graphics.lowlvlnew;

public class Vector2 {
	public static final int 
				CONVERSION_NONE = 0,
				CONVERSION_Z_TO_Y = 1,
				CONVERSION_Z_TO_X = 2;
	
	
	public float x, y;
	
	public Vector2(Vector2 toClone) {
		super();
		this.x = toClone.x;
		this.y = toClone.y;
		
	}
	
	public Vector2(Vector3 toClone) {
		this(toClone, CONVERSION_NONE);
	}
	
	public Vector2(Vector3 toClone, int conversionMode) {
		super();
		this.x = toClone.x;
		this.y = toClone.y;
		
		switch(conversionMode){
			case CONVERSION_Z_TO_Y:
				this.x = toClone.x;
				this.y = toClone.z;
				break;
			case CONVERSION_Z_TO_X:
				this.x = toClone.z;
				this.y = toClone.y;
				break;
			default:
				this.x = toClone.x;
				this.y = toClone.y;
				break;
		}
	}
	
	public Vector2(){
		super();
		this.x = 0;
		this.y = 0;
	}
	
	public Vector2(float x, float y) {
		super();
		this.x = x;
		this.y = y;
	}


	public Vector2 add(Vector2 other){
		Vector2 out = new Vector2(this);
		out.x += other.x;
		out.y += other.y;
		
		return out;
	}
	
	public Vector2 subtract(Vector2 other){
		Vector2 out = new Vector2(this);
		out.x -= other.x;
		out.y -= other.y;
		
		return out;
	}
	
	public Vector2 multiply(Vector2 other){
		Vector2 out = new Vector2(this);
		out.x *= other.x;
		out.y *= other.y;
		
		return out;
	}
	
	public Vector2 divide(Vector2 other){
		Vector2 out = new Vector2(this);
		out.x /= other.x;
		out.y /= other.y;
		
		return out;
	}
	
	public Vector2 inverse(){
		Vector2 out = new Vector2();
		out.x = -x;
		out.y = -y;
		
		return out;
	}
	
	public Vector2 rotate(float radians){
		Vector2 out = new Vector2();
		out.x = (float)(x*Math.cos(radians) - y*Math.sin(radians));
		out.y = (float)(y*Math.cos(radians) + x*Math.sin(radians));
		return out;
	}

	@Override
	public String toString() {
		return "Vector2 [x=" + x + ", y=" + y + "]";
	}
	

}
