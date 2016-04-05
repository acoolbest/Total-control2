package com.example.nelicia.totalcontrol;

import android.util.Log;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by nelicia on 2016-04-04.
 */
public class RecvClass implements Runnable {

    final static String ERROR_TEG="SendSocketClass";
    private DataInputStream dataInputStream;

    public RecvClass(Socket socket)
    {
        try {
            dataInputStream=new DataInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
            Log.i(ERROR_TEG, "DataOutputStream 에러");
        }
    }


    @Override
    public void run() {

        while(true)
        {

        }
    }
}
