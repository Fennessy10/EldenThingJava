package game.weather;

import game.enums.Weather;

public interface WeatherAffected {
    /**
     * Reacts to changes in weather.
     * @param currentWeather the current weather condition.
     */
    void reactToWeather(Weather currentWeather);
}
