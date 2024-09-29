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
     * Determine action for man_fly in each turn
     * set wandering as default state
     * when actor near it, try to attack by poison
     * keep follow the Tarnished around until either of them dies
     *
     * @param actions actions for man_fly
     * @param lastAction lastAction perform
     * @param map map for the game here
     * @param display show output
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        Location ManFlyLoaction = map.locationOf(this); //get location of man_fly
        return null;
    }

}
