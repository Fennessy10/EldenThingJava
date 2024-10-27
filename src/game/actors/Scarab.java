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
import game.actions.ConsumeAction;
import game.behaviours.WanderBehaviour;
import game.consumables.Consumable;
import game.consumables.CrimsonTear;
import game.enums.Ability;
import game.enums.Status;
import game.effects.HealingStatusEffect;
import game.effects.ManaStatusEffect;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * A Scarab that wanders around and can be consumed.
 * It contains many treasures and explodes upon death.
 */
public class Scarab extends Actor implements Consumable {
    private final Map<Integer, Behaviour> scarabBehaviours = new TreeMap<>();
    private final static int scarabHP = 25;

    /**
     * Constructor for the Scarab class.
     * Initializes with specific HP and default behaviours.
     */
    public Scarab() {
        super("Scarab", 'b', scarabHP);
        this.addCapability(Status.ENEMY);
        this.scarabBehaviours.put(999, new WanderBehaviour());
        this.addCapability(Ability.POISON_RESISTANT);
    }

    /**
     * Select and return an action to perform on the current turn.
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the Action to be performed
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        for (Behaviour behaviour : scarabBehaviours.values()) {
            Action action = behaviour.getAction(this, map);
            if (action != null) return action;
        }
        return new DoNothingAction();
    }

    /**
     * Determines what actions other actors can perform on the Scarab.
     *
     * Also handles follow behaviour by checking if a followable actor
     * enters its surroundings and adds the behaviour if needed.
     *
     * @param otherActor the Actor that might perform an attack
     * @param direction  direction of the other Actor
     * @param map        current GameMap
     * @return An ActionList of available actions for other actors
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            actions.add(new AttackAction(this, direction));
        }
        actions.add(new ConsumeAction(this));
        return actions;
    }

    /**
     * Defines the effect of consuming the Scarab.
     *
     * @param actor the consumer
     * @param map   the current game map
     * @return a message indicating the actor feels stronger
     */
    @Override
    public String consume(Actor actor, GameMap map) {
        actor.addStatusEffect(new HealingStatusEffect(actor, 10, 30, false));
        actor.addStatusEffect(new ManaStatusEffect(actor, 10, 50));
        map.removeActor(this);
        return String.format("Scarab consumed by %s. %s feels stronger.", actor, actor);
    }

    /**
     * Handles what happens when the Scarab becomes unconscious.
     * It explodes and deals damage to nearby actors.
     *
     * @param actor the actor that caused the unconscious state
     * @param map   the game map
     * @return a message detailing the explosion and its effects
     */
    @Override
    public String unconscious(Actor actor, GameMap map) {
        Location scarabLocation = map.locationOf(this);
        String result = super.unconscious(actor, map) + "\n" + this + " explodes upon its death!";

        List<Location> surroundingLocations = new ArrayList<>();
        for (Exit exit : scarabLocation.getExits()) {
            surroundingLocations.add(exit.getDestination());
        }

        int explosionDamage = 25;
        for (Location surroundingLocation : surroundingLocations) {
            if (surroundingLocation.containsAnActor()) {
                Actor nearbyActor = surroundingLocation.getActor();
                nearbyActor.hurt(explosionDamage);
                result += String.format("\nThe explosion hits %s for %d damage!", nearbyActor, explosionDamage);
            }
        }

        CrimsonTear crimsonTear = new CrimsonTear();
        scarabLocation.addItem(crimsonTear);
        return result;
    }
}
