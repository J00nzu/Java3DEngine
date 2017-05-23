package com.J00nzu.Java3DEngine.graphics.test;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.J00nzu.Java3DEngine.graphics.lowlvl.*;

public class TriangleTest {
	
	static final int width = 800;
	static final int height = 600;
	static final int multi = 50;
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
		
		Vert3 a = new Vert3(2, 1 ,0);
		Vert3 b = new Vert3(1, 0 ,0);
		Vert3 c = new Vert3(3, 0 ,0);
		
		Face face = new Face(a,b,c);
		
		ColoredFace cf1 = new ColoredFace(face, Color.RED);
		
		
		
		frame.setVisible(true);
		
		
		Graphics g = panel.getGraphics();
		
		Transformatrix tf = new Transformatrix();
		tf.rotate(new Vector3(0 , 0, 0.3f));

		
		while(true){
			
			g.clearRect(0, 0, width, height);
			
			drawFace(g, cf1);
			
			a.ApplyTransformatrix(tf);
			b.ApplyTransformatrix(tf);
			c.ApplyTransformatrix(tf);


			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		//g.dispose();

		
		/*
		 * TEST CODE ENDS
		 */
		
		
	}
	
	
	public static void drawFace(Graphics g, ColoredFace cf){
		
		g.setColor(cf.color);
		
		g.drawLine(
				
					(int)(cf.face.A.x*multi + offsetX), 
					(int)(-cf.face.A.y*multi + offsetY), 
				
					(int)(cf.face.B.x*multi + offsetX),
					(int)(-cf.face.B.y*multi + offsetY)
					
				);
		
		g.drawLine(
				
				(int)(cf.face.B.x*multi + offsetX), 
				(int)(-cf.face.B.y*multi + offsetY), 
			
				(int)(cf.face.C.x*multi + offsetX),
				(int)(-cf.face.C.y*multi + offsetY)
				
			);
		
		g.drawLine(
				
				(int)(cf.face.C.x*multi + offsetX), 
				(int)(-cf.face.C.y*multi + offsetY), 
			
				(int)(cf.face.A.x*multi + offsetX),
				(int)(-cf.face.A.y*multi + offsetY)
				
			);
		
	}

}
