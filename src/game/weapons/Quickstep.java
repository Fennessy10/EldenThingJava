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

public class Quickstep extends WeaponArt {

    private final static int MANA_COST = 0;

    public Quickstep() {
        super("Quickstep",MANA_COST);
    }

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
