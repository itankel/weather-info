package com.bdd.project.weather_info.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Monitor implements Serializable {
    private int channelId;
    private String name;
    private String alias;
    private boolean active;
    private int typeId;
    private int pollutantId;
    private String units;
    private String description;
}
