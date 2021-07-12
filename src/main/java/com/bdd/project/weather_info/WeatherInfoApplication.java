package com.bdd.project.weather_info;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class WeatherInfoApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context= SpringApplication.run(WeatherInfoApplication.class, args);
    }

}
