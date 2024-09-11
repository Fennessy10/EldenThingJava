package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

/**
 * A behaviour that allows an actor to follow another actor (e.g., the Tarnished).
 * This behaviour calculates the closest exit that brings the actor closer to the target.
 */
public class FollowBehaviour implements Behaviour {

    private final Actor target; // The actor to follow

    /**
     * Constructor for FollowBehaviour.
     *
     * @param target the actor to follow
     */
    public FollowBehaviour(Actor target) {
        this.target = target;
    }

    /**
     * Determines the action to tke during the actor's turn to follow the target.
     *
     * Calculates the distance between the actor and the target, then finds the
     * nearest accessible location that brings the actor closer to the target.
     * If this location exists, it returns a MoveActorAction to move the actor to this location.
     *
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return A MoveActorAction to move towards the target, or null if there is nowhere closer to move.
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        // Check if both the actor and the target are on the map
        if (!map.contains(target) || !map.contains(actor)) {
            return null;
        }

        // Get the current locations of the actor and the target
        Location actorLocation = map.locationOf(actor);
        Location targetLocation = map.locationOf(target);

        // Calculate the current Manhattan distance between the actor and the target
        int currentDistance = distance(actorLocation, targetLocation);

        // Iterate over each exit of the actor's current location
        for (Exit exit : actorLocation.getExits()) {
            Location destination = exit.getDestination();

            // Check if the actor can move to this destination
            if (destination.canActorEnter(actor)) {
                // Calculate the distance from the destination to the target
                int newDistance = distance(destination, targetLocation);

                // If moving to this destination brings the actor closer, return a MoveActorAction
                if (newDistance < currentDistance) {
                    return new MoveActorAction(destination, exit.getName(), exit.getHotKey());
                }
            }
        }

        // If no closer exits were found, return null
        return null;
    }

    /**
     * Compute the Manhattan distance between two locations.
     *
     * @param a the first location
     * @param b the second location
     * @return the number of steps between a and b, moving only in the four cardinal directions.
     */
    private int distance(Location a, Location b) {
        return Math.abs(a.x() - b.x()) + Math.abs(a.y() - b.y());
    }
}
