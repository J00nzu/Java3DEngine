package com.J00nzu.Java3DEngine.graphics.lowlvl;
import java.util.ArrayList;


public class Obj {
	
	private Obj parent;
	
	private ArrayList<Obj> children = new ArrayList<Obj>();
	private ArrayList<Vert3> verticies = new ArrayList<Vert3>();
	private ArrayList<Face> faces = new ArrayList<Face>();
	
	public Vector3 Position;
	public Vector3 Scale;
	public Vector3 Rotation;

	
	
	void RemoveChild (Obj child){
		children.remove(child);
	}
	
	public void Delete(){
		if(parent!=null){
			parent.RemoveChild(this);
		}
		
		for(Obj child : children){
			child.Delete();
		}
	}


}
