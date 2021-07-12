package com.bdd.project.weather_info.services;

import com.bdd.project.weather_info.model.Location;
import com.bdd.project.weather_info.model.Region;
import com.bdd.project.weather_info.model.Station;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class StationsCollector {

    private final static String METEROLOGICAL_STATION = "METEROLOGICAL";
    private List<Station> stationsList;
    private Set<Integer> meterologicalStationsId;
    private WeatherWebClient weatherWebClient;

    // create the METEROLOGICAL stations list
    StationsCollector(WeatherWebClient weatherWebClient) {
        this.weatherWebClient = weatherWebClient;
        initiateStations();
    }

    @Scheduled(cron = "${weatherinfo.stations.collection.cron.expression}")
    private void initiateStations() {
        stationsList = weatherWebClient.getAllStations();
        List<Integer> stationsIds = stationsList.stream()
                .mapToInt(Station::getStationId)
                .boxed()
                .collect(Collectors.toList());
        log.debug("stationslist size = " + stationsList.size());
        log.debug("stationslist is now " + stationsIds);

        // since only from the regions I can understand which are the METEROLOGICAL stations
        List<Region> regionList = weatherWebClient.getAllRegions();
        meterologicalStationsId = regionList.stream().map(Region::getStations)
                .flatMap(List::stream)
                .filter(station -> station.getStationTarget().equals(METEROLOGICAL_STATION))
                .mapToInt(Station::getStationId)
                .boxed()
                .collect(Collectors.toSet());

        log.debug("meterologicalStationsId=>" + meterologicalStationsId);
        // remove from the stations "repository " the Non-METEROLOGICAL_STATION
        stationsList.removeIf(station -> (!meterologicalStationsId.contains(station.getStationId())));
        log.debug("stationslist count = " + (long) stationsList.size());
        log.debug("stationslist is now " + stationsList.stream().mapToInt(Station::getStationId).boxed().collect(Collectors.toList()));
    }

    public List<Location> getCollectStationsLocation() {
        // this is to be used by forecastService
        return stationsList.stream()
                .map(Station::getLocation)
                .collect(Collectors.toList());
    }


    public Set<Integer> getMeterologicalStationsId() {
        return meterologicalStationsId;
    }
}

