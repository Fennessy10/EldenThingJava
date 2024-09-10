package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.enums.Status;
import game.behaviours.StompBehaviour;
import game.behaviours.WanderBehaviour;
import game.actions.AttackAction;
import game.behaviours.FollowBehaviour;

import java.util.Map;
import java.util.TreeMap;

/**
 * Class representing the Furnace Golem
 * It can wander, follow, and stomp its enemies.
 */
public class FurnaceGolem extends Actor {
    private Map<Integer, Behaviour> behaviours = new TreeMap<>();
    private int golemWanderPriority = 999;
    private int golemFollowPriority = 500;
    private int golemStompPriority = 300;

    /**
     *
     */
    public FurnaceGolem() {
        super("Furnace Golem", 'A', 1000); // Name, display character, and health points
        this.behaviours.put(golemWanderPriority, new WanderBehaviour());
    }

    /**
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        // Loop through the Golem's behaviours and execute the first valid action
        for (Behaviour behaviour : behaviours.values()) {
            Action action = behaviour.getAction(this, map);
            if (action != null)
                return action;
        }
        return new DoNothingAction(); // If no behaviour is triggered, do nothing


    }

    /**
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            // Add the AttackAction for the other actor to attack the Golem
            actions.add(new AttackAction(this, direction));


            this.behaviours.put(golemStompPriority, new StompBehaviour(otherActor));
            this.behaviours.put(golemFollowPriority, new FollowBehaviour(otherActor));


        }


        return actions;
    }
}
