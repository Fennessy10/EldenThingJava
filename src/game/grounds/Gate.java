package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.*;
import game.actions.TeleportAction;
import game.actions.TeleportAction;

import java.util.ArrayList;

/**
 * A class representing a Gate, a type of Ground which allows actors to travel between different maps.
 * The Gate offers the ability to teleport an actor to one of multiple map locations, based on the available destinations.
 */
public class Gate extends Ground {
    /**
     * List of destination locations where the Gate can teleport the actor.
     */
    private ArrayList<Location> destinations = new ArrayList<>();


    /**
     * Constructor for Gate.
     * Initializes the Gate with the name "Gate" and the symbol 'H'
     */
    public Gate() {
        super('H', "Gate");
    }

    public void addDestination(Location location) {
        this.destinations.add(location);
    }

    /**
     * Returns a list of allowable actions that the actor can perform at the current location.
     * This includes moving the actor to one of the specified destination locations via the Gate.
     * Actors on and in the immediate surroundings of the Gate are recognized
     *
     * @param actor     The actor interacting with the Gate.
     * @param location  The current location of the Gate.
     * @param direction The direction from the actor's current position to the Gate.
     * @return An ActionList containing MoveActorAction for each destination location and corresponding direction.
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = new ActionList(); // Initialize the list of actions

        // Iterate over both lists and add a MoveActorAction for each destination and corresponding direction
        for (Location destination : destinations) {
            GameMap destinationMap = destination.map();
            actions.add(new TeleportAction(destination, destinationMap));
        }

        return actions; // Return the list of allowable actions
    }
}