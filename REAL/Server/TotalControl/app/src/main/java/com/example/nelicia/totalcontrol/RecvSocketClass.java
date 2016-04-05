package com.example.nelicia.totalcontrol;

import android.util.Log;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by nelicia on 2016-04-04.
 */
public class RecvSocketClass implements Runnable {

    private DataInputStream dataInputStream;
    final private String ERROR_TAG="RecvSocketClass";

    public RecvSocketClass(Socket socket)
    {
        try {
            dataInputStream=new DataInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
            Log.i(ERROR_TAG,"getInuputStream() 애러");
        }
    }
    @Override
    public void run() {

        while(true)
        {
            byte[] buff=new byte[1024];
            try {
                dataInputStream.read(buff);
            } catch (IOException e) {
                e.printStackTrace();
                Log.i(ERROR_TAG,"Recv error");
            }
        }
    }
}
