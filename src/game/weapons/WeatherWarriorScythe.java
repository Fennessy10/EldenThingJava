package game.weapons;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.effects.BurningStatusEffect;
import game.effects.PoisonedEffect;
import game.enums.Weather;
import game.weather.Atmosphere;
import game.weather.WeatherAffected;

import java.util.Random;

public class WeatherWarriorScythe extends IntrinsicWeapon implements WeatherAffected {
    private final static int weatherWarriorDamage = 20;
    private final static String weatherWarriorVerb = "stings";
    private final static int weatherWarriorHitRate = 90;
    private static final int poisonDamage = 5;
    private static final int poisonDuration = 2;
    private static final int burnDamage = 5;
    private static final int burnDuration = 2;
    private final Atmosphere atmosphere;
    private static final int extraIceDamage = 10;
    private Weather weather;

    private final Random random = new Random();

    public WeatherWarriorScythe(Atmosphere atmosphere) {
        super(weatherWarriorDamage, weatherWarriorVerb, weatherWarriorHitRate);
        this.weather = atmosphere.getCurrentWeather();
        this.atmosphere = atmosphere;
    }

    /**
     * Reacts to changes in weather.
     *
     * @param currentWeather the current weather condition.
     */
    @Override
    public void reactToWeather(Weather currentWeather) {
        this.weather = currentWeather;
        System.out.println("TallAxe reacting to weather: " + currentWeather); // Debug log
    }


    /**
     * attack action method only for WeatherWarrior
     * 25% chance to hit player, if hit on target, have 30% chance to cause poison effect
     *
     * @param attacker the actor who performed the attack
     * @param target   the actor who is the target of the attack
     * @param map      the map on which the attack was executed
     * @return
     */
    @Override
    public String attack(Actor attacker, Actor target, GameMap map) {
        // React to the latest weather before attacking
        reactToWeather(atmosphere.getCurrentWeather());

        int currentDamage = weatherWarriorDamage;
        if (!(random.nextInt(100) < this.hitRate)) {
            return attacker + " misses " + target + ".";
        }

        switch (weather) {
            case SUNNY -> target.addStatusEffect(new BurningStatusEffect(burnDamage, burnDuration));
            case RAINY -> target.addStatusEffect(new PoisonedEffect(poisonDamage, poisonDuration));
            case SNOWY -> currentDamage += extraIceDamage;
        }

        target.hurt(damage);

        return String.format("%s %s %s for %d damage", attacker, verb, target, damage);
    }

}
