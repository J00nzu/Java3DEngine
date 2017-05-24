package com.J00nzu.Java3DEngine.graphics.lowlvl;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;

public class Viewport {
	
	public Camera camera;
	public World world;
	
	private ArrayList<Face> facesInView = new ArrayList<Face>();
	
	CohenSutherlandClipper lineClipper;
	
	public Viewport(World world, Camera camera){
		this.world = world;
		this.camera = camera;
		lineClipper = new CohenSutherlandClipper(1, -1, 1, -1, 1, 0);
	}
	
	public synchronized ArrayList<Face> process(){
		if(world==null || camera == null){
			throw new NullPointerException("WORLD OR CAMERA IS NULL");
		}
		
		facesInView.clear();
		
		

		Transformatrix viewScape = new Transformatrix();
		
		Vector3 xyInverse = new Vector3(-1, -1, 1);
		
		viewScape.translate(camera.transform.position.multiply(xyInverse));
		viewScape.rotate(camera.transform.rotation.inverse());
				
		Transformatrix cameraMatrix = camera.GetProjectionMatrix();
		
		viewScape.multiply(cameraMatrix);
		

		processTransform(world, viewScape);
		
		return facesInView;
	}
	
	
	private void processTransform(Obj child, Transformatrix parentTransformatrix){
		Transformatrix transformatrix = new Transformatrix(child.transform.getTransformatrix());
		transformatrix.multiply(parentTransformatrix);
		
		for(Obj o : child.getChildren()){
			processTransform(o, transformatrix);
		}
		
		if(child instanceof GraphicsObj){
			GraphicsObj go = (GraphicsObj)child;
			
			processFacesAndVerts(go, transformatrix);
		}
		
	}
	
	private void processFacesAndVerts(GraphicsObj go, Transformatrix transformatrix){

		synchronized(go){
			for(Vert3 vert : go.getVerticies()){	
				vert.ViewTransform(transformatrix);
				System.out.println(vert);
			}
			
			for(Face face : go.getFaces()){
				boolean clipping = false;
				
				for(Vert3 vert : face){
										
					if(vert.viewX < -1 || vert.viewX> 1 || vert.viewY < -1 || vert.viewY > 1 || vert.viewZ > 1 || vert.viewZ < 0){
						clipping = true;
					}
				}
				
				if(!clipping){
					facesInView.add(face);
				}else{
					//System.out.println("clipping");
					ClipFace(go, face);
				}
				
			}
			
		}

	}
	
	private void ClipFace(GraphicsObj go, Face face){
		Line3 AB = new Line3(face.A.viewX, face.A.viewY, face.A.viewZ, face.B.viewX, face.B.viewY, face.B.viewZ);
		Line3 BC = new Line3(face.B.viewX, face.B.viewY, face.B.viewZ, face.C.viewX, face.C.viewY, face.C.viewZ);
		Line3 CA = new Line3(face.C.viewX, face.C.viewY, face.C.viewZ, face.A.viewX, face.A.viewY, face.A.viewZ);
		
		ArrayList<Vert3> newVerticies = new ArrayList<Vert3>();
		
		ArrayList<Line3> lines = new ArrayList<Line3>();
		lines.add(AB);
		lines.add(BC);
		lines.add(CA);
		
		for(Line3 line : lines){
			if(lineClipper.clip(line)){
				Vert3 A = new Vert3();
				A.viewX = line.a.x;
				A.viewY = line.a.y;
				A.viewZ = line.a.z;
				
				Vert3 B = new Vert3();
				B.viewX = line.b.x;
				B.viewY = line.b.y;
				B.viewZ = line.b.z;
				
				boolean Afound = false;
				boolean Bfound = false; 
				
				for(Vert3 C : newVerticies){
					if(A.sameAs(C)){
						Afound = true;
					}else if(B.sameAs(C)){
						Bfound = true;
					}
				}
				if((!Afound) && (!Bfound) && A.sameAs(B)){
					Afound = true;
					Bfound = true;
					newVerticies.add(A);
				}
				if(!Afound){
					newVerticies.add(A);
				}
				if(!Bfound){
					newVerticies.add(B);
				}
			}
		}
		
		
		int newTrisCount = newVerticies.size()-2;
		
		if(newTrisCount < 1){
			return;
		}
		
		for(int i=0; i< newTrisCount; i++){
			Vert3 a = newVerticies.get(0);
			Vert3 b = newVerticies.get(1+i);
			Vert3 c = newVerticies.get(2+i);
			
			Face nuFace = new Face(a,b,c);
			
			facesInView.add(nuFace);
		}
		
		

	}

}
