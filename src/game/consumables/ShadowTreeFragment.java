package game.consumables;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.enums.NewActorAttributes;
import game.actions.ConsumeAction;

/**
 * A class representing a consumable ShadowTree Fragment item that blesses the actor by increasing their maximum attributes.
 *
 * The ShadowTree Fragment can be consumed by an actor to permanently increase their maximum hit points (HP),
 * mana, and strength. Once consumed, the fragment disappears from the actor's inventory.
 */
public class ShadowTreeFragment extends Item implements Consumable {

    /**
     * Constructor.
     * Initializes the ShadowTreeFragment with a name and display character.
     */
    public ShadowTreeFragment() {
        super("ShadowTree Fragment", 's', true);
    }

    /**
     * Consumes the ShadowTree Fragment, blessing the actor by increasing their maximum attributes.
     *
     * @param actor who is consuming the object
     * @return a string describing the result of the consumption.
     */
    @Override
    public String consume(Actor actor, GameMap map) {
        int HPIncrease = 50;
        actor.modifyAttributeMaximum(BaseActorAttributes.HEALTH, ActorAttributeOperations.INCREASE, HPIncrease);
        int manaIncrease = 25;
        actor.modifyAttributeMaximum(BaseActorAttributes.MANA, ActorAttributeOperations.INCREASE, manaIncrease);
        int strengthIncrease = 5;
        actor.modifyAttributeMaximum(NewActorAttributes.STRENGTH, ActorAttributeOperations.INCREASE, strengthIncrease);
        actor.removeItemFromInventory(this);
        return String.format("Shadowtree Fragment consumed by " + actor + "." + actor + " feels stronger.");
    }

    /**
     * Returns the allowable actions that can be performed on the Flask of Rejuvenation
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
