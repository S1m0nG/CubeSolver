package com.example.cubesolver;


import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.UUID;



import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.util.Log;

public class BTComm {

//Target NXTs for communication
final String nxt = "00:16:53:02:52:F9";

BluetoothAdapter localAdapter;
BluetoothSocket socket_nxt;
boolean success=false;



//Enables Bluetooth if not enabled
public void enableBT(){
    localAdapter=BluetoothAdapter.getDefaultAdapter();
    //If Bluetooth not enable then do it
    if(localAdapter.isEnabled()==false){
        localAdapter.enable();
        while(!(localAdapter.isEnabled())){

        }
    }

}

//connect to both NXTs
public  boolean connectToNXTs(){



    //get the BluetoothDevice of the NXT
    BluetoothDevice btnxt = localAdapter.getRemoteDevice(nxt);
    //try to connect to the nxt
    try {
        socket_nxt = btnxt.createRfcommSocketToServiceRecord(UUID
                .fromString("00001101-0000-1000-8000-00805F9B34FB"));
        
            socket_nxt.connect();


        success = true;



    } catch (IOException e) {
        Log.d("Bluetooth","Err: Device not found or cannot connect");
        success=false;


    }
    return success;

}


public void writeMessage(byte msg) throws InterruptedException{
    BluetoothSocket connSock;

    connSock=socket_nxt;

    try {
        OutputStreamWriter out=new OutputStreamWriter(connSock.getOutputStream());
//        BufferedWriter bout = new BufferedWriter(out);
        out.write(msg);
        out.flush();

        Thread.sleep(1000);


    } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
}

public int readMessage(){
    BluetoothSocket connSock;
    int n;
    connSock=socket_nxt;

    if(connSock!=null){
        try {

            InputStreamReader in=new InputStreamReader(connSock.getInputStream());
            n=in.read();

            return n;


        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return -1;
        }
    }else{
        //Error
        return -1;
    }

}

}