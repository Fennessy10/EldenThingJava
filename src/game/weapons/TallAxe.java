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
    private static final int hitRate = 80;
    private static final int strengthRequirement = 15;
    private static final String verb = "attack";
    private static final char displayChar = '‡';
    private static final String weaponName = "TallAxe";
    private static final float damageMultiplier = 1.0f;
    private static final int poisonDamage = 5;
    private static final int poisonDuration = 1;
    private static final int extraIceDamage = 20;
    private final Atmosphere atmosphere;

    private Weather weather;

    public TallAxe(WeaponArt weaponArt, Atmosphere atmosphere) {
        super(weaponName, displayChar, baseDamage, verb, hitRate, strengthRequirement);
        this.setWeaponArt(weaponArt);
        this.atmosphere = atmosphere;
    }

    /**
     * Reacts to changes in weather.
     *
     * @param currentWeather the current weather condition.
     */
    @Override
    public void reactToWeather(Weather currentWeather) {
        weather = currentWeather;
    }

    /**
     * Executes an attack with this weapon.
     *
     * @param attacker The actor attacking.
     * @param target   The actor being attacked.
     * @param map      The game map where the attack occurs.
     * @return A string describing the outcome of the attack.
     */
    @Override
    public String attack(Actor attacker, Actor target, GameMap map) {
        // Ensure weather is not null, defaulting to SUNNY if it is
        Weather currentWeather = atmosphere.getCurrentWeather();
        System.out.println("Current weather in attack(): " + currentWeather); // Debug log

        int currentDamage = baseDamage;  // Local damage variable to avoid modifying the original value
        Random rand = new Random();
        String result = "";

        if (!(rand.nextInt(100) < hitRate)) {
            result += "\n" + attacker + " misses " + target + ".";
            return result;
        }

        // Apply weather-based effects
        switch (currentWeather) {
            case SUNNY -> target.addStatusEffect(new BurningStatusEffect());
            case RAINY -> target.addStatusEffect(new PoisonedEffect(poisonDamage, poisonDuration));
            case SNOWY -> currentDamage += extraIceDamage;  // Increase local damage
        }

        // Apply the damage to the target
        target.hurt(Math.round(currentDamage * damageMultiplier));
        result += String.format("\n%s %s %s for %d damage", attacker, verb, target, currentDamage);

        return result;
    }
}
