package com.J00nzu.Java3DEngine.graphics.test;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.J00nzu.Java3DEngine.graphics.lowlvl.*;

public class ViewportTest {
	
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
		Vert3 e = new Vert3(0, 1 ,0);
		
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
		
		GraphicsObj pyramid2 = pyramid.copy();
		
		GraphicsObj pyramid3 = pyramid.copy();
		
		pyramid.transform.position = new Vector3(0,-1,10);
		
		pyramid.transform.scale = new Vector3(1,1,1);
		
		pyramid2.transform.position = new Vector3(2,-1,3);
		
		pyramid2.transform.scale = new Vector3(1f, 1f, 1f);
		
		pyramid3.transform.position = new Vector3(0,1.5f,0);
		
		pyramid3.transform.scale = new Vector3(0.5f, 0.5f, 0.5f);

		
		PerspectiveCamera cam = new PerspectiveCamera();
		
		cam.transform.position.z = 0;
		
		World world = new World();
		
		world.AddChild(pyramid);
		world.AddChild(pyramid2);
		pyramid2.AddChild(pyramid3);
		
		Viewport view = new Viewport(world, cam);
		
		System.out.println(pyramid.getWorldPosition());
		System.out.println(pyramid2.getWorldPosition());
		System.out.println(pyramid3.getWorldPosition());
		
		frame.setVisible(true);
		
		
		Graphics g = panel.getGraphics();
		
		
		while(true){
			g.clearRect(0, 0, width, height);
			
			pyramid2.transform.position.z += 0.001;
			//cam.transform.rotation.z+=0.005;
			//pyramid.transform.rotation.z+=0.005;
			pyramid3.transform.rotation.y+=0.01;

			
			ArrayList<Face> toDraw = view.process();
			
			for(Face face : toDraw){
				drawFace(g, Color.RED, face);
			}
			
			System.out.println(pyramid2.getVerticies().get(0));




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
