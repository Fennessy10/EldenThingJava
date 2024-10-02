package game.items;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.*;

import java.util.ArrayList;

/**
 * A class representing a Gate, a type of Ground which allows actors to travel between different maps.
 * The Gate offers the ability to teleport an actor to one of multiple map locations, based on the available destinations.
 */
public class Gate extends Ground {
    /**
     * List of destination locations where the Gate can teleport the actor.
     */
    private ArrayList<Location> destinations;

    /**
     * List of corresponding directions for each destination, representing the direction in which
     * the actor will be traveling.
     */
    private ArrayList<String> directions;

    /**
     * Constructor for Gate.
     * Initializes the Gate with the name "Gate" and the symbol 'H'.
     *
     * @param destinations A list of locations that the Gate can teleport actors to.
     * @param directions   A list of corresponding directions for each location.
     *                     Each direction string represents the direction from the current location.
     *                     The size of this list must match the size of the destinations list.
     * @throws IllegalArgumentException if the size of the destinations list and directions list don't match.
     */
    public Gate(ArrayList<Location> destinations, ArrayList<String> directions) {
        super('H', "Gate");

        // Ensure both lists have the same number of elements
        if (destinations.size() != directions.size()) {
            throw new IllegalArgumentException("Every gate destination must have a direction!");
        }

        this.destinations = destinations;
        this.directions = directions;
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
        for (int i = 0; i < destinations.size(); i++) {
            Location targetLocation = destinations.get(i);
            String targetDirection = directions.get(i);

            actions.add(new MoveActorAction(targetLocation, targetDirection));
        }

        return actions; // Return the list of allowable actions
    }
}