package game.weapons;



import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.PickUpAction;
import game.enums.NewActorAttributes;
import game.weaponarts.WeaponArt;

/**
 * Class representing a short sword weapon item.
 * Short sword deals 100 damage and has 75% chance to hit
 * It requires actor to have minimum strength of 10 points to pick up.
 */
public class ShortSword extends WeaponItem {
    private final static int STRENGTH_REQUIREMENT = 10;
    /**
     * Constructor for ShortSword class
     * Initializes short sword with predefined attributes.
     */
    public ShortSword(WeaponArt weaponArt){

        super("ShortSword",'!', 100, "attack", 75);
        this.setWeaponArt(weaponArt);
    }

    /**
     * Returns a pick-up actions if the actor has sufficient strength to wield a weapon
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
