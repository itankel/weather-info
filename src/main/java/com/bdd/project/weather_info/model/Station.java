package com.bdd.project.weather_info.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Station {
    private int stationsId;
    private String name;
    private String shortName;
    private String stationTag;
    private Location location;
    private int timebase;
    private boolean active;
    private String owner;
    private int regionId;
    private List<Monitor> monitors;
    private String StationTarget;
}
