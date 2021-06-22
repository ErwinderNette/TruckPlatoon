package com.example.demo.Server.Sockets;

import java.io.DataOutputStream;
import java.net.Socket;

public class SocketClient {

    Socket s;
    public SocketClient(int id, int temp){
        int port = 10000 + id*10+temp;

        try{
            s=new Socket("localhost",port);



        }catch(Exception e){System.out.println(e);}
    }



    public void sendMessage(String message)
    {
        try {
            DataOutputStream dout = new DataOutputStream(s.getOutputStream());
            dout.writeUTF(message);
            dout.flush();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}

