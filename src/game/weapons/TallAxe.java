package game.weapons;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.effects.BurningStatusEffect;
import game.effects.PoisonedEffect;
import game.enums.Weather;
import game.weaponarts.WeaponArt;
import game.weather.Atmosphere;
import game.weather.WeatherAffected;

import java.util.Random;

/**
 * Represents the TallAxe, a weapon that reacts to weather conditions and applies different effects.
 * Depending on the weather, the axe can burn, poison, or deal extra ice damage to enemies.
 */
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

    /**
     * Constructs a TallAxe with a specific WeaponArt and access to the current Atmosphere.
     *
     * @param weaponArt  the special ability associated with this weapon.
     * @param atmosphere the atmosphere that provides weather updates.
     */
    public TallAxe(WeaponArt weaponArt, Atmosphere atmosphere) {
        super(weaponName, displayChar, baseDamage, verb, hitRate, strengthRequirement);
        this.atmosphere = atmosphere;
        this.weather = atmosphere.getCurrentWeather(); // Initialize with current weather
        this.setWeaponArt(weaponArt);
    }

    /**
     * Reacts to the current weather by updating the internal weather state.
     *
     * @param currentWeather the current weather condition from the atmosphere.
     */
    @Override
    public void reactToWeather(Weather currentWeather) {
        this.weather = currentWeather;
        System.out.println("TallAxe reacting to weather: " + currentWeather); // Debug log
    }

    /**
     * Executes an attack using the TallAxe, applying damage and weather-based effects.
     *
     * @param attacker the Actor using the TallAxe.
     * @param target   the target Actor being attacked.
     * @param map      the GameMap where the attack occurs.
     * @return a string describing the result of the attack.
     */
    @Override
    public String attack(Actor attacker, Actor target, GameMap map) {
        reactToWeather(atmosphere.getCurrentWeather()); // Update weather before attacking

        int currentDamage = baseDamage;
        Random rand = new Random();
        String result = "";

        // Check if the attack hits
        if (!(rand.nextInt(100) < hitRate)) {
            result += "\n" + attacker + " misses " + target + ".";
            return result;
        }

        // Apply weather-specific effects
        switch (weather) {
            case SUNNY -> target.addStatusEffect(new BurningStatusEffect(burnDamage, burnDuration));
            case RAINY -> target.addStatusEffect(new PoisonedEffect(poisonDamage, poisonDuration));
            case SNOWY -> currentDamage += extraIceDamage;
        }

        target.hurt(Math.round(currentDamage * damageMultiplier)); // Apply damage
        result += String.format("\n%s %s %s for %d damage", attacker, verb, target, currentDamage);

        return result;
    }
}
