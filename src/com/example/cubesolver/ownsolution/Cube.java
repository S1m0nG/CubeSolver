package com.example.cubesolver.ownsolution;

import android.util.Log;

public class Cube {

	private Object[][][] cube = new Object[3][3][3];

	public Cube(String sCube) {
		cube[0][0][0] = new Corner(sCube.charAt(6 + (9 * 3)),
				sCube.charAt(8 + (9 * 5)), sCube.charAt(6 + (9 * 4)));
		cube[0][0][2] = new Corner(sCube.charAt(0 + (9 * 0)),
				sCube.charAt(0 + (9 * 4)), sCube.charAt(2 + (9 * 5)));
		cube[0][2][0] = new Corner(sCube.charAt(8 + (9 * 3)),
				sCube.charAt(8 + (9 * 1)), sCube.charAt(6 + (9 * 5)));
		cube[0][2][2] = new Corner(sCube.charAt(2 + (9 * 0)),
				sCube.charAt(0 + (9 * 5)), sCube.charAt(2 + (9 * 1)));
		cube[2][0][0] = new Corner(sCube.charAt(0 + (9 * 3)),
				sCube.charAt(8 + (9 * 4)), sCube.charAt(6 + (9 * 2)));
		cube[2][0][2] = new Corner(sCube.charAt(0 + (9 * 0)),
				sCube.charAt(0 + (9 * 2)), sCube.charAt(2 + (9 * 4)));
		cube[2][2][0] = new Corner(sCube.charAt(2 + (9 * 3)),
				sCube.charAt(8 + (9 * 2)), sCube.charAt(6 + (9 * 1)));
		cube[2][2][2] = new Corner(sCube.charAt(8 + (9 * 0)),
				sCube.charAt(0 + (9 * 1)), sCube.charAt(2 + (9 * 2)));

		cube[2][1][2] = new Edge(sCube.charAt(7 + (9 * 0)),
				sCube.charAt(1 + (9 * 2)));
		cube[1][0][2] = new Edge(sCube.charAt(3 + (9 * 0)),
				sCube.charAt(1 + (9 * 4)));
		cube[0][1][2] = new Edge(sCube.charAt(1 + (9 * 0)),
				sCube.charAt(1 + (9 * 5)));
		cube[1][2][2] = new Edge(sCube.charAt(5 + (9 * 0)),
				sCube.charAt(1 + (9 * 1)));
		cube[2][0][1] = new Edge(sCube.charAt(3 + (9 * 2)),
				sCube.charAt(5 + (9 * 4)));
		cube[0][0][1] = new Edge(sCube.charAt(5 + (9 * 5)),
				sCube.charAt(3 + (9 * 4)));
		cube[0][2][1] = new Edge(sCube.charAt(3 + (9 * 5)),
				sCube.charAt(5 + (9 * 1)));
		cube[2][2][1] = new Edge(sCube.charAt(5 + (9 * 2)),
				sCube.charAt(3 + (9 * 1)));
		cube[2][1][0] = new Edge(sCube.charAt(1 + (9 * 3)),
				sCube.charAt(7 + (9 * 2)));
		cube[1][0][0] = new Edge(sCube.charAt(3 + (9 * 3)),
				sCube.charAt(7 + (9 * 4)));
		cube[0][1][0] = new Edge(sCube.charAt(7 + (9 * 3)),
				sCube.charAt(7 + (9 * 5)));
		cube[1][2][0] = new Edge(sCube.charAt(5 + (9 * 3)),
				sCube.charAt(7 + (9 * 1)));

		int[] test = this.searchCorner("U", "F", "L");
		Log.d(test[0] + "," + test[1] + "," + test[2], "Test");
	}

