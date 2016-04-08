package com.example.nelicia.totalcontrol;

import android.util.Log;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by nelicia on 2016-04-04.
 */
public class RecvSocketClass extends Thread {

    private DataInputStream dataInputStream;
    private SendSocketClass sendSocketClass;
    private ArrayList<SendSocketClass> sendSocketClasses;
    private ArrayList<RecvSocketClass> recvSocketClasses;
    
    final private String LOG_TEG="RecvSocketClass";
    final static private int STOP_FLAG=0;
    final static private int START_FLAG=1;
    final static private int WAIT_FLAG=2;
    private int threadFlag=START_FLAG;
    
    

    public RecvSocketClass(Socket socket,SendSocketClass sendSocketClass,ArrayList<SendSocketClass> sendSocketClasses,ArrayList<RecvSocketClass> recvSocketClasses)
    {
        //System.out.println("RecvSocketClass 생성");
        Log.i(LOG_TEG, "RecvSocketClass 생성");
        threadFlag=START_FLAG;
        
        this.sendSocketClasses=sendSocketClasses;
        this.recvSocketClasses=recvSocketClasses;
        this.sendSocketClass=sendSocketClass;
        
        try {
            dataInputStream=new DataInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
            
        }
    }
    @Override
    public void run() {
        byte[] buff=new byte[1024];
        //System.out.println("RecvSocketClass run 시작");
        Log.i(LOG_TEG, "RecvSocketClass run 시작");
        while(threadFlag==START_FLAG | threadFlag==WAIT_FLAG)
        {
            try {
            	
                dataInputStream.read(buff);
                //System.out.println(new String(buff));
                Log.i(LOG_TEG, new String(buff));
                
            } catch (IOException e) {
                e.printStackTrace();
                sendSocketClass.stopThread();
                
                try {
					sendSocketClass.join();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                socketListRefresh();
                break;
                
            }
        }
        //System.out.println("RecvSocketClass run 종료");
        Log.i(LOG_TEG, "RecvSocketClass run 종료");
    }
    
    private synchronized void socketListRefresh()
    {
    	SendSocketClass iter;
    	while((iter=sendSocketClasses.iterator().next()) != null)
    	{
    		if(iter.isAlive()==false)
    		{
    			int index=sendSocketClasses.indexOf(iter);
    			sendSocketClasses.remove(index);
    			recvSocketClasses.remove(index);
    		}
    	}
    }

    public synchronized void waitThread()
    {
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void notifyThread()
    {
        notify();
    }

}
