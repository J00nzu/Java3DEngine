package com.J00nzu.Java3DEngine.graphics.lowlvl;

import java.util.Iterator;

public class Face implements Iterable<Vert3>{
	
	public Vert3 A, B, C;

	public Face(Vert3 a, Vert3 b, Vert3 c) {
		super();
		A = a;
		B = b;
		C = c;
	}

	@Override
	public Iterator<Vert3> iterator() {
		// TODO Auto-generated method stub
		
		Iterator<Vert3> it = new Iterator<Vert3>(){
			
			private int index = 0;

			@Override
			public boolean hasNext() {
				return index <= 2;
			}

			@Override
			public Vert3 next() {
				Vert3 vertex = null;
				
				switch(index){
					case 0:
						vertex = A;
						break;
					case 1:
						vertex = B;
						break;
					case 2:
						vertex = C;
						break;
				}
				
				index++;
				
				return vertex;
			}
			
			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}
			
		};
		
		return it;
	}
	

}
