package com.J00nzu.Java3DEngine.graphics.lowlvl;

import java.util.ArrayList;
import java.util.Collection;

public class GraphicsObj extends Obj{
	
	private ArrayList<Vert3> verticies = new ArrayList<Vert3>();
	private ArrayList<Face> faces = new ArrayList<Face>();
	
	public GraphicsObj(Collection<Vert3> verticies, Collection<Face> faces){
		if(verticies != null){
			this.verticies.addAll(verticies);
		}
		if(faces != null){
			this.faces.addAll(faces);
		}
	}
	
	public Collection<Vert3> getVerticies(){
		return verticies;
	}
	
	public Collection<Face> getFaces(){
		return faces;
	}

}
