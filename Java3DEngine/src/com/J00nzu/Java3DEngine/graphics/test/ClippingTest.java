package com.J00nzu.Java3DEngine.graphics.test;

import java.awt.*;

import javax.swing.*;

import com.J00nzu.Java3DEngine.graphics.lowlvlnew.*;

public class ClippingTest {
	
	static JFrame frame;
	
	static final int width = 800;
	static final int height = 600;
	static final int offsetX = width/2;
	static final int offsetY = height/2;
	
	
	static PerspectiveCamera cam;
	
	public static void initWorld(){
		cam = new PerspectiveCamera();
	}
	
	public static void main(String[] args){
		
		initWorld();
		
		frame = new JFrame();
		
		NormalView nView = new NormalView();
		WorldView wView = new WorldView();
		ViewspaceView vView = new ViewspaceView();
		
		nView.setPreferredSize(new Dimension(width/2, height/2));
		wView.setPreferredSize(new Dimension(width/2, height/2));
		vView.setPreferredSize(new Dimension(width/2, height/2));
		
		GridLayout gridLayout = new GridLayout(2,2);
		
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		
		frame.setLayout(gridLayout);
		
		frame.add(nView);
		frame.add(wView);
		frame.add(vView);
		
		frame.setVisible(true);
		frame.pack();
		
		while(true){
			
			//cam.transform.position.z+=0.01f;
			cam.transform.rotation.y+=0.005f;
			frame.repaint();
			
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	
	static class WorldView extends JPanel{
		
		int width;
		int height;
		int plusX;
		int plusY;
		
		
		int multiplier = 5;
		
		
		public WorldView(){
			
		}
		
		@Override
		public void paintComponent(Graphics g) {
	        super.paintComponent(g);       

	        g.drawString("World View", 15, 15);
	       
	        
	        width = this.getWidth();
	        height = this.getHeight();
	        
	        g.drawLine(0, 0, width, 0);
	        g.drawLine(0, 0, 0, height);
	        g.drawLine(0, height-1, width, height-1);
	        g.drawLine(width-1, 0, width-1, height);
	        
	        plusX = width/2;
	        plusY = height/2;
	        	        
	        
	        g.drawOval((int)(cam.transform.position.x*multiplier + plusX - multiplier/2), (int)(-cam.transform.position.z*multiplier + plusY  - multiplier/2), multiplier, multiplier);
	        
	        Vector2 n1, n2, f1, f2;
	        n1 = new Vector2((float)(Math.tan(cam.getFOV()/2 * Math.PI / 180)*cam.getNear()), (float)(cam.getNear()));
	        n2 = new Vector2((float)(-Math.tan(cam.getFOV()/2 * Math.PI / 180)*cam.getNear()), (float)(cam.getNear()));
	        f1 = new Vector2((float)(Math.tan(cam.getFOV()/2 * Math.PI / 180)*cam.getFar()), (float)(cam.getFar()));
	        f2 = new Vector2((float)(-Math.tan(cam.getFOV()/2 * Math.PI / 180)*cam.getFar()), (float)(cam.getFar()));
	        
	        n1 = n1.rotate(cam.transform.rotation.y);
	        n2 = n2.rotate(cam.transform.rotation.y);
	        f1 = f1.rotate(cam.transform.rotation.y);
	        f2 = f2.rotate(cam.transform.rotation.y);
	        
	        n1 = n1.add(new Vector2(cam.transform.position, Vector2.CONVERSION_Z_TO_Y));
	        n2 = n2.add(new Vector2(cam.transform.position, Vector2.CONVERSION_Z_TO_Y));
	        f1 = f1.add(new Vector2(cam.transform.position, Vector2.CONVERSION_Z_TO_Y));
	        f2 = f2.add(new Vector2(cam.transform.position, Vector2.CONVERSION_Z_TO_Y));
	        
	        drawLine(g, n1, n2);
	        drawLine(g, f1, f2);
	        drawLine(g, n1, f1);
	        drawLine(g, n2, f2);
	    }
		
		private void drawLine(Graphics g, Vector2 p1, Vector2 p2){
			
			g.drawLine(
					
					(int)(p1.x*multiplier + plusX), 
					(int)(-p1.y*multiplier + plusY), 
				
					(int)(p2.x*multiplier + plusX),
					(int)(-p2.y*multiplier + plusY)
					
				);
		}
		
	}
	static class ViewspaceView extends JPanel{
		
		public ViewspaceView(){
			
		}
		
		@Override
		public void paintComponent(Graphics g) {
	        super.paintComponent(g);       

	        g.drawString("Viewspace View", 15, 15);
	        
	        int width = this.getWidth();
	        int height = this.getHeight();
	        
	        g.drawLine(0, 0, width, 0);
	        g.drawLine(0, 0, 0, height);
	        g.drawLine(0, height-1, width, height-1);
	        g.drawLine(width-1, 0, width-1, height);

	    } 
	}
	
	static class NormalView extends JPanel{
		
		
		public NormalView(){
			
		}
		
		@Override
		public void paintComponent(Graphics g) {
	        super.paintComponent(g);   
	        
	        g.drawString("Normal View", 15, 15);
	        
	        int width = this.getWidth();
	        int height = this.getHeight();
	        
	        g.drawLine(0, 0, width, 0);
	        g.drawLine(0, 0, 0, height);
	        g.drawLine(0, height-1, width, height-1);
	        g.drawLine(width-1, 0, width-1, height);

	        
	    } 
		
	}
}
