package com.example.nelicia.totalcontrol;

import android.util.Log;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by nelicia on 2016-04-04.
 */
public class SendSocketClass implements Runnable {

    final static String ERROR_TEG="SendSocketClass";
    private DataOutputStream dataOutputStream;
    private byte[] buffer;
    private Thread thread;

    public SendSocketClass(Socket socket)
    {
        thread=new Thread(this);
        try {
            dataOutputStream=new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
            Log.i(ERROR_TEG, "getOutputStream() 에러");
        }
    }

    public int send(byte[] buffer)
    {
        this.buffer=buffer;
        thread.start();
        return 0;
    }


    @Override
    public void run() {

        while(true)
        {
            waitThread();
            try {
                dataOutputStream.write(buffer,0,1024);
            } catch (IOException e) {
                e.printStackTrace();
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
