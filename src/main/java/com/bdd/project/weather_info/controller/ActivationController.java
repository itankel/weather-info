package com.bdd.project.weather_info.controller;

import com.bdd.project.weather_info.model.Location;
import com.bdd.project.weather_info.model.Station;
import com.bdd.project.weather_info.model.StationCollectedData;
import com.bdd.project.weather_info.services.StationsCollector;
import com.bdd.project.weather_info.services.WeatherWebClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import java.util.logging.Logger;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api/")
public class ActivationController {

    @Autowired
    private WeatherWebClient wwc;
    @Autowired
    private StationsCollector stationsCollector;

    @GetMapping("allstations")
    public List<Station> allStationsList(){
        System.out.println("get all stations form IMS");
        return wwc.getAllStations();
    }

    @GetMapping("allregions")
    public String allRegions(){
        log.debug("get all regions form IMS");
        return wwc.getAllRegions().toString();
    }


    @GetMapping("latest/{stationId}")
    public StationCollectedData getLatestCollectedDataFor(@PathVariable int stationId){
        System.out.println("get latest Collected Data from station  "+stationId);
        return wwc.getStationLatestCollectedData(stationId);
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

    @GetMapping("stations/location")
    public List<Location> getAllStationsLocation(){
        System.out.println("get all stations location  ");
        return this.stationsCollector.getCollectStationsLocation();
    }

    @GetMapping("stations/ids")
    public List<Integer> getAllStationsIds(){
        System.out.println("get all stations ids  ");
        return new ArrayList<>(this.stationsCollector.getMeterologicalStationsId());
    }



}
