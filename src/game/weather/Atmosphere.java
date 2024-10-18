package game.weather;

import game.enums.Weather;

import java.util.Random;

public class Atmosphere {
    private Weather currentWeather;

    public Atmosphere() {
        changeWeather();
        if (currentWeather == null) {
            currentWeather = Weather.SUNNY; // Default weather
        }
    }

    /**
     * Changes the weather randomly.
     */
    public void changeWeather() {
        Weather[] weatherConditions = Weather.values();
        Random rand = new Random();
        if (rand.nextInt(20)<3) {
            this.currentWeather = weatherConditions[rand.nextInt(weatherConditions.length)];
        }

    }

    /**
     * Gets the current weather.
     * @return the current weather condition.
     */
    public Weather getCurrentWeather() {
        return currentWeather;
    }
}
