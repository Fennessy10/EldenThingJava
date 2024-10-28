package game.weapons;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.PickUpAction;
import game.enums.NewActorAttributes;
import game.weaponarts.WeaponArt;

/**
 * Class representing a great knife weapon item.
 * Short sword deals 75 damage and has a 60% chance to hit
 * It requires actor to have minimum strength of 5 points to pick up.
 *
 *  * Created by:
 *  * @author Brianna Vaughan
 */
public class GreatKnife extends WeaponItem {
    private final static int STRENGTH_REQUIREMENT = 5;

    /**
     * Constructor for GreatKnife class
     * Initializes great knife with predefined attributes.
     */

    public GreatKnife(WeaponArt weaponArt){

        super("GreatKnife",'†', 75, "attack", 60);

        this.setWeaponArt(weaponArt);
    }

    /**
     * Returns a pick-up actions if the actor has sufficient strength to wield this weapon
     *
     * @param actor The actor attempting to pick up the weapon
     * @return A PickUpAction if the actor has enough strength, otherwise null
     */
    @Override
    public PickUpAction getPickUpAction(Actor actor) {
        if (portable){
            if (STRENGTH_REQUIREMENT <= actor.getAttribute(NewActorAttributes.STRENGTH)) {
                return new PickUpAction(this);
            }
        }
        return null;
    }

}
