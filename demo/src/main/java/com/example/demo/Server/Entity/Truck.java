package com.example.demo.Server.Entity;

public class Truck {

    int id;
    int frontId;
    int backId;
    int speed;
    boolean leader;

    public Truck(int id, int frontId, int backId, int speed, boolean leader) {
        this.id = id;
        this.frontId = frontId;
        this.backId = backId;
        this.speed = speed;
        this.leader = leader;
    }


    public Truck(int id) {
        this.id = id;
    }

    public Truck() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFrontId() {
        return frontId;
    }

    public void setFrontId(int frontId) {
        this.frontId = frontId;
    }

    public int getBackId() {
        return backId;
    }

    public void setBackId(int backId) {
        this.backId = backId;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setLeader(boolean leader) {
        this.leader = leader;
    }
    public Boolean getLeader() { return leader;}

}
