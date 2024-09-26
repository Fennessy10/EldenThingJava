package game.consumables;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.items.Item;
import game.actions.ConsumeAction;

/**
 * A class representing a consumable item that can heal an actor.
 *
 * The flask of healing can be consumed by an actor to restore a set number of hit points.
 * It has a limited number of charges, and each consume uses one charge.
 * Once all charges are depleted, the flask can no longer be used.
 */
public class FlaskOfHealing extends Item implements Consumable {
    private int CHARGES = 10;
    private int charges;
    private int HEALING_POINTS = 150;

    /**
     * Constructor.
     * Initialises the Flask of healing with a set number of charges and a display character.
     */
    public FlaskOfHealing() {
        super("Flask of Healing", 'u', true);
    }

    /**
     * Consumes the Flask of healing, healing the actor if charges remain.
     *
     * @param player who is consuming the object
     * @return a string describing the result of consumption
     */
    @Override
    public String consume(Actor player) {
        if (charges < CHARGES) {
            charges ++;
            player.modifyAttribute(BaseActorAttributes.HEALTH, ActorAttributeOperations.INCREASE, HEALING_POINTS);
            return String.format(player + "consumes " + this + " have been healed by " + HEALING_POINTS + " points");
        } else  {
            return "Flask of Healing is empty.";
        }
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
