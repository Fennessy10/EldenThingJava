package game.consumables;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.items.Item;
import game.enums.NewActorAttributes;

public class CrimsonTear extends Item implements Consumable{

    /***
     * Constructor.
     *  @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     * @param portable true if and only if the Item can be picked up
     */
    public CrimsonTear(String name, char displayChar, boolean portable) {
        super("Crimson Tear", '*', portable);
    }

    /**
     * Consumes the Crimson Tear, blessing the actor by increasing their attributes.
     *
     * @param actor who is consuming the object
     * @return a string describing the result of the consumption.
     */
    @Override
    public String consume(Actor actor) {
        int HP_INCREASE = 30;
        actor.modifyAttribute(BaseActorAttributes.HEALTH, ActorAttributeOperations.INCREASE, HP_INCREASE);
        actor.removeItemFromInventory(this);
        return String.format("Crimson Tear consumed by " + actor + "." + actor + " feels stronger.");
    }
}
