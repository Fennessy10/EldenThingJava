package game.actors;


import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.AttackAction;
import game.behaviours.FollowBehaviour;
import game.behaviours.WanderBehaviour;
import edu.monash.fit2099.engine.positions.Location;
import game.enums.ManFlyPoisonEffect;

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
        this.followBehaviour = null; //set original state is not following any actor
    }

    /**
     * A method to check if any actor is nearby
     * We check the position of the 9 squares around ManFly by traversing ox and oy.
     * The traversal range is [-1, 0, 1] in both directions, which traverses the grid ManFly is currently on,
     * and up, down, left, right, and diagonal grids, which are the attack and follow ranges
     *
     * @param map map for the game
     * @param location current location
     * @return if any actor nearby, ture or not
     */
    private Map.Entry<Actor,String> getNearby(GameMap map, Location location) {
        for(int ox = -1; ox <= 1; ox++){
            for(int oy = -1; oy <= 1; oy++){ //ox and oy represent the offset of ManFly's current coordinates
                Location nearbyLocation = map.at(location.x() + ox, location.y() + oy);
                if (nearbyLocation.equals(location) && nearbyLocation.getActor() instanceof Player) {
                    //check if the actor on the position is the player
                    String attackDirection = getDirection(ox,oy);
                    return new AbstractMap.SimpleEntry<>(nearbyLocation.getActor(),attackDirection);
                    //creates and returns a SimpleEntry containing nearby players and attack directions
                }
            }
        }
        return null; //return null if no one nearby
    }


    /**
     * get attack direction by offset
     *
     * @param ox  offset fo x direction
     * @param oy  offset fo y direction
     * @return direction for attacking
     */
    private String getDirection(int ox,int oy){
        if (ox == 1) return "east";
        if (oy == 1) return "south";
        if (ox == -1) return "west";
        if (oy == -1) return "north";
        return null;
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
        Location ManFlyLoaction = map.locationOf(this); //get location of man_fly
        Map.Entry<Actor,String> nearby = getNearby(map,ManFlyLoaction);//get nearby players and direction of attack
        if (nearby != null) { //check if a player nearby
            Actor player = nearby.getKey();
            String attackDirection = nearby.getValue();
            int distance = Math.abs(ManFlyLoaction.x() - map.locationOf(player).x() + Math.abs(ManFlyLoaction.y() - map.locationOf(player).y()));
            //calculate Manhattan Distance to check around 9 location
            this.followBehaviour = new FollowBehaviour(player);

                if (distance == 1) { //if in attack realm, attack
                    return ManFlyAttack(player, attackDirection);
                }
                return followBehaviour.getAction(this, map); //if moving away, following
        }
            else {
                return wanderBehaviour.getAction(this, map); // if no player around, keep wandering
            }

    }

    /**
     * attack action method only for man_fly
     * 25% chance to hit player, if hit on target, have 30% chance to cause poisoneffect
     *
     * @param player as attacking target
     * @param attackDirection direction to attack
     * @return action of man_fly attacking
     */
    private Action ManFlyAttack(Actor player, String attackDirection) {
        if(random.nextInt(100)<25){
            if(random.nextInt(100)<30){
                player.addStatusEffect(new ManFlyPoisonEffect()); //if poison on add effect on player
            }
            return new AttackAction(player,attackDirection); //keep attacking if not poison on
        }
        return null; //if not hit on
    }

}
