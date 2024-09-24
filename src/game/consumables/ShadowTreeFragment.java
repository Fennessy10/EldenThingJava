package game.consumables;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.items.Item;
import game.enums.NewActorAttributes;
import game.actions.ConsumeAction;

/**
 *
 */
public class ShadowTreeFragment extends Item implements Consumable {
    public ShadowTreeFragment(){
            super("Shadow Tree Fragment", ',', true);
    }

    /**
     *
     * @param actor
     * @return
     */
    @Override
    public String consume(Actor actor) {
        actor.modifyAttributeMaximum(BaseActorAttributes.HEALTH, ActorAttributeOperations.INCREASE, 50);
        actor.modifyAttributeMaximum(BaseActorAttributes.MANA, ActorAttributeOperations.INCREASE, 25);
        actor.modifyAttributeMaximum(NewActorAttributes.STRENGTH, ActorAttributeOperations.INCREASE, 5);
        actor.removeItemFromInventory(this);
        return String.format("Shadowtree Fragment consumed by " + actor + "." + actor + " feels stronger.");
    }

    /**
     *
     * @param actor the actor that owns the item
     * @return
     */
    @Override
    public ActionList allowableActions(Actor actor) {
        ActionList actions = super.allowableActions(actor);
        actions.add(new ConsumeAction(this));
        return actions;
    }
}
