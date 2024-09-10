package game.weapons;

/**
 * Class representing The Short Sword Weapon
 */

import edu.monash.fit2099.engine.weapons.Weapon;

public class GreatKnife extends WeaponItem implements Weapon{
    public GreatKnife(){
        super("GreatKnife",'†', 75, "attack", 60, 5);
    }
}
