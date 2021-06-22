package com.example.demo.Server.Service;

import com.example.demo.Server.Entity.Truck;
import com.example.demo.Server.Repository.TruckRepo;
import com.example.demo.Server.Sockets.SocketClient;

import java.util.List;

public class TruckService {

    TruckRepo truckRepo = new TruckRepo();
    SocketClient leader;


    public Truck addTruck(int position) {
        Truck truck = truckRepo.addTruck(position);
        return truck;
    }

    public List<Truck> getTruckList() {
        return truckRepo.getTruckList();
    }

    // beschleunigen
    public void accelerate(int speed) {

        int newSpeed;
        int oldSpeed = truckRepo.detectSpeed();
        if (oldSpeed != -1) {
            if (oldSpeed + speed >= 200) {
                newSpeed = 200;
            } else {

                newSpeed = oldSpeed + speed;
            }
            truckRepo.setSpeed(newSpeed);
            leader.sendMessage("Aktuelle Geschwindigkeit: " + newSpeed);
        }

    }

    // Bremsen
    public void slowdown(int speed) {

        int newSpeed;
        int oldSpeed = truckRepo.detectSpeed();
        if (oldSpeed != -1) {
            if (oldSpeed - speed <= 0) {
                newSpeed = 0;
            } else {
                newSpeed = oldSpeed - speed;
            }
            truckRepo.setSpeed(newSpeed);
            leader.sendMessage("Aktuelle Geschwindigkeit: " + newSpeed);
        }

    }

    // Truck stoppen
    public void stop() {

        truckRepo.setSpeed(0);
        if (leader != null) {
            leader.sendMessage("Aktuelle Geschwindigkeit: " + 0);
        }

    }

    // Exit Platoon
    public void exitPlatoon(int id) {
        truckRepo.exit(id);
    }

    // Auswahl aller Trucks über ID
    public Truck getTruckById(int id) {
        return truckRepo.getTruckById(id);
    }

    // Auswahl von einem Truck über ID
    public Truck getOneTruckById(int id) {
        return truckRepo.getOneTruckById(id);
    }

    public void verbunden(int id) {
        if (id == truckRepo.getTruckLeaderId()) {
            leader = new SocketClient(truckRepo.getTruckLeaderId(), 1);
        }
    }
}