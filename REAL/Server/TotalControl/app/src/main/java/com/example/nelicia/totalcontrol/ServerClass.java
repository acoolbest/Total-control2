package com.example.nelicia.totalcontrol;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by nelicia on 2016-04-04.
 */
public class ServerClass implements Runnable {

    private ServerSocket server;
    private int port;
    private ArrayList<SendSocketClass> sendSocketClasses;
    private ArrayList<RecvSocketClass> recvSocketClasses;

    private ServerClass(int port)
    {
        try {
            server=new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }

        sendSocketClasses=new ArrayList<SendSocketClass>();
        recvSocketClasses=new ArrayList<RecvSocketClass>();
    }

    @Override
    public void run() {

        while (true)
        {
            try {
                Socket sock=server.accept();
                sendSocketClasses.add(new SendSocketClass(sock));
                recvSocketClasses.add(new RecvSocketClass(sock));
                sendSocketClasses.get(sendSocketClasses.size()-1).threadStart();
                recvSocketClasses.get(recvSocketClasses.size()-1).threadStart();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
