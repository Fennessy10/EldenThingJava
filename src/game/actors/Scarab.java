package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.AttackAction;
import game.actions.ConsumeAction;
import game.behaviours.WanderBehaviour;
import game.consumables.Consumable;
import game.consumables.CrimsonTear;
import game.enums.Status;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Scarab extends Actor implements Consumable {
    private final Map<Integer, Behaviour> scarabBehaviours = new HashMap<>();

    /**
     * The constructor of the scarab class
     * it wanders around and can be consumed
     */
    public Scarab() {
        super("Scarab", 'b', 25);
        this.addCapability(Status.ENEMY);
        this.scarabBehaviours.put(999, new WanderBehaviour());
    }

    /**
     * Select and return an action to perform on the current turn.
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the Action to be performed
     */
    /**
     * Select and return an action to perform on the current turn.
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the Action to be performed
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        for (Behaviour behaviour : scarabBehaviours.values()) {
            Action action = behaviour.getAction(this, map);
            if (action != null)
                return action;
        }
        return new DoNothingAction();
    }

    /**
     * Determines what actions other actors can perform on the Scarab
     *
     * Also handles implementation of follow behaviour by checking if a followable actor enters its
     * surroundings, and if so, adds follow behaviour to actor's hashmap of behaviours.
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return An ActionList of available actions an actor can perform on the Scarab
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
     * @param actor
     * @return
     */
    @Override
    public String consume(Actor actor, GameMap map) {
        actor.addStatusEffect(new HealingStatusEffect(actor,10,30, false));
        actor.addStatusEffect(new ManaStatusEffect(actor, 10, 50, false));
        map.removeActor(this);
        return String.format("Scarab consumed by " + actor + "." + actor + " feels stronger.");
    }

    @Override
    public String unconscious(Actor actor, GameMap map) {
        // Retrieve the Scarab's current location before removing it from the map
        Location scarabLocation = map.locationOf(this);

        // Proceed with removing the Scarab from the map
        String result = super.unconscious(actor, map) + "\n" + this + " explodes upon its death!";


        // Get the surrounding locations
        List<Location> surroundingLocations = new ArrayList<>();
        for (Exit exit : scarabLocation.getExits()) {
            surroundingLocations.add(exit.getDestination());
        }

        // Apply explosion damage to actors in the surroundings
        int explosionDamage = 25;
        for (Location surroundingLocation : surroundingLocations) {
            if (surroundingLocation.containsAnActor()) {
                Actor nearbyActor = surroundingLocation.getActor();
                nearbyActor.hurt(explosionDamage);
                result += String.format("\nThe explosion hits %s for %d damage!", nearbyActor, explosionDamage);
            }
        }

        return result;
    }


}
