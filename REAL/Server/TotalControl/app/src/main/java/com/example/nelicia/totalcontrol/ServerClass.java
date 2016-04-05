package com.example.nelicia.totalcontrol;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by nelicia on 2016-04-04.
 */
public class ServerClass implements Runnable {

    private ServerSocket server;
    private int port;

    private ServerClass()
    {
        try {
            server=new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

        while (true)
        {
            try {
                Socket sock=server.accept();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
