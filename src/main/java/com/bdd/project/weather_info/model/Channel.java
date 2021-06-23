package com.bdd.project.weather_info.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Channel implements Serializable {
    private int id;
    private String name;
    private String alias;
    private double value;
    private int status;
    private boolean valid;
    private String description;
}
