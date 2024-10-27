package game.weapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import game.enums.Status;
import edu.monash.fit2099.engine.positions.Location;

/**
 * The Frost class represents a type of divine power that applies frost-related effects to an actor.
 * This power can freeze the target's environment, causing them to lose all items in their inventory if they are standing on a
 * wet surface when the frost strikes.
 */
public class Frost implements DivinePower {
    private boolean beatenByTheFrost;

    /**
     * Executes the frost attack on a target, potentially causing the target to lose all of its inventory
     * if they are standing on a wet surface.
     *
     * @param beast The beast performing the attack
     * @param map The current GameMap
     * @param target The target of the attack
     * @return null
     */
    @Override
    public Action divineAttack(Actor beast, GameMap map, Actor target) {
        // Get the location of the target on the map
        Location targetLocation = map.locationOf(target);

        // Get the ground at the actor's location
        Ground groundAtLocation = targetLocation.getGround();

        // Check if the ground has the 'WET' capability
        if (groundAtLocation.hasCapability(Status.WET)) {
            for (Item item: target.getItemInventory()) {
                target.removeItemFromInventory(item);
            }
        } else {
            beatenByTheFrost = false;
        }
        return null;
    }

    /**
     * Retrieves the next divine power that the divine beast will switch to, which
     * will always be wind
     *
     * @return The next DivinePower in the sequence, which is an instance of Wind.
     */
    @Override
    public DivinePower getNextPower() {
        return new Wind();
    }

    /**
     * Provides a description of the frost attack, indicating the chilling and disruptive
     * effects of the frost on the surroundings and target.
     *
     * @return A string description of the frost attack, with additional detail if the target is standing
     * on a wet surface and loses all of their inventory.
     */
    @Override
    public String attackDescription() {
        if (beatenByTheFrost) {
            return "The cold seizes the water beneath and the ground betrays your footing.";
        }
        return null;
    }

    /**
     * Provides an initialisation message when the Frost Power is activated
     *
     * @return A string describing the awakening of the Frost Power
     */
    @Override
    public String initialisePower() {
        return "The air turns bitter, and the land shudders beneath an ancient cold. Frost grips all in its chilling embrace.";
    }
}
