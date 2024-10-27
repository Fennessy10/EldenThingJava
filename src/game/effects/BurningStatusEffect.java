package game.effects;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.actors.StatusEffect;
import game.enums.Ability;
import game.enums.Weather;
import game.weather.WeatherAffected;

/**
 * Represents a status effect that causes an actor to take burning damage.
 * Effect lasts only one turn, for every turn spent on the burning ground, applying damage and then removing itself.
 */
public class BurningStatusEffect extends StatusEffect implements WeatherAffected {
    private int burningTurns = 0;
    private final int burnDamage;
    private final int burningDuration;
    private Weather currentWeather;  // Store the current weather

    /**
     * Constructor
     */
    public BurningStatusEffect(int burningDamage, int burningDuration) {
        super("Burning");
        this.burnDamage = burningDamage;
        this.burningDuration = burningDuration;
    }

    /**
     * Reacts to changes in weather.
     *
     * @param currentWeather the current weather condition.
     */
    @Override
    public void reactToWeather(Weather currentWeather) {
        this.currentWeather = currentWeather;  // Update the current weather
    }

    /**
     * Applies burning damage to the actor if weather is not sunny. Handles the effect's duration.
     *
     * @param location the location where the actor with the status effect is currently standing
     * @param actor the actor holding the status effect
     */
    @Override
    public void tick(Location location, Actor actor) {
        // Skip damage if the weather is rainy
        if (currentWeather == Weather.RAINY) {
            new Display().println("The rain prevents further burning.");
            return;
        }

        // Apply burning damage if the actor is not fire-resistant
        if (!actor.hasCapability(Ability.FIRE_RESISTANT)) {
            burningTurns++;
            actor.hurt(burnDamage);
            new Display().println(String.format("The %s is burned for %d damage!", actor, burnDamage));

            if (burningTurns == burningDuration) {  // Remove effect after duration
                actor.removeStatusEffect(this);
                burningTurns = 0;  // Reset burning turns
            }
        }
    }
}
