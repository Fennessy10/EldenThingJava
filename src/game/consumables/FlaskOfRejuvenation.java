package game.consumables;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.actions.ConsumeAction;

/**
 * A class representing an item that restores mana to an actor.
 *
 * The Flask of Rejuvenation can be consumed to restore a set number of mana points.
 * It has a limited number of charges, and each consumes one charge.
 * Once all charges are depleted, the flask can no longer be used.
 */
public class FlaskOfRejuvenation extends Item implements Consumable {
    private int CHARGES = 3;
    private int charges;
    private int HEALING_POINTS = 100;

    /***
     * Constructor.
     * Initializes the Flank of Rejuvenation with a set number of charges and a display character.
     */
    public FlaskOfRejuvenation() {
        super("Flask of Rejuvenation", 'o', true);
    }

    /**
     * Consumes the Flask of Rejuvenation, restoring mana to the actor if charges remain.
     *
     * @param player who is consuming the object
     * @return a string describing the result of the consumption
     */
    @Override
    public String consume(Actor player) {
        if (charges < CHARGES) {
            charges +=1;
            player.modifyAttribute(BaseActorAttributes.MANA, ActorAttributeOperations.INCREASE, HEALING_POINTS);
            return String.format("consumes " + this + "their mana has been restored by " + HEALING_POINTS + " points");
        } else  {
            return "Flask of Rejuvenation is empty.";
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
