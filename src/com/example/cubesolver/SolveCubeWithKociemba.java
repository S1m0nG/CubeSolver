package com.example.cubesolver;
import org.kociemba.twophase.*;

public class SolveCubeWithKociemba {
	
	private String cube, solution;
	public SolveCubeWithKociemba(String c) {
		// TODO Auto-generated constructor stub
		cube = c;
	}
	
	public void solve(){
		solution = Search.solution(cube, 24, 5, false);
	}
	
	public String returnSolution(){
		return solution;
	}
}
