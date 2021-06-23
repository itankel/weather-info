package com.bdd.project.weather_info.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class CollectedChannelsSample implements Serializable {
    private String datetime;
    private List<Channel> channels;
}
