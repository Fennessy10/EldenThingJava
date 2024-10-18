package game.weapons;

import game.enums.Ability;
import game.enums.Weather;
import game.weather.WeatherAffected;

public class TallAxe extends WeaponItem implements WeatherAffected {
    private static int tallAxeDamage = 70;

    public TallAxe(WeaponArt weaponArt){

        super("TallAxe",'‡', tallAxeDamage, "attack", 80, 15);

        this.setWeaponArt(weaponArt);
    }

    /**
     * Reacts to changes in weather.
     *
     * @param currentWeather the current weather condition.
     */
    @Override
    public void reactToWeather(Weather currentWeather) {
        switch (currentWeather) {
            case SUNNY -> this.addCapability(Ability.FIERY);
            case RAINY -> this.addCapability(Ability.DOUSED);
            case SNOWY -> tallAxeDamage = 100;
        }
    }
}
