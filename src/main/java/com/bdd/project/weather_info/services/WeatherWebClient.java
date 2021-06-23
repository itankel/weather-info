package com.bdd.project.weather_info.services;

import com.bdd.project.weather_info.configuration.ApiConfiguration;
import com.bdd.project.weather_info.model.Station;
import com.bdd.project.weather_info.model.StationCollectedData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import javax.annotation.PostConstruct;
import java.util.List;

@Slf4j
@Component
public class WeatherWebClient {
    private static final String GET_ALL_STATIONS_PATH = "/stations";
    //https://api.ims.gov.il/v1/envista/stations/%7b%25ST_ID%25%7d/data/latest
    private static final String GET_STATION_X_LATEST_DATA="/stations/{stationId}/data/latest";
    //https://api.ims.gov.il/v1/envista/stations/%7b%25ST_ID%25%7d/data/%7b%25CH_ID%25%7d/latest
    private static final String GET_STATION_X_LATEST_CHANNEL_X_DATA="/stations/{stationId}/data/{channelId}/latest";
    //https://api.ims.gov.il/v1/envista/stations/%7b%25ST_ID%25%7d/data/daily
    private static final String GET_STATION_X_DAILY_DATA="/stations/{stationId}/data/daily";
    //https://api.ims.gov.il/v1/envista/stations/%7b%25ST_ID%25%7d/data/daily
    private static final String GET_STATION_X_DAILY_CHANNEL_X_DATA="/stations/{stationId}/data/{channelId}/daily";



    private WebClient imsWebClient;
    @Value("${header.set.param.value}")
    private String imsParamValue;
    @Value("${header.set.param.name}")
    private String imsParamName;

    @Autowired
    private ApiConfiguration config;

    public WeatherWebClient() { System.out.println("default constructor of WhetherWebClient"); }//app fails to start without this
    public WeatherWebClient(WebClient weatherInfoWebClient) {
        this.imsWebClient = weatherInfoWebClient;
    }

    @PostConstruct
    private void init() {
        log.debug("postConstruct of WeatherWebClient");
        imsWebClient = WebClient.create(config.getWeatherIMSUrl());
        if (config == null) {
            log.debug(" config == null");
        } else {
            log.debug("config not null apiUrl is " + config.getWeatherIMSUrl());
        }

    }

    public List<Station> getAllStations() {
        return imsWebClient.get()
                .uri(GET_ALL_STATIONS_PATH)
                .headers(h->h.set(imsParamName,imsParamValue))
                .retrieve()
                .bodyToFlux(Station.class)
                .collectList()
                .block();
    }

    public StationCollectedData getStationLatestCollectedData(int stationId) {
            return imsWebClient.get()
                    .uri(GET_STATION_X_LATEST_DATA, stationId)
                    .headers(h->h.set(imsParamName,imsParamValue))
                    .retrieve()
                    .bodyToMono(StationCollectedData.class)
                    .block();

    }

    public StationCollectedData getStationLatestCollectedDataSpecificChannel(int stationId,int channelId) {
        return imsWebClient.get()
                .uri(GET_STATION_X_LATEST_CHANNEL_X_DATA, stationId, channelId)
                .headers(h->h.set(imsParamName,imsParamValue))
                .retrieve()
                .bodyToMono(StationCollectedData.class)
                .block();

    }


    public StationCollectedData getStationDailyCollectedData(int stationId) {
        return imsWebClient.get()
                .uri(GET_STATION_X_DAILY_DATA, stationId)
                .headers(h->h.set(imsParamName,imsParamValue))
                .retrieve()
                .bodyToMono(StationCollectedData.class)
                .block();

    }

    public StationCollectedData getStationDailyChannelCollectedData(int stationId,int channelId) {
        return imsWebClient.get()
                .uri(GET_STATION_X_DAILY_CHANNEL_X_DATA, stationId,channelId)
                .headers(h->h.set(imsParamName,imsParamValue))
                .retrieve()
                .bodyToMono(StationCollectedData.class)
                .block();

    }



}
