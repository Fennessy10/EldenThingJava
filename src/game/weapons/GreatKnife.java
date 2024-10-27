package game.weapons;

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
    /**
     * Constructor for GreatKnife class
     * Initializes great knife with predefined attributes.
     */

    public GreatKnife(WeaponArt weaponArt){

        super("GreatKnife",'†', 75, "attack", 60, 5);

        this.setWeaponArt(weaponArt);
    }

}
