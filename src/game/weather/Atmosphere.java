package game.weather;

import game.enums.Weather;
import java.util.Random;

/**
 * The Atmosphere class manages weather conditions in the game.
 * It allows for random weather changes and provides the current weather status.
 */
public class Atmosphere {
    private Weather currentWeather;

    /**
     * Constructor that initializes the atmosphere with sunny weather by default.
     */
    public Atmosphere() {
        currentWeather = Weather.SUNNY; // Default weather
    }

    /**
     * Changes the weather randomly with a 20% chance of modification.
     * If triggered, selects a new weather condition from the available options.
     */
    public void changeWeather() {
        Weather[] weatherConditions = Weather.values();
        Random rand = new Random();
        if (rand.nextInt(20) <= 3) {
            this.currentWeather = weatherConditions[rand.nextInt(weatherConditions.length)];
        }
    }

    /**
     * Returns the current weather condition.
     *
     * @return the current weather.
     */
    public Weather getCurrentWeather() {
        return currentWeather;
    }
}
