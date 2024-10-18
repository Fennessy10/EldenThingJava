package game.weapons;

import game.weather.WeatherAffected;

public class TallAxe extends WeaponItem implements WeatherAffected {
    public TallAxe(WeaponArt weaponArt){

        super("TallAxe",'‡', 70, "attack", 80, 15);

        this.setWeaponArt(weaponArt);
    }
}
