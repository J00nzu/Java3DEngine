package com.J00nzu.Java3DEngine.graphics.lowlvl;

public class CohenSutherlandClipper {
	
	// Java implmenetation of the Cohen Sutherland algorithm
	// Implemented from http://www.geeksforgeeks.org/line-clipping-set-1-cohen-sutherland-algorithm/
	
	// region codes
	final int INSIDE = 0; // 000000
	final int LEFT = 1;   // 000001
	final int RIGHT = 2;  // 000010
	final int BOTTOM = 4; // 000100
	final int TOP = 8;    // 001000
	final int BEHIND = 16;// 010000
	final int FRONT = 32; // 100000
	
	float x_max, y_max, z_max;
	float x_min, y_min, z_min;
	
	public CohenSutherlandClipper(float x_max, float x_min, float y_max, float y_min, float z_max, float z_min){
		this.x_max = x_max;
		this.y_max = y_max;
		this.z_max = z_max;
		
		this.x_min = x_min;
		this.y_min = y_min;
		this.z_min = z_min;
	}
		
	private int computeCode(Vector3 point){
		return computeCode(point.x, point.y, point.z);
	}
	
	private int computeCode(float x, float y, float z){
		
		int code = INSIDE;
		
		if(x < x_min){
			code |= LEFT;
		}else if(x > x_max){
			code |= RIGHT;
		}
		
		if(y < y_min){
			code |= BOTTOM;
		}else if(y > y_max){
			code |= TOP;
		}
		
		if(z < z_min){
			code |= BEHIND;
		}else if(z > z_max){
			code |= FRONT;
		}
		
		
		return code;
	}
	
	public boolean clip(Line3 line){
		
		Vector3 a = line.a;
		Vector3 b = line.b;
		
		int code1 = computeCode(a);
		int code2 = computeCode(b);
		
		boolean accept = false;
		
		while(true){
			if((code1 == 0) && (code2 == 0)){
				accept = true;
				break;
			}else if((code1 & code2) != 0){
				
				break;
			}else{
				
				
				int code_out;
				float x=0, y=0, z=0;
				
				if(code1 != 0){
					code_out = code1;
				}else{
					code_out = code2;
				}
				
				if((code_out & TOP) != 0){
					x = a.x + (b.x - a.x) * (y_max - a.y) / (b.y - a.y);
					z = a.z + (b.z - a.z) * (y_max - a.y) / (b.y - a.y);
	                y = y_max;
				}else if((code_out & BOTTOM) != 0){
					x = a.x + (b.x - a.x) * (y_min - a.y) / (b.y - a.y);
					z = a.z + (b.z - a.z) * (y_min - a.y) / (b.y - a.y);
	                y = y_min;
				}else if((code_out & RIGHT) != 0){
					y = a.y + (b.y - a.y) * (x_max - a.x) / (b.x - a.x);
					z = a.z + (b.z - a.z) * (x_max - a.x) / (b.x - a.x);
	                x = x_max;
				}else if((code_out & LEFT) != 0){
					y = a.y + (b.y - a.y) * (x_min - a.x) / (b.x - a.x);
					z = a.z + (b.z - a.z) * (x_min - a.x) / (b.x - a.x);
	                x = x_min;
				}else if((code_out & FRONT) != 0){
					y = a.y + (b.y - a.y) * (z_max - a.z) / (b.z - a.z);
					x = a.x + (b.x - a.x) * (z_max - a.z) / (b.z - a.z);
	                z = z_max;
				}else if((code_out & BEHIND) != 0){
					y = a.y + (b.y - a.y) * (z_min - a.z) / (b.z - a.z);
					x = a.x + (b.x - a.x) * (z_min - a.z) / (b.z - a.z);
	                z = z_min;
				}
				
				if (code_out == code1)
	            {
	                a.x = x;
	                a.y = y;
	                a.z = z;
	                code1 = computeCode(a);
	            }
	            else
	            {
	                b.x = x;
	                b.y = y;
	                b.z = z;
	                code2 = computeCode(b);
	            }
				
				
				
			}
		}
		
		return accept;
	}

}
