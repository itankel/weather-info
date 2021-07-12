package com.bdd.project.weather_info.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@AllArgsConstructor
@Data
@NoArgsConstructor
public class Region {
     private int regionId;
     private String name;//: "None",
     private List<Station>  stations;//": []
}
