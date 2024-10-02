package game.consumables;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.ConsumeAction;
import game.actors.BurningStatusEffect;
import game.actors.HealingStatusEffect;
import game.enums.NewActorAttributes;

public class CrimsonTear extends Item implements Consumable{

    /***
     * Constructor.
     * @param name the name of this Item
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
    public String consume(Actor actor, GameMap map) {
        actor.addStatusEffect(new HealingStatusEffect(actor,5,30, true));
        actor.removeItemFromInventory(this);
        return "Crimson Tear consumed";
    }

    /**
     * Returns the allowable actions that can be performed on the Crimson Tear
     * This method adds a {@link ConsumeAction} to the list of actions.
     *
     * @param owner the actor that owns the item
     * @return an ActionList containing the allowable actions.
     */
    @Override
    public ActionList allowableActions(Actor owner) {
        ActionList actions = new ActionList();

        actions.add(new ConsumeAction(this));
        return actions;
    }
}
