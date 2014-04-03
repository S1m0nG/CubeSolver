package com.example.cubesolver;
import com.example.cubesolver.solver.*;

import com.example.cubesolver.solver.Search;

import android.util.Log;

public class SolveCubeWithKociemba {
	
	private String cube, solution;
	public SolveCubeWithKociemba(String c) {
		// TODO Auto-generated constructor stub
		cube = c;
	}
	
	public void solve(){
		solution = Search.solution(cube, 24, 15, false);
	}
	
	public String returnSolution(){
		Log.d("SolveCubeWithKociemba", "Test");
		return Search.solution("BDUBUULDURBLRRFRUUFRFDFUBDFUBDFDFBLRLRDLLFRRLFLDLBUBBD", 24, 15, false);
	}
}
