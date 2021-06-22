package com.example.demo.Client.Socket;

import com.example.demo.Client.Start;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;

public class SocketServer {

    ServerSocket ss;
    Socket s;
    public SocketServer(int id, int temp) {
        int port = 10000+ id *10 + temp;
        try
        {
            ss = new ServerSocket(port);

            new Timer().schedule(
                    new TimerTask() {
                        @Override
                        public void run() {
                            try {
                                s = ss.accept();
                                DataInputStream din=new DataInputStream(s.getInputStream());

                                String message = din.readUTF();
                                messageHandler(message);
                                System.out.println("Server " + temp + ", client says: "+message);


                            } catch (Exception e)
                            {
                                e.printStackTrace();
                            }

                        }
                    }, 10,1000
            );

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
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

    private void messageHandler(String message)
    {
        if (message.contains("i bin hinter dir"))
        {
            String id = message.substring(16);
            Start.newBackConec(Integer.parseInt(id));
        }

        else if (message.contains("i bin vor dir"))
        {
            String id = message.substring(13);
            Start.newFrontConec(Integer.parseInt(id));
        }

        else if (message.contains("Aktuelle Geschwindigkeit: ")){
            int speed = Integer.parseInt(message.substring(26));
            Start.newSpeed(Integer.parseInt(String.valueOf(speed)));
        }
    }
}

