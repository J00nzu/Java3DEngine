package com.J00nzu.Java3DEngine.graphics.lowlvl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class GraphicsObj extends Obj{
	
	private ArrayList<Vert3> verticies = new ArrayList<Vert3>();
	private ArrayList<Face> faces = new ArrayList<Face>();
	
	public GraphicsObj(){
		super();
	}
	
	public GraphicsObj(Collection<Vert3> verticies, Collection<Face> faces){
		super();
		
		if(verticies != null){
			this.verticies.addAll(verticies);
		}
		if(faces != null){
			this.faces.addAll(faces);
		}
	}
	
	public GraphicsObj copy(){
		GraphicsObj copy = new GraphicsObj();
		HashMap<Vert3, Vert3> vertmap = new HashMap<Vert3, Vert3>();
		
		for(Vert3 ver : verticies){
			Vert3 c_ver = new Vert3(ver);
			vertmap.put(ver, c_ver);
			copy.verticies.add(c_ver);
		}
		
		for(Face face : faces){
			Face c_face = new Face(vertmap.get(face.A), vertmap.get(face.B), vertmap.get(face.C));
			copy.faces.add(c_face);
		}
		
		return copy;
	}
	
	public ArrayList<Vert3> getVerticies(){
		return verticies;
	}
	
	public ArrayList<Face> getFaces(){
		return faces;
	}

}
