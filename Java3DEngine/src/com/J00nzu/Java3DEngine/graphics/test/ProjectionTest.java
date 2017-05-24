package com.J00nzu.Java3DEngine.graphics.test;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.J00nzu.Java3DEngine.graphics.lowlvl.*;

public class ProjectionTest {
	
	static final int width = 800;
	static final int height = 600;
	static final int offsetX = width/2;
	static final int offsetY = height/2;

	
	
	public static void main(String[] args){
		
		
		
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setIgnoreRepaint(true);
		
		frame.add(panel);
		
		
				
		/*
		 * TEST CODE HERE
		 */
		
		Vert3 a = new Vert3(1, 0 ,1);
		Vert3 b = new Vert3(1, 0 ,-1);
		Vert3 c = new Vert3(-1, 0 ,-1);
		Vert3 d = new Vert3(-1, 0 ,1);
		Vert3 e = new Vert3(0, 1.5f ,0);
		
		Face face_eab = new Face(e,a,b);
		Face face_ebc = new Face(e,b,c);
		Face face_ecd = new Face(e,c,d);
		Face face_eda = new Face(e,d,a);
		Face face_bac = new Face(b,a,c);
		Face face_dca = new Face(d,c,a);
		
		ArrayList<Vert3> verticies = new ArrayList<Vert3>();
		verticies.add(a);
		verticies.add(b);
		verticies.add(c);
		verticies.add(d);
		verticies.add(e);
		
		ArrayList<Face> faces = new ArrayList<Face>();
		faces.add(face_eab);
		faces.add(face_ebc);
		faces.add(face_ecd);
		faces.add(face_eda);
		faces.add(face_bac);
		faces.add(face_dca);


		GraphicsObj pyramid = new GraphicsObj(verticies, faces);
		
		GraphicsObj pyramid2 = new GraphicsObj(verticies, faces);
		
		pyramid.transform.position = new Vector3(2,-2,-5);
		
		pyramid2.transform.position = new Vector3(-2,-2,-20);
		
		
		ArrayList<GraphicsObj> drawables = new ArrayList<GraphicsObj>();
		
		drawables.add(pyramid);
		drawables.add(pyramid2);

		
		PerspectiveCamera cam = new PerspectiveCamera();
		
		
		
		frame.setVisible(true);
		
		
		Graphics g = panel.getGraphics();
		
		
		while(true){
			
			g.clearRect(0, 0, width, height);
			
			for(GraphicsObj obj : drawables){
				
				//obj.transform.rotation.z += 0.01f;
				//obj.transform.rotation.x = -(float)Math.PI/16;
				obj.transform.rotation.y += 0.005f;
				//obj.transform.Position.x += 0.01f;
				
				//obj.transform.position.z += 0.01f;
				
				Transformatrix tf = new Transformatrix();
				
				Transformatrix camtrix = cam.GetProjectionMatrix();
				
				tf.scale(obj.transform.scale);
				tf.rotate(obj.transform.rotation);
				tf.translate(obj.transform.position);
				
				tf.multiply(camtrix);
				
				
				
				for(Vert3 vert : obj.getVerticies()){
					vert.ViewTransform(tf);
				}
				
				for(Face face : obj.getFaces()){
					drawFace(g, Color.RED, face);
				}
			}



			try {
				Thread.sleep(20);
			} catch (InterruptedException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
			
		}
		
		//g.dispose();

		
		/*
		 * TEST CODE ENDS
		 */
		
		
	}
	
	
	public static void drawFace(Graphics g, Color color, Face face){
		
		g.setColor(color);
		
		g.drawLine(
				
					(int)(face.A.viewX*width/2 + offsetX), 
					(int)(-face.A.viewY*height/2 + offsetY), 
				
					(int)(face.B.viewX*width/2 + offsetX),
					(int)(-face.B.viewY*height/2 + offsetY)
					
				);
		
		g.drawLine(
				
				(int)(face.B.viewX*width/2 + offsetX), 
				(int)(-face.B.viewY*height/2 + offsetY), 
			
				(int)(face.C.viewX*width/2 + offsetX),
				(int)(-face.C.viewY*height/2 + offsetY)
				
			);
		
		g.drawLine(
				
				(int)(face.C.viewX*width/2 + offsetX), 
				(int)(-face.C.viewY*height/2 + offsetY), 
			
				(int)(face.A.viewX*width/2 + offsetX),
				(int)(-face.A.viewY*height/2 + offsetY)
				
			);
		
	}

}
