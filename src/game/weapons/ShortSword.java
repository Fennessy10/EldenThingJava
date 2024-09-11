package game.weapons;



import edu.monash.fit2099.engine.weapons.Weapon;

/**
 * Class representing a short sword weapon item.
 * Short sword deals 100 damage and has 75% chance to hit
 * It requires actor to have minimum strength of 10 points to pick up.
 */
public class ShortSword extends WeaponItem implements Weapon{
    /**
     * Constructor for ShortSword class
     * Initializes short sword with predefined attributes.
     */
    public ShortSword(){
        super("ShortSword",'!', 100, "attack", 75, 10);
    }

}