	public void rotate(String side, int turns) {
		if (side.equals("U")) {
			for (int i = 0; i < turns; i++) {
				Corner tmp = (Corner) cube[0][0][2];
				cube[0][0][2] = cube[2][0][2];
				cube[2][0][2] = cube[2][2][2];
				cube[2][2][2] = cube[0][2][2];
				cube[0][2][2] = tmp;

				Edge tmpe = (Edge) cube[1][0][2];
				cube[1][0][2] = cube[2][1][2];
				cube[2][1][2] = cube[1][2][2];
				cube[1][2][2] = cube[0][1][2];
				cube[0][1][2] = tmpe;
			}
		} else if (side.equals("D")) {
			turns = 4 - turns;
			for (int i = 0; i < turns; i++) {
				Corner tmp = (Corner) cube[0][0][0];
				cube[0][0][0] = cube[2][0][0];
				cube[2][0][0] = cube[2][2][0];
				cube[2][2][0] = cube[0][2][0];
				cube[0][2][0] = tmp;

				Edge tmpe = (Edge) cube[1][0][0];
				cube[1][0][0] = cube[2][1][0];
				cube[2][1][0] = cube[1][2][0];
				cube[1][2][0] = cube[0][1][0];
				cube[0][1][0] = tmpe;
			}
		} else if (side.equals("R")) {
			for (int i = 0; i < turns; i++) {
				Corner tmp = (Corner) cube[2][2][2];
				cube[2][2][2] = new Corner(
						((Corner) cube[2][2][0]).getColor2(),
						((Corner) cube[2][2][0]).getColor3(),
						((Corner) cube[2][2][0]).getColor1());
				cube[2][2][0] = new Corner(
						((Corner) cube[0][2][0]).getColor3(),
						((Corner) cube[0][2][0]).getColor1(),
						((Corner) cube[0][2][0]).getColor2());
				cube[0][2][0] = new Corner(
						((Corner) cube[0][2][2]).getColor2(),
						((Corner) cube[0][2][2]).getColor3(),
						((Corner) cube[0][2][2]).getColor1());
				cube[0][2][2] = new Corner(tmp.getColor3(), tmp.getColor1(),
						tmp.getColor2());

				Edge tmpe = (Edge) cube[1][2][2];
				cube[1][2][2] = cube[2][2][1];
				cube[2][2][1] = cube[1][2][0];
				cube[1][2][0] = cube[0][2][1];
				cube[0][2][1] = tmpe;
			}
		} else if (side.equals("L")) {
			turns = 4 - turns;
			for (int i = 0; i < turns; i++) {
				Corner tmp = (Corner) cube[2][0][2];
				cube[2][0][2] = new Corner(
						((Corner) cube[2][0][0]).getColor3(),
						((Corner) cube[2][0][0]).getColor1(),
						((Corner) cube[2][0][0]).getColor2());
				cube[2][0][0] = new Corner(
						((Corner) cube[0][0][0]).getColor2(),
						((Corner) cube[0][0][0]).getColor3(),
						((Corner) cube[0][0][0]).getColor1());
				cube[0][0][0] = new Corner(
						((Corner) cube[0][0][2]).getColor3(),
						((Corner) cube[0][0][2]).getColor1(),
						((Corner) cube[0][0][2]).getColor2());
				cube[0][0][2] = new Corner(tmp.getColor2(), tmp.getColor3(),
						tmp.getColor1());

				Edge tmpe = (Edge) cube[1][0][2];
				cube[1][0][2] = cube[2][0][1];
				cube[2][0][1] = cube[1][0][0];
				cube[1][0][0] = cube[0][0][1];
				cube[0][0][1] = tmpe;
			}
		} else if (side.equals("F")) {
			for (int i = 0; i < turns; i++) {
				Corner tmp = (Corner) cube[2][0][2];
				cube[2][0][2] = new Corner(
						((Corner) cube[2][0][0]).getColor2(),
						((Corner) cube[2][0][0]).getColor3(),
						((Corner) cube[2][0][0]).getColor1());
				cube[2][0][0] = new Corner(
						((Corner) cube[2][2][0]).getColor3(),
						((Corner) cube[2][2][0]).getColor1(),
						((Corner) cube[2][2][0]).getColor2());
				cube[2][2][0] = new Corner(
						((Corner) cube[2][2][2]).getColor2(),
						((Corner) cube[2][2][2]).getColor3(),
						((Corner) cube[2][2][2]).getColor1());
				cube[2][2][2] = new Corner(tmp.getColor3(), tmp.getColor1(),
						tmp.getColor2());

				Edge tmpe = (Edge) cube[2][1][2];
				cube[2][1][2] = new Edge(((Edge) cube[2][0][1]).getColor2(),
						((Edge) cube[2][0][1]).getColor1());
				cube[2][0][1] = new Edge(((Edge) cube[2][1][0]).getColor2(),
						((Edge) cube[2][1][0]).getColor1());
				cube[2][1][0] = new Edge(((Edge) cube[2][2][1]).getColor2(),
						((Edge) cube[2][2][1]).getColor1());
				cube[2][2][1] = new Edge(tmpe.getColor2(), tmpe.getColor1());
			}
		} else if (side.equals("B")) {
			turns = 4 - turns;
			for (int i = 0; i < turns; i++) {
				Corner tmp = (Corner) cube[0][0][2];
				cube[0][0][2] = new Corner(
						((Corner) cube[0][0][0]).getColor3(),
						((Corner) cube[0][0][0]).getColor1(),
						((Corner) cube[0][0][0]).getColor2());
				cube[0][0][0] = new Corner(
						((Corner) cube[0][2][0]).getColor2(),
						((Corner) cube[0][2][0]).getColor3(),
						((Corner) cube[0][2][0]).getColor1());
				cube[0][2][0] = new Corner(
						((Corner) cube[0][2][2]).getColor3(),
						((Corner) cube[0][2][2]).getColor1(),
						((Corner) cube[0][2][2]).getColor2());
				cube[0][2][2] = new Corner(tmp.getColor2(), tmp.getColor3(),
						tmp.getColor1());

				Edge tmpe = (Edge) cube[0][1][2];
				cube[0][1][2] = new Edge(((Edge) cube[0][0][1]).getColor2(),
						((Edge) cube[0][0][1]).getColor1());
				cube[0][0][1] = new Edge(((Edge) cube[0][1][0]).getColor2(),
						((Edge) cube[0][1][0]).getColor1());
				cube[0][1][0] = new Edge(((Edge) cube[0][2][1]).getColor2(),
						((Edge) cube[0][2][1]).getColor1());
				cube[0][2][1] = new Edge(tmpe.getColor2(), tmpe.getColor1());
			}
		}
	}

