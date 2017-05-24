package com.J00nzu.Java3DEngine.graphics.lowlvl;

public abstract class Camera extends Obj{
	
	public Camera(){
		transform.scale = new Vector3(-1, 1, 1);
	}
	
	
	public abstract Transformatrix GetProjectionMatrix();

}
