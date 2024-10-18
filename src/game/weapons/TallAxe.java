package game.weapons;

import game.weather.WeatherAffected;

public class TallAxe extends WeaponItem implements WeatherAffected {
    public TallAxe(WeaponArt weaponArt){

        super("TallAxe",'‡', 60, "attack", 80, 15);

        this.setWeaponArt(weaponArt);
    }
}
