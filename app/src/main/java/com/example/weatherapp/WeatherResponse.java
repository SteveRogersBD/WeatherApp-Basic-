package com.example.weatherapp;

import java.util.List;

public class WeatherResponse {
    public Coord coord;
    public List<Weather> weather;
    public Main main;
    public Wind wind;
    public Clouds clouds;
    public Sys sys;
    public String name;

    public class Coord {
        public double lon;
        public double lat;
    }

    public class Weather {
        public String main;
        public String description;
    }

    public class Main {
        public double temp;
        public double feels_like;
        public double temp_min;
        public double temp_max;
        public int pressure;
        public int humidity;
    }

    public class Wind {
        public double speed;
        public int deg;
    }

    public class Clouds {
        public int all;
    }

    public class Sys {
        public String country;
        public long sunrise;
        public long sunset;
    }
}

