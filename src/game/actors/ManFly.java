package game.actors;


import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.AttackAction;
import game.behaviours.FollowBehaviour;
import game.behaviours.WanderBehaviour;
import edu.monash.fit2099.engine.positions.Location;
import game.enums.ManFlyPoisonEffect;
import game.enums.Status;

import java.util.AbstractMap;
import java.util.Map;
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
        this.addCapability(Status.ENEMY);
    }


    /**
     * Determine allowable action towards Man_fly
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction attack direction
     * @param map game map
     * @return a list for allowable action
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)){
            actions.add(new AttackAction(this,direction));
        }
        return actions;
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
     * @return man_fly's action in turns
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        for (Exit exit : map.locationOf(this).getExits()) {
            //caheck if here exit a actor
            if (exit.getDestination().containsAnActor()) {
                Actor nearbyActor = exit.getDestination().getActor(); // get the first actor which the exit
                //check if this actor is hostile
                if (nearbyActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
                    //check if in or out attack range around
                    if (distance(map.locationOf(this), exit.getDestination()) == 1) {
                        Action attackAction = ManFlyAttack(nearbyActor, exit.getDestination().toString());
                        if(attackAction != null) {
                            return attackAction;
                        }
                    } else {
                        //if not in attack range, follow
                        if (this.followBehaviour == null) {
                            this.followBehaviour = new FollowBehaviour(nearbyActor);
                        }return followBehaviour.getAction(this, map);
                    }
                }
            }
        }
        return wanderBehaviour.getAction(this, map);
    }

    /**
     * calculate distance method from followbehaviour class
     * @param a location a
     * @param b location b
     * @return the distance between
     */
    private int distance(Location a, Location b) {
        return Math.abs(a.x() - b.x()) + Math.abs(a.y() - b.y());
    }


    /**
     * attack action method only for man_fly
     * 25% chance to hit player, if hit on target, have 30% chance to cause poisoneffect
     *
     * @param player as attacking target
     * @param direction direction to attack
     * @return action of man_fly attacking
     */
    public Action ManFlyAttack(Actor player, String direction) {
        if(random.nextInt(100)<25){
            if(random.nextInt(100)<30){
                player.addStatusEffect(new ManFlyPoisonEffect()); //if poison on add effect on player
            }
            return new AttackAction(player,direction); //keep attacking if not poison on
        }
        return null; //if attack miss
    }
}
