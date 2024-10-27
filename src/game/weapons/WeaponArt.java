package game.weapons;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
/**
 * An abstract class representing a weapon art
 *
 * * Created by:
 *  * @author Sebastian Aisea
 *
 **/

public abstract class WeaponArt {
    private int reqCost;

    /**
     * Constructor to initialize a WeaponArt with its name and mana cost.
     *
     * @param manaCost the mana cost of using the weapon art
     */

    public WeaponArt(int manaCost){
        this.reqCost = reqCost;
    }

    public String activate(Actor player, GameMap map){

        return String.format("%s uses %s", player);

    }
}
