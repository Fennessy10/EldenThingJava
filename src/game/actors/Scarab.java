package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.ActorLocationsIterator;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.AttackAction;
import game.behaviours.FollowBehaviour;
import game.consumables.Consumable;
import game.enums.NewActorAttributes;
import game.enums.Status;

import java.util.Map;

public class Scarab extends Enemy implements Consumable {

    /**
     * The constructor of the Actor class.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     */
    public Scarab(String name, char displayChar, int hitPoints) {
        super("Scarab", 'b', 25);
    }

    /**
     * Determines what actions other actors can perform on the Scarab
     *
     * Also handles implementation of follow behaviour by checking if a followable actor enters its
     * surroundings, and if so, adds follow behaviour to actor's hashmap of behaviours.
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return An ActionList of available actions an actor can perform on the Scarab
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();

        if (otherActor.hasCapability(Status.FOLLOW)) {
            super.behavioursPut(500, new FollowBehaviour(otherActor));
        }
        return actions;
    }

    /**
     * @param actor
     * @return
     */
    @Override
    public String consume(Actor actor, GameMap map) {
        int HPIncrease = 30;
        actor.modifyAttributeMaximum(BaseActorAttributes.HEALTH, ActorAttributeOperations.INCREASE, HPIncrease);
        int manaIncrease = 50;
        actor.modifyAttributeMaximum(BaseActorAttributes.MANA, ActorAttributeOperations.INCREASE, manaIncrease);
        map.removeActor(this);
        return String.format("Scarab consumed by " + actor + "." + actor + " feels stronger.");
    }
}
