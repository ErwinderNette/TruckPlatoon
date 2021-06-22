package com.example.demo.Client;

import com.example.demo.Client.Socket.SocketClient;
import com.example.demo.Client.Socket.SocketServer;
import com.example.demo.Client.model.Truck;
import com.example.demo.Server.Controller.Controller;
import com.example.demo.Server.Repository.TruckRepo;

import java.io.IOException;
import java.net.http.HttpClient;

import static com.example.demo.Client.HTTPConec.register;

public class Start {


    private static Truck truck;
    private static SocketServer server;
    private static SocketServer firstConnectionServer;
    private static SocketClient front;
    private static SocketClient back;
    private static boolean frontExist;

    public static void main(String[] args) {


        try {
            truck = HTTPConec.register(10);

            if (truck.getFrontId() != 0)
            {
                front = new SocketClient(truck.getFrontId(), 2);
                front.sendMessage("i bin hinter dir" + truck.getId()); // "Aktuelle Geschwindigkeit: "); //+ truck.getSpeed());
                front.closeConnection();
                frontExist = true;
            }
            if (truck.getBackId() != 0)
            {
                back = new SocketClient(truck.getBackId(), 1);
                back.listen();
                back.sendMessage("i bin vor dir" + truck.getId()); //"Aktuelle Geschwindigkeit: "); //+truck.getSpeed());
            }
            server = new SocketServer(truck.getId(), 1);
            firstConnectionServer = new SocketServer(truck.getId(), 2);
            HTTPConec.connect(truck.getId());
            System.out.println(truck);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void newSpeed(int speed){

        truck.setSpeed(speed);
        if (back != null){
            back.sendMessage("Aktuelle Geschwindigkeit: "+ truck.getSpeed());
        }
    }

    public static void newFrontConec(int id){
        frontExist = true;
        server.sendMessage("Aktuelle Geschwindigkeit: " + truck.getSpeed());
    }

    public static void newBackConec(int id){
        truck.setBackId(id);
        back = new SocketClient(truck.getBackId(), 1);
        back.listen();
        back.sendMessage("Aktuelle Geschwindigkeit: "+ truck.getSpeed());
    }

}
