package game.consumables;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.items.Item;

/**
 *
 */
public class FlaskOfHealing extends Item implements ConsumableItem {
    private int charges;
    private int maxCharges = 5;

    /**
     *
     */
    public FlaskOfHealing() {
        super("Flask of Healing", 'u', true);
    }

    /**
     *
     * @param player
     * @return
     */
    @Override
    public String consume(Actor player) {
        if (charges < maxCharges) {
            charges +=1;
            player.modifyAttribute(BaseActorAttributes.HEALTH, ActorAttributeOperations.INCREASE, 150);
            return String.format("Health increased 150 for " + player);
        } else  {
            return "Flask of Healing is empty.";
        }
    }



}
