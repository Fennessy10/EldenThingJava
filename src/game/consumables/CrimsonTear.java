package game.consumables;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.ConsumeAction;
import game.effects.HealingStatusEffect;

public class CrimsonTear extends Item implements Consumable{

    /***
     * Constructor
     */
    public CrimsonTear() {

        super("Crimson Tear", '*', true);
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
