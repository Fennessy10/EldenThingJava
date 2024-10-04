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
import game.enums.Ability;
import game.enums.ManFlyPoisonEffect;
import game.enums.Status;
import game.weapons.ManFlyStinger;
import game.weapons.StompingFoot;

import java.util.AbstractMap;
import java.util.Map;
import java.util.Random;

/**
 * New actor class for man_fly which wandering in the sewers
 * man_fly can attack nearby actors by poison and following it
 */
public class ManFly extends Enemy {
    private static final int manFlyHP = 50;
    private static final char displayChar = '%';
    private static final String name = "ManFly";

    /**
     * constructor for man_fly
     * initialize it behaviour, char and hit point(50)
     */
    public ManFly() {
        super(name, displayChar, manFlyHP);
        this.addCapability(Status.ENEMY);
        this.addCapability(Ability.POISON_RESISTANT);
        this.setIntrinsicWeapon(new ManFlyStinger());
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
        ActionList actions = new ActionList(); //create a list of actions

        if (otherActor.hasCapability(Status.FOLLOW)) {
            super.behavioursPut(500, new FollowBehaviour(otherActor));
        }
        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            actions.add(new AttackAction(this, direction));
        }
        return actions;
    }

}
