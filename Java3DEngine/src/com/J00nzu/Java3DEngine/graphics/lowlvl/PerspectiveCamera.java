package com.J00nzu.Java3DEngine.graphics.lowlvl;

public class PerspectiveCamera extends Camera{
	
	float FOV = 90;
	float far = 1000;
	float near = 1f;
	float displayRatio = 800f/600f;
	
   
    
    /*
     * Code applied from
     * https://www.scratchapixel.com/lessons/3d-basic-rendering/perspective-and-orthographic-projection-matrix/building-basic-perspective-projection-matrix
     */
    public Transformatrix GetProjectionMatrix(){
    	 // set the basic projection matrix
    	
    	Matrix4 projection = new Matrix4();
    	
        float scaleY = (float) (1 / Math.tan(FOV * 0.5 * Math.PI / 180)); 
        float scaleX = (float) (1 / (displayRatio * Math.tan(FOV * 0.5 * Math.PI / 180))); 
        
        /*
        projection.matrix[0][0] = scaleX; // scale the x coordinates of the projected point 
        projection.matrix[1][1] = scaleY; // scale the y coordinates of the projected point 
        
        
        projection.matrix[2][2] = -far / (far - near); // used to remap z to [0,1] 
        projection.matrix[3][2] = -far * near / (far - near); // used to remap z [0,1] 
        
        projection.matrix[2][3] = -1; // set w = -z 
        projection.matrix[3][3] = 0;
        */
        
        float frustumDepth = far - near;
        float oneOverDepth = 1 / frustumDepth;

        projection.matrix[1][1] = (float) (1 / Math.tan(FOV * 0.5 * Math.PI / 180));
        projection.matrix[0][0] = projection.matrix[1][1] / displayRatio;
        projection.matrix[2][2] = far * oneOverDepth;
        projection.matrix[3][2] = (-far * near) * oneOverDepth;
        projection.matrix[2][3] = 1;
        projection.matrix[3][3] = 0;

        
        //TODO Z axis doesn't map correctly maybe... Needs testing
        
        
        /*
        float zNear = near;
        float zFar = far;
        float zRange = zNear - zFar;
        
        projection.matrix[2][2] = (-zNear - zFar) / zRange;
        projection.matrix[2][3] = 2.0f * zFar * zNear / zRange;
        
        projection.matrix[3][2] = 1.0f; 
        projection.matrix[3][3] = 0;
        */
        
        Transformatrix matrix = new Transformatrix(projection);
        
        return matrix;
    }
	
	
	
	
	

}
