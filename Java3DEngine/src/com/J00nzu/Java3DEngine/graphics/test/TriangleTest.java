package com.J00nzu.Java3DEngine.graphics.test;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.J00nzu.Java3DEngine.graphics.lowlvl.*;

public class TriangleTest {
	
	final int width = 800;
	final int height = 600;
	final int multi = 50;
	final int offsetX = width/2;
	final int offsetY = height/2;

	
	
	public static void main(String[] args){
		
		
		
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		
		frame.setSize(800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		frame.add(panel);
		
		
				
		/*
		 * TEST CODE HERE
		 */
		
		Vert3 a = new Vert3(0, 1 ,0);
		Vert3 b = new Vert3(-1, 0 ,0);
		Vert3 c = new Vert3(1, 0 ,0);
		
		Face face = new Face(a,b,c);
		
		ColoredFace cf1 = new ColoredFace(face, Color.RED);
		
		
		
		frame.setVisible(true);
		
		
		Graphics g = panel.getGraphics();

		
		while(true){
			
			
			
		}
		
		//g.dispose();

		
		/*
		 * TEST CODE ENDS
		 */
		
		
	}
	
	
	public void DrawFace(Graphics g, ColoredFace cf){
		
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
