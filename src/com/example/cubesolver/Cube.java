package com.example.cubesolver;

public class Cube {

	private Object[][][] wuerfel = new Object[3][3][3];
	
	public Cube() {
		// TODO Auto-generated constructor stub
		//[1] = "X-Koordinate", [2] = "Y-Koordinate", [3] = "Z-Koordinate"
		
	}
	
	//0 = weiﬂ, 1 = gelb, 2 = rot, 3 = orange, 4 = gruen, 5 = blau
	
	public class Corner {
		
		//c1 ist die Farbe, die zu der roten Seite parallel ist
		private int color1, color2, color3;
		
		public Corner(int c1, int c2, int c3){
			color1 = c1;
			color2 = c2;
			color3 = c3;
		}
	}
	
	
	public class Edge {
		
		public Edge() {
			
		}
		
	}
}
