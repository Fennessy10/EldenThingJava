package game.weapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import java.util.ArrayList;
import java.util.List;

import java.util.Random;
/**
 * Class representing the quickstep weapon art
 * Extends the WeaponArt class and allows the player to move to a random location attacking another actor
 * when using a weapon with the quickstep weapon art.
 * Mana cost to use the quickste    p weapon art is 0.
 *
 *  * Created by:
 *  * @author Sebastian Aisea
 */
public class Quickstep extends WeaponArt {

    private final static int DEFAULT_MANA_COST = 0;



    /**
     * Constructor to initialize Quickstep with its name and mana cost.
     */
    public Quickstep() {
        super("Quickstep",DEFAULT_MANA_COST);
    }


    /**
     * Executes the Quickstep ability by moving the player to a random valid adjacent location on the map.
     * If no valid exits are available, the player stays in the same position.
     *
     * @param player the player performing the Quickstep
     * @param map the map on which the player is located
     * @return a String describing the action taken
     */

    @Override
    public String execute(Actor player, GameMap map) {
        Random random = new Random();
        Location actorLocation = map.locationOf(player);

        List<Exit> validExits = new ArrayList<>();
        for (Exit exit : actorLocation.getExits()) {
            Location destination = exit.getDestination();

            if (destination.canActorEnter(player)) {
                validExits.add(exit);
            }
        }

        if (!validExits.isEmpty()) {
            Exit randomExit = validExits.get(random.nextInt(validExits.size()));
            Location destination = randomExit.getDestination();

            Action moveAction = new MoveActorAction(destination, randomExit.getName());
            moveAction.execute(player, map);
        }
        return String.format("%s uses %s", player,name);
    }
}
