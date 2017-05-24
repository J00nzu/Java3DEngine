package com.J00nzu.Java3DEngine.graphics.lowlvl;

public class Transform {
	
	public Vector3 position = new Vector3();
	public Vector3 scale = new Vector3(1,1,1);
	public Vector3 rotation = new Vector3();
	
	private Transformatrix matrix = new Transformatrix();
	
	public Transform(){
		super();
	}
	
	
	public Transformatrix getTransformatrix(){
		matrix.clear();
		
		matrix.scale(scale);
		matrix.rotate(rotation);
		matrix.translate(position);
		
		return matrix;
	}

}
