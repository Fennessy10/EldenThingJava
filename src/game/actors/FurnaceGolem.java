package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.enums.Ability;
import game.enums.Status;
import game.behaviours.WanderBehaviour;
import game.actions.AttackAction;
import game.behaviours.FollowBehaviour;
import game.weapons.StompingFoot;

import java.util.Map;
import java.util.TreeMap;

/**
 * Class representing the Furnace Golem
 * It can wander, follow, and stomp its enemies.
 */
public class FurnaceGolem extends Enemy {

    /**
     * Constructor
     */
    public FurnaceGolem() {
        super("Furnace Golem", 'A', 1000);
        this.setIntrinsicWeapon(new StompingFoot());
    }

    /**
     * Determines what actions other actors can perform on the Furnance Golem
     *
     * Also handles implementation of follow behaviour by checking if a followable actor enters its
     * surroundings, and if so, adds follow behaviour to actor's hashmap of behaviours.
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return An ActionList of available actions an actor can perform on the Furnace Golem
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();

        if (otherActor.hasCapability(Status.FOLLOW)) {
            super.behavioursPut(500, new FollowBehaviour(otherActor));
        }
        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            actions.add(new AttackAction(this, direction));
        }
        return actions;
    }
}
