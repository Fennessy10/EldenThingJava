package game.weather;

import game.enums.Weather;

import java.util.Random;

public class Atmosphere {
    private Weather currentWeather;

    public Atmosphere() {
        changeWeather();
    }

    /**
     * Changes the weather randomly.
     */
    public void changeWeather() {
        Weather[] weatherConditions = Weather.values();
        Random rand = new Random();
        this.currentWeather = weatherConditions[rand.nextInt(weatherConditions.length)];
    }

    /**
     * Gets the current weather.
     * @return the current weather condition.
     */
    public Weather getCurrentWeather() {
        return currentWeather;
    }
}
