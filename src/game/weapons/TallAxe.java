package game.weapons;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.effects.BurningStatusEffect;
import game.effects.PoisonedEffect;
import game.enums.Weather;
import game.weather.Atmosphere;
import game.weather.WeatherAffected;

import java.util.Random;

public class TallAxe extends WeaponItem implements WeatherAffected {
    private static final int baseDamage = 70;
    private static final int hitRate = 75;
    private static final int strengthRequirement = 15;
    private static final String verb = "attack";
    private static final char displayChar = '‡';
    private static final String weaponName = "TallAxe";
    private static final float damageMultiplier = 1.0f;
    private static final int poisonDamage = 10;
    private static final int poisonDuration = 3;
    private static final int burnDamage = 10;
    private static final int burnDuration = 3;
    private static final int extraIceDamage = 40;
    private final Atmosphere atmosphere;
    private Weather weather;

    public TallAxe(WeaponArt weaponArt, Atmosphere atmosphere) {
        super(weaponName, displayChar, baseDamage, verb, hitRate, strengthRequirement);
        this.atmosphere = atmosphere;
        this.weather = atmosphere.getCurrentWeather();  // Initialize with current weather
        this.setWeaponArt(weaponArt);
    }

    @Override
    public void reactToWeather(Weather currentWeather) {
        this.weather = currentWeather;
        System.out.println("TallAxe reacting to weather: " + currentWeather); // Debug log
    }

    @Override
    public String attack(Actor attacker, Actor target, GameMap map) {
        // React to the latest weather before attacking
        reactToWeather(atmosphere.getCurrentWeather());

        int currentDamage = baseDamage;
        Random rand = new Random();
        String result = "";

        if (!(rand.nextInt(100) < hitRate)) {
            result += "\n" + attacker + " misses " + target + ".";
            return result;
        }

        switch (weather) {
            case SUNNY -> target.addStatusEffect(new BurningStatusEffect(burnDamage, burnDuration));
            case RAINY -> target.addStatusEffect(new PoisonedEffect(poisonDamage, poisonDuration));
            case SNOWY -> currentDamage += extraIceDamage;
        }

        target.hurt(Math.round(currentDamage * damageMultiplier));
        result += String.format("\n%s %s %s for %d damage", attacker, verb, target, currentDamage);

        return result;
    }
}
