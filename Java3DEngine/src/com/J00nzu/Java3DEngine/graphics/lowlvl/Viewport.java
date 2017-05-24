package com.J00nzu.Java3DEngine.graphics.lowlvl;

import java.awt.Color;
import java.util.ArrayList;

public class Viewport {
	
	public Camera camera;
	public World world;
	
	private ArrayList<Face> facesInView = new ArrayList<Face>();
	
	public Viewport(World world, Camera camera){
		this.world = world;
		this.camera = camera;
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
				
			}
			
			for(Face face : go.getFaces()){
				boolean clipping = false;
				
				for(Vert3 vert : face){
										
					if(vert.viewX < -1 || vert.viewX> 1 || vert.viewY < -1 || vert.viewY > 1 || vert.viewZ > 1){
						clipping = true;
					}
				}
				
				if(!clipping){
					facesInView.add(face);
				}else{
					ClipFace(go, face);
				}
				
			}
			
		}

	}
	
	private void ClipFace(GraphicsObj go, Face face){
		
	}

}
