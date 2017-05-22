
public class Vertex {
	
	public float x, y, z;
	
	public Vertex(Vertex toClone) {
		super();
		this.x = toClone.x;
		this.y = toClone.y;
		this.z = toClone.z;
	}
	
	public Vertex(){
		super();
		this.x = 0;
		this.y = 0;
		this.z = 0;
	}
	
	public Vertex(float x, float y, float z) {
		super();
		this.x = x;
		this.y = y;
		this.z = z;
	}


	public Vertex Add(Vertex other){
		Vertex out = new Vertex(this);
		out.x += other.x;
		out.y += other.y;
		out.z += other.z;
		
		return out;
	}
	
	public Vertex Subtract(Vertex other){
		Vertex out = new Vertex(this);
		out.x -= other.x;
		out.y -= other.y;
		out.z -= other.z;
		
		return out;
	}
	
	public Vertex Multiply(Vertex other){
		Vertex out = new Vertex(this);
		out.x *= other.x;
		out.y *= other.y;
		out.z *= other.z;
		
		return out;
	}
	
	public Vertex Divide(Vertex other){
		Vertex out = new Vertex(this);
		out.x /= other.x;
		out.y /= other.y;
		out.z /= other.z;
		
		return out;
	}

}
