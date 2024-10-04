package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

import java.util.Map;

/**
 * Represents an action to allow an actor to transport from one location to another, namely from one map to another
 * This action moves an actor from their current location on the map to a new location on a specified map
 */
public class TeleportAction extends Action {
    // The destination location where the actor will be teleported
    private Location teleportToLocation;
    // The destination map where the actor will be teleported
    private GameMap teleportToMap;

    /**
     * Constructor for TeleportAction
     *
     * @param teleportToLocation The location on the destination map where the actor will teleport to
     * @param teleportToMap The map to which the actor will teleport.
     */
    public TeleportAction(Location teleportToLocation, GameMap teleportToMap) {
        this.teleportToLocation = teleportToLocation;
        this.teleportToMap = teleportToMap;
    }

    /**
     * Executes the teleport action, moving the actor from the current map to the specified location on anohter map
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return A description of the action for display purposes
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        map.moveActor(actor, teleportToLocation);
        return menuDescription(actor);
    }

    /**
     * Provides a description of the teleport action for menu display, indicating where the actor will travel
     * @param actor The actor performing the action.
     * @return A string describing the actor's teleport destination
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " travels to " + teleportToMap.toString();
    }

}
