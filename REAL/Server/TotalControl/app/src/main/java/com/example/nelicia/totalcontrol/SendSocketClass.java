package com.example.nelicia.totalcontrol;



import android.util.Log;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketException;

/**
 * Created by nelicia on 2016-04-04.
 */
public class SendSocketClass extends Thread {

    final static private String LOG_TEG="SendSocketClass";
    final static private int STOP_FLAG=0;
    final static private int START_FLAG=1;
    final static private int WAIT_FLAG=2;

    final static private int sendBufferSize=9;
    private int threadFlag=START_FLAG;
    private DataOutputStream dataOutputStream;
    private byte[] buffer;

    public SendSocketClass(Socket socket)
    {
    	//System.out.println("SendSocketClass 생성");
        Log.i(LOG_TEG, "SendSocketClass 생성");
    	threadFlag=START_FLAG;
    	buffer=new byte[1024];
        try {
            dataOutputStream=new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
            
        }
    }
    
    public void stopThread()
    {
    	if(threadFlag==WAIT_FLAG)
    	{
    		threadFlag=STOP_FLAG;
    		notifyThread();
    	}
    }

    public int send(byte[] buffer)
    {
        if(threadFlag==WAIT_FLAG) {
            this.buffer = buffer;
            notifyThread();
            return 0;
        }
        else
        {
            return -1;
        }
    }


    @Override
    public void run() {
    	//System.out.println("SendSocketClass run 시작");
        Log.i(LOG_TEG,"SendSocketClass run 시작");
        while(threadFlag==START_FLAG | threadFlag==WAIT_FLAG)
        {
            waitThread();
            try {
            	dataOutputStream.write(buffer,0,9);
            }catch(SocketException e){
            	e.printStackTrace();
            	break;
            }catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
        }
        //System.out.println("SendSocketClass run 종료");
        Log.i(LOG_TEG,"SendSocketClass run 종료");
    }

    public synchronized void waitThread()
    {
        try {
        	threadFlag=WAIT_FLAG;
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void notifyThread()
    {
        threadFlag=START_FLAG;
        notify();
    }
}
