package com.bdd.project.weather_info.controller;

import com.bdd.project.weather_info.services.WeatherWebClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class ActivationController {

    @Autowired
    private WeatherWebClient wwc;

    @GetMapping("allstations")
    public String allStationsList(){
        System.out.println("get all stations form IMS");
        return wwc.getAllStations().toString();
    }

    @GetMapping("latest/{stationId}")
    public String getLatestCollectedDataFor(@PathVariable int stationId){
        System.out.println("get latest Collected Data from station  "+stationId);
        return wwc.getStationLatestCollectedData(stationId).toString();
    }


    @GetMapping("latest/{stationId}/for/{channelId}")
    public String getLatestChannelCollectedDataFor(@PathVariable int stationId,@PathVariable int channelId){
        System.out.println("get latest Collected Data from station  "+stationId + " for channel "+channelId);
        return wwc.getStationLatestCollectedDataSpecificChannel(stationId,channelId).toString();
    }

    @GetMapping("daily/{stationId}")
    public String getDailyCollectedDataFor(@PathVariable int stationId){
        System.out.println("get Daily Collected Data from station  "+stationId);
        return wwc.getStationDailyCollectedData(stationId).toString();
    }

    @GetMapping("daily/{stationId}/for/{channelId}")
    public String getDailyChannelCollectedDataFor(@PathVariable int stationId,@PathVariable int channelId){
        System.out.println("get Daily Collected Data from station  "+stationId + " for channel "+ channelId);
        return wwc.getStationDailyChannelCollectedData(stationId,channelId).toString();
    }

}
