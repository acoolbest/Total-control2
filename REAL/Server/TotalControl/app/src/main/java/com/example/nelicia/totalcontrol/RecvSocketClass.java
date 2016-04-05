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
    private Thread thread;

    public RecvSocketClass(Socket socket)
    {
        thread=new Thread(this);
        try {
            dataInputStream=new DataInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
            Log.i(ERROR_TAG, "getInuputStream() 애러");
        }
    }
    @Override
    public void run() {
        byte[] buff=new byte[1024];
        while(true)
        {
            try {
                dataInputStream.read(buff);
            } catch (IOException e) {
                e.printStackTrace();
                Log.i(ERROR_TAG,"Recv error");
            }
        }
    }

    public synchronized void waitThread()
    {
        try {
            thread.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void notifyThread()
    {
        thread.notify();
    }

    public void threadStart()
    {
        thread.start();
    }
}
