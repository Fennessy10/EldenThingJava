package game.weapons;

/**
 * Class representing The Short Sword Weapon
 */

import edu.monash.fit2099.engine.weapons.Weapon;

public class ShortSword extends WeaponItem implements Weapon{
    public ShortSword(){
        super("ShortSword",'!', 100, "attack", 75, 10);
    }

}
