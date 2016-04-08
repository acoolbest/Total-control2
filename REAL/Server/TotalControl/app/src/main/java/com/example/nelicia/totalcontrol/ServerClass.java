package com.example.nelicia.totalcontrol;

import android.util.Log;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by nelicia on 2016-04-04.
 */
public class ServerClass extends Thread {

    private final static String LOG_TEG="ServerClass";
	private static ServerClass instance=null;
    public ServerSocket serverSocket;
    private int port;
    private ArrayList<SendSocketClass> sendSocketClasses;
    private ArrayList<RecvSocketClass> recvSocketClasses;
    private int ctrIndex;

    private ServerClass(int port)
    {
    	try {
    		//System.out.println("socket ��");
            ctrIndex=0;
            serverSocket=new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    	sendSocketClasses=new ArrayList<SendSocketClass>();
        recvSocketClasses=new ArrayList<RecvSocketClass>();
    }


    
    public static ServerClass getInstance(int port)
    {
    	if(instance==null)
    	{
    		instance=new ServerClass(port);
    	}
    	else
    	{
    		//System.out.println("�̹� �ν��Ͻ��� �ֽ��ϴ�."+instance.serverSocket.getLocalPort());
            Log.i(LOG_TEG,"이미 인스턴스가 있습니다.");
        }
    	return instance;
    }
    @Override
    public void run() {

        while (true)
        {
            try {
            	//System.out.println("accept 대기");
                Log.i(LOG_TEG,"accept대기중");
                Socket sock=serverSocket.accept();
                
                SendSocketClass sendSocketClass=new SendSocketClass(sock);
                
                socketListRefresh(sock, sendSocketClass);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void send(byte[] buffer)
    {
        if(sendSocketClasses.size()!=0)
        {
            sendSocketClasses.get(ctrIndex).send(buffer);
        }
    }
    
    private synchronized void socketListRefresh(Socket sock,SendSocketClass sendSocketClass)
    {
    	sendSocketClasses.add(sendSocketClass);
        recvSocketClasses.add(new RecvSocketClass(sock,sendSocketClass,sendSocketClasses,recvSocketClasses));
        sendSocketClasses.get(sendSocketClasses.size()-1).start();
        recvSocketClasses.get(recvSocketClasses.size()-1).start();
    }
}
