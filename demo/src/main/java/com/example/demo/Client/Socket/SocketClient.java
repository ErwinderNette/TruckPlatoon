package com.example.demo.Client.Socket;

import com.example.demo.Client.Start;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;

public class SocketClient {

    Socket s;
    DataOutputStream dout;
    int temp;
    int id;
    public SocketClient(int id, int temp){
        this.temp = temp;
        this.id = id;
        int port = 10000 + id*10 + temp;

        try{
            s=new Socket("localhost",port);

        }catch(Exception e){System.out.println(e);}
    }

    public void listen()
    {
        new Timer().schedule(
                new TimerTask() {
                    @Override
                    public void run() {
                        try {
                            DataInputStream din = new DataInputStream(s.getInputStream());
                            String message = din.readUTF();
                            messageHandler(message);
                            System.out.println("Server says :" + message);


                        } catch (Exception e)
                        {
                            e.printStackTrace();
                        }

                    }
                }, 10, 1000
        );
    }

    public void closeConnection()
    {
        try{
            dout.close();
            s.close();
        }catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    public void sendMessage(String message)
    {
        try {
            dout = new DataOutputStream(s.getOutputStream());
            dout.writeUTF(message);
            dout.flush();
            System.out.println("temp: " + temp + " send Client " + id + ": " + message);
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

        else if (message.contains("Aktuelle Geschwindigkeit: "))
        {
            int speed = Integer.parseInt(message.substring(26));
            Start.newSpeed(Integer.parseInt(String.valueOf(speed)));
        }
    }
}

