package com.J00nzu.Java3DEngine.graphics.lowlvl;

public class PerspectiveCamera extends Camera{
	
	float FOV = 90;
	float far = 100;
	float near = 0.3f;
	float displayRatio = 800f/600f;
	
   
    
    /*
     * Code applied from
     * https://www.scratchapixel.com/lessons/3d-basic-rendering/perspective-and-orthographic-projection-matrix/building-basic-perspective-projection-matrix
     */
    public Transformatrix GetProjectionMatrix(){
    	 // set the basic projection matrix
    	
    	Matrix4 projection = new Matrix4();
    	
        float scale = (float) (1 / Math.tan(FOV * 0.5 * Math.PI / 180)); 
        
        projection.matrix[0][0] = scale; // scale the x coordinates of the projected point 
        projection.matrix[1][1] = scale / displayRatio; // scale the y coordinates of the projected point 
        projection.matrix[2][2] = -far / (far - near); // used to remap z to [0,1] 
        projection.matrix[3][2] = -far * near / (far - near); // used to remap z [0,1] 
        projection.matrix[2][3] = -1; // set w = -z 
        projection.matrix[3][3] = 0;
        
        Transformatrix matrix = new Transformatrix(projection);
        
        return matrix;
    }
	
	
	
	
	

}
