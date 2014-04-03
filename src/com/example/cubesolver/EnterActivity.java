package com.example.cubesolver;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

public class EnterActivity extends Activity implements View.OnClickListener {

	private FrameLayout cornerLU, cornerCU, cornerRU, cornerLC, cornerCC,
			cornerRC, cornerLD, cornerCD, cornerRD;
	private FrameLayout whiteTile, blueTile, redTile, yellowTile, greenTile,
			orangeTile;
	private Button bSubmit;
	private int[] cube = new int[54];
	private int currentColor = 0;
	private int currentSide = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_enter);
		this.initialize();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.enter, menu);
		return true;
	}

	public void initialize() {
		cornerLU = (FrameLayout) findViewById(R.id.cornerLU);
		cornerLU.setOnClickListener(this);
		cornerCU = (FrameLayout) findViewById(R.id.cornerCU);
		cornerCU.setOnClickListener(this);
		cornerRU = (FrameLayout) findViewById(R.id.cornerRU);
		cornerRU.setOnClickListener(this);
		cornerLC = (FrameLayout) findViewById(R.id.cornerLC);
		cornerLC.setOnClickListener(this);
		cornerCC = (FrameLayout) findViewById(R.id.cornerCC);
		cornerCC.setOnClickListener(this);
		cornerRC = (FrameLayout) findViewById(R.id.cornerRC);
		cornerRC.setOnClickListener(this);
		cornerLD = (FrameLayout) findViewById(R.id.cornerLD);
		cornerLD.setOnClickListener(this);
		cornerCD = (FrameLayout) findViewById(R.id.cornerCD);
		cornerCD.setOnClickListener(this);
		cornerRD = (FrameLayout) findViewById(R.id.cornerRD);
		cornerRD.setOnClickListener(this);

		whiteTile = (FrameLayout) findViewById(R.id.whiteTile);
		whiteTile.setOnClickListener(this);
		blueTile = (FrameLayout) findViewById(R.id.blueTile);
		blueTile.setOnClickListener(this);
		redTile = (FrameLayout) findViewById(R.id.redTile);
		redTile.setOnClickListener(this);
		yellowTile = (FrameLayout) findViewById(R.id.yellowTile);
		yellowTile.setOnClickListener(this);
		greenTile = (FrameLayout) findViewById(R.id.greenTile);
		greenTile.setOnClickListener(this);
		orangeTile = (FrameLayout) findViewById(R.id.orangeTile);
		orangeTile.setOnClickListener(this);

		bSubmit = (Button) findViewById(R.id.bSubmit);
		bSubmit.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.cornerLU:
			this.changeField(cornerLU, (currentSide * 9), currentColor);
			break;
		case R.id.cornerCU:
			this.changeField(cornerCU, (currentSide * 9) + 1, currentColor);
			break;
		case R.id.cornerRU:
			this.changeField(cornerRU, (currentSide * 9) + 2, currentColor);
			break;
		case R.id.cornerLC:
			this.changeField(cornerLC, (currentSide * 9) + 3, currentColor);
			break;
		case R.id.cornerCC:
			this.changeField(cornerCC, (currentSide * 9) + 4, currentColor);
			break;
		case R.id.cornerRC:
			this.changeField(cornerRC, (currentSide * 9) + 5, currentColor);
			break;
		case R.id.cornerLD:
			this.changeField(cornerLD, (currentSide * 9) + 6, currentColor);
			break;
		case R.id.cornerCD:
			this.changeField(cornerCD, (currentSide * 9) + 7, currentColor);
			break;
		case R.id.cornerRD:
			this.changeField(cornerRD, (currentSide * 9) + 8, currentColor);
			break;
		// Pick color
		case R.id.whiteTile:
			currentColor = 0;
			break;
		case R.id.blueTile:
			currentColor = 1;
			break;
		case R.id.redTile:
			currentColor = 2;
			break;
		case R.id.yellowTile:
			currentColor = 3;
			break;
		case R.id.greenTile:
			currentColor = 4;
			break;
		case R.id.orangeTile:
			currentColor = 5;
			break;
		// Submit
		case R.id.bSubmit:
			this.submit();
		}
	}

	public void changeField(FrameLayout field, int tile, int color) {
		switch (color) {
		case 0:
			field.setBackgroundResource(R.drawable.white_tile);
			break;
		case 1:
			field.setBackgroundResource(R.drawable.blue_tile);
			break;
		case 2:
			field.setBackgroundResource(R.drawable.red_tile);
			break;
		case 3:
			field.setBackgroundResource(R.drawable.yellow_tile);
			break;
		case 4:
			field.setBackgroundResource(R.drawable.green_tile);
			break;
		case 5:
			field.setBackgroundResource(R.drawable.orange_tile);
			break;
		}
		cube[tile] = color;
	}

	public void submit() {
		if (currentSide < 5) {
			Toast.makeText(getApplicationContext(), currentSide + " done!", Toast.LENGTH_SHORT).show();
			currentSide++;
		} else {
			String[] code = new String[6];
			code[cube[4+(0*9)]] = "U";
			code[cube[4+(1*9)]] = "R";
			code[cube[4+(2*9)]] = "F";
			code[cube[4+(3*9)]] = "D";
			code[cube[4+(4*9)]] = "L";
			code[cube[4+(5*9)]] = "B";
			String sCube = "";
			for(int i=0;i<cube.length;i++) {
				sCube+=code[cube[i]];
			}
			Log.d(sCube,"Test");
			SolveCubeWithKociemba solver = new SolveCubeWithKociemba("BDUBUULDURBLRRFRUUFRFDFUBDFUBDFDFBLRLRDLLFRRLFLDLBUBBD");
			Log.d("Not yet","Test");
			solver.returnSolution();
			Log.d("Almost","Test");
//			Log.d(solution, "Test");
//			Toast.makeText(getApplicationContext(), "Finished!", Toast.LENGTH_SHORT).show();
		}
	}

}
