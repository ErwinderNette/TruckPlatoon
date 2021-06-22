package com.example.demo.Server.Controller;

import com.example.demo.Server.Entity.Truck;
import com.example.demo.Server.Service.TruckService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controller {

    Truck truck = new Truck(1,2,3,80,true);
    TruckService truckService = new TruckService();

//    @RequestMapping(path="/test")
//    public @ResponseBody Truck test(){
//        return truckService.getTruckById(2);
//    }

    @GetMapping(path="/addTruck/{position}")
    public @ResponseBody Truck addTruck(@PathVariable int position){

        return  truckService.addTruck(position);
    }

    @RequestMapping(path="/getPlatoon")
    public List<Truck> getPlatoon(){
        return truckService.getTruckList();
    }

    @RequestMapping(path ="/showTruckAgentById/{id}")
    public @ResponseBody Truck show(){
        return truckService.getOneTruckById(truck.getId());
    }

    @RequestMapping(path = "/stop")
    public @ResponseBody
    String stop(){
        truckService.stop();
        return "gestoppt";
    }

    @RequestMapping(path ="/slowdown/{speed}" )
    public @ResponseBody
    String slowdown(@PathVariable int speed){
        truckService.slowdown(speed);
        return "slowgedowned";
    }

    @RequestMapping(path = "/accelerate/{speed}")
    public String accelerate(@PathVariable int speed){
       truckService.accelerate(speed);
       return "beschleunigt";
    }

    @RequestMapping(path = "/exit/{id}")
    public String exit(@PathVariable int id){
        truckService.exitPlatoon(id);
        return "Erfolgreich gelöscht";
    }

    @GetMapping(path= "/SocketDa/{id}")
    public String socketDa(@PathVariable int id){
        truckService.verbunden(id);
        return "verbunden";
    }
}
