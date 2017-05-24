package com.J00nzu.Java3DEngine.graphics.lowlvl;
import java.util.ArrayList;
import java.util.Collection;


public abstract class Obj {
	
	private Obj parent;
	
	private ArrayList<Obj> children = new ArrayList<Obj>();
	
	public Transform transform = new Transform();
	
	public Obj(){
		
	}

	public void AddChild(Obj child){
		if(child != null){
			this.children.add(child);
			
			if(child.parent!=null){
				child.parent.RemoveChild(child);
			}
			
			child.parent = this;
		}
	}

	void RemoveChild (Obj child){
		children.remove(child);
	}
	
	public void Delete(){
		if(parent!=null){
			parent.RemoveChild(this);
		}
		
		for(Obj child : children){
			child.Delete();
		}
	}


}
