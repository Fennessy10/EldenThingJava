package game.consumables;

import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;

/**
 *
 */
public class FlaskOfRejuvenation extends Item implements Consumable {
    private int maxCharges = 3;
    private int charges;

    /**
     *
     */
    public FlaskOfRejuvenation() {
        super("Flask of Rejuvenation", 'o', true);
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
            player.modifyAttribute(BaseActorAttributes.MANA, ActorAttributeOperations.INCREASE, 100);
            return String.format("Mana increased 100 for " + player);
        } else  {
            return "Flask of Rejuvenation is empty.";
        }
    }

}
