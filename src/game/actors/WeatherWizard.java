package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.AttackAction;
import game.behaviours.WanderBehaviour;
import game.enums.Ability;
import game.enums.Status;
import game.enums.Weather;
import game.weather.Atmosphere;
import game.weather.WeatherAffected;

import java.util.*;

/**
 * The WeatherWizard wanders around and changes the weather.
 * Upon unconsciousness, it explodes and respawns in the same location.
 */
public class WeatherWizard extends Actor implements WeatherAffected {
    private final Map<Integer, Behaviour> weatherWizardBehaviours = new TreeMap<>();
    private static final int weatherWizardHP = 50;
    private final Atmosphere atmosphere;

    /**
     * Constructor for the WeatherWizard class.
     *
     * @param atmosphere the atmosphere instance controlling weather changes.
     */
    public WeatherWizard(Atmosphere atmosphere) {
        super("WeatherWizard", '^', weatherWizardHP);
        this.atmosphere = atmosphere;
        weatherWizardBehaviours.put(999, new WanderBehaviour());
        addCapability(Ability.POISON_RESISTANT);
        addCapability(Ability.FIRE_RESISTANT);
        addCapability(Status.ENEMY);
    }

    /**
     * Selects and returns an action to perform on the current turn.
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction the Action this Actor took last turn
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the Action to be performed
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        atmosphere.changeWeather();
        display.println("WeatherWizard chooses: " + atmosphere.getCurrentWeather() + " for the next turn");
        reactToWeather(atmosphere.getCurrentWeather());

        // Execute behavior logic using the TreeMap.
        for (Behaviour behaviour : weatherWizardBehaviours.values()) {
            Action action = behaviour.getAction(this, map);
            if (action != null) return action;
        }

        return new DoNothingAction();
    }

    /**
     * Determines the actions other actors can perform on the WeatherWizard.
     *
     * @param otherActor the Actor that might be performing an attack
     * @param direction  the direction of the other Actor
     * @param map        the current GameMap
     * @return an ActionList of available actions an actor can perform on the WeatherWizard
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            actions.add(new AttackAction(this, direction));
        }
        return actions;
    }

    /**
     * Handles logic when the WeatherWizard becomes unconscious. It explodes,
     * dealing damage to nearby actors and spawning a new WeatherWizard.
     *
     * @param actor the actor responsible for the WeatherWizard's unconscious state
     * @param map   the GameMap where the WeatherWizard is located
     * @return a message describing the result of the explosion and respawn
     */
    @Override
    public String unconscious(Actor actor, GameMap map) {
        Location wizardLocation = map.locationOf(this);
        String result = super.unconscious(actor, map) + "\n" + this + " explodes upon its death!";

        List<Location> surroundingLocations = new ArrayList<>();
        for (Exit exit : wizardLocation.getExits()) {
            surroundingLocations.add(exit.getDestination());
        }

        int explosionDamage = 50;
        for (Location location : surroundingLocations) {
            if (location.containsAnActor()) {
                Actor nearbyActor = location.getActor();
                nearbyActor.hurt(explosionDamage);
                result += String.format("\nThe explosion hits %s for %d damage!", nearbyActor, explosionDamage);
            }
        }

        wizardLocation.addActor(new WeatherWizard(atmosphere));
        return result;
    }

    /**
     * Reacts to changes in weather.
     *
     * @param currentWeather the current weather condition.
     */
    @Override
    public void reactToWeather(Weather currentWeather) {
        // Implement weather-based behavior here (e.g., gain/lose abilities).
    }
}