	public int[] searchEdge(String c1, String c2) {
		for (int i = 0; i < 3; i++) {
			for (int k = 0; k < 3; k++) {
				for (int l = 0; l < 3; l++) {
					if (cube[i][k][l] instanceof Edge) {
						if (((Edge) cube[i][k][l]).hasColors(c1, c2)) {
							int[] r = { i, k, l };
							return r;
						}
					}
				}
			}
		}
		return null;
	}

	public int[] searchCorner(String c1, String c2, String c3) {
		for (int i = 0; i < 3; i++) {
			for (int k = 0; k < 3; k++) {
				for (int l = 0; l < 3; l++) {
					if (cube[i][k][l] instanceof Corner
							&& ((Corner) cube[i][k][l]).hasColors(c1, c2, c3)) {
						int[] r = { i, k, l };
						return r;
					}
				}
			}
		}
		return null;
	}

	public class Corner {

		// color1 is the color, which is parallel to the top center, color2 and
		// color3 following clockwise
		private String color1, color2, color3;

		public Corner(String c1, String c2, String c3) {
			setColor1(c1);
			setColor2(c2);
			setColor3(c3);
		}

		public Corner(char c1, char c2, char c3) {
			setColor1(c1 + "");
			setColor2(c2 + "");
			setColor3(c3 + "");
		}

		public boolean hasColors(String c1, String c2, String c3) {
			return (c1.equals(color1) && c2.equals(color2) && c3.equals(color3))
					|| (c1.equals(color2) && c2.equals(color3) && c3
							.equals(color1))
					|| (c1.equals(color3) && c2.equals(color1) && c3
							.equals(color2));
		}

		@Override
		public String toString() {
			return getColor1() + ", " + getColor2() + ", " + getColor3();
		}

		public String getColor1() {
			return color1;
		}

		public void setColor1(String color1) {
			this.color1 = color1;
		}

		public String getColor2() {
			return color2;
		}

		public void setColor2(String color2) {
			this.color2 = color2;
		}

		public String getColor3() {
			return color3;
		}

		public void setColor3(String color3) {
			this.color3 = color3;
		}
	}

	public class Edge {

		// color1 is the color which is parallel to the top (preferred) or front
		// center
		private String color1, color2;

		public Edge(String c1, String c2) {
			setColor1(c1);
			setColor2(c2);
		}

		public Edge(char c1, char c2) {
			setColor1(c1 + "");
			setColor2(c2 + "");
		}

		public boolean hasColors(String c1, String c2) {
			return (c1.equals(color1) && c2.equals(color2))
					|| (c1.equals(color2) && c2.equals(color1));
		}

		@Override
		public String toString() {
			return getColor1() + ", " + getColor2();
		}

		public String getColor1() {
			return color1;
		}

		public void setColor1(String color1) {
			this.color1 = color1;
		}

		public String getColor2() {
			return color2;
		}

		public void setColor2(String color2) {
			this.color2 = color2;
		}
	}
}
