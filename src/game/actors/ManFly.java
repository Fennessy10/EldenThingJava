package game.actors;


import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.behaviours.FollowBehaviour;
import game.behaviours.WanderBehaviour;
import edu.monash.fit2099.engine.positions.Location;

import java.util.Random;

/**
 * New actor class for man_fly which wandering in the sewers
 * man_fly can attack nearby actors by poison and following it
 */
public class ManFly extends Actor {
    private final Random random = new Random();
    private WanderBehaviour wanderBehaviour;
    private FollowBehaviour followBehaviour;

    /**
     * constructor for man_fly
     * initialize it behaviour, char and hit point(50)
     */
    public ManFly() {
        super("ManFly",'%',50);
        this.wanderBehaviour = new WanderBehaviour(); //use wanderbehaviour we creat before
        this.followBehaviour = null; //set original state is not following any actor
    }

    /**
     * A new method to check if any actor is nearby
     * We check the position of the 9 squares around ManFly by traversing ox and oy.
     * The traversal range is [-1, 0, 1] in both directions, which traverses the grid ManFly is currently on,
     * and up, down, left, right, and diagonal grids, which are the attack and follow ranges
     * @param map map for the game
     * @param location current location
     * @return if any actor nearby, ture or not
     */
    private Actor getNearby(GameMap map, Location location) {
        for(int ox = -1; ox <= 1; ox++){
            for(int oy = -1; oy <= 1; oy++){ //ox and oy represent the offset of ManFly's current coordinates
                Location nearbyLocation = map.at(location.x() + ox, location.y() + oy);
                if (nearbyLocation.equals(location) && nearbyLocation.getActor() instanceof Player) {
                    //check if the actor on the position is the player
                    return nearbyLocation.getActor();
                }
            }
        }
        return null; //return null if no one nearby
    }


    /**
     * Determine action for man_fly in each turn
     * set wandering as default state
     * when actor near it, try to attack by poison
     * keep follow the Tarnished around until either of them dies
     *
     * @param actions actions for man_fly
     * @param lastAction lastAction perform
     * @param map map for the game
     * @param display show output
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        Location ManFlyLoaction = map.locationOf(this); //get location of man_fly

        return null;
    }

}
