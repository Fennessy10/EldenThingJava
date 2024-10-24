package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import game.enums.Ability;
import game.enums.Status;
import game.grounds.Puddle;
import edu.monash.fit2099.engine.positions.Location;

public class Frost implements DivinePower {
    @Override
    public Action divineAttack(Actor beast, GameMap map, Actor target) {
        // Get the location of the target on the map
        Location targetLocation = map.locationOf(target);

        // Get the ground at the actor's location
        Ground groundAtLocation = targetLocation.getGround();

        // Check if the ground has the 'WET' capability
        if (groundAtLocation.hasCapability(Ability.WET)) {
            for (Item item: target.getItemInventory()) {
                target.removeItemFromInventory(item);
            }
        }
        return null;
    }

    @Override
    public DivinePower getNextPower(DivinePower currentPower) {
        return new Wind();
    }
}
