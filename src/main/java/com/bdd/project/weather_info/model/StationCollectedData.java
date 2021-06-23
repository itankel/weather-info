package com.bdd.project.weather_info.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;


@AllArgsConstructor
@Data
@NoArgsConstructor
public class StationCollectedData implements Serializable {
    private int stationId;
    private List<CollectedChannelsSample> data;
}
