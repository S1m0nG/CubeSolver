package com.example.cubesolver;

import java.util.Set;

import android.os.Bundle;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener {

	private BluetoothAdapter mBluetoothAdapter;
	private Switch switch1;
	private LinearLayout linDev;
	private Button bTakePic, bColor, bSend;
	private ImageView ivReturnedPic;
	private Intent i;
	private final static int cameraData = 0;
	private Bitmap bmp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		this.initialize();
		this.bluetoothSwitch();

	}

	private void initialize() {
		mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
		switch1 = (Switch) findViewById(R.id.switch1);

		ivReturnedPic = (ImageView) findViewById(R.id.ivReturnedPic);
		bTakePic = (Button) findViewById(R.id.bTakePic);
		bTakePic.setOnClickListener(this);
		bColor = (Button) findViewById(R.id.bColor);
		bColor.setOnClickListener(this);
		bSend = (Button)findViewById(R.id.bSend);
		bSend.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void bluetoothSwitch() {

		if (mBluetoothAdapter == null) {
			Toast.makeText(getApplicationContext(),
					"Your Smartphone does not support Bluetooth",
					Toast.LENGTH_SHORT).show();
		}
		if (mBluetoothAdapter.isEnabled()) {
			switch1.setChecked(true);
		}
	}

	public void bluetoothSwitchOnClick(View view) {
		Intent enableBtIntent = null;
		if (switch1.isChecked()) {
			enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
			startActivityForResult(enableBtIntent, 5);
			// this.addDevicesToLayout();
		}
		if (!switch1.isChecked()) {
			mBluetoothAdapter.disable();

		}
	}

	public void addDevicesToLayout() {
		ArrayAdapter mArrayAdapter = null;
		Set<BluetoothDevice> pairedDevices = mBluetoothAdapter
				.getBondedDevices();
		// If there are paired devices
		if (pairedDevices.size() > 0) {
			// Loop through paired devices
			for (BluetoothDevice device : pairedDevices) {

				// Add the name and address to an array adapter to show in a
				// ListView
				mArrayAdapter
						.add(device.getName() + "\n" + device.getAddress());
			}
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.bTakePic:
			i = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
			startActivityForResult(i, cameraData);
			break;
		case R.id.bColor:
			Toast.makeText(getApplicationContext(), "Front Side",
					Toast.LENGTH_SHORT).show();
			Intent enter = new Intent(MainActivity.this, EnterActivity.class);
			startActivity(enter);
			break;
		case R.id.bSend:
			Toast.makeText(getApplicationContext(), "Front Side",
					Toast.LENGTH_SHORT).show();
			BTComm btcomm = new BTComm();
			btcomm.enableBT();
			btcomm.connectToNXTs();
			try {
				btcomm.writeMessage(Byte.parseByte("21"));
				btcomm.writeMessage(Byte.parseByte("11"));
				btcomm.writeMessage(Byte.parseByte("21"));
				btcomm.writeMessage(Byte.parseByte("11"));
				btcomm.writeMessage(Byte.parseByte("21"));
				btcomm.writeMessage(Byte.parseByte("11"));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK) {
			Bundle extras = data.getExtras();
			bmp = (Bitmap) extras.get("data");
			ivReturnedPic.setImageBitmap(bmp);
		}
	}
}
