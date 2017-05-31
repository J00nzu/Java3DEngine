package com.J00nzu.Java3DEngine.graphics.lowlvlnew;

public abstract class Camera extends Obj{
	
	public Camera(){
		transform.scale = new Vector3(-1, 1, 1);
	}
	
	public abstract float getNear();
	public abstract float getFar();
	public abstract Transformatrix GetProjectionMatrix();

}
