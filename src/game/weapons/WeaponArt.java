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
    protected String name;
    protected int manaCost;

    /**
     * Constructor to initialize a WeaponArt with its name and mana cost.
     *
     * @param name the name of the weapon art
     * @param manaCost the mana cost of using the weapon art
     */

    public WeaponArt(String name, int manaCost){
        this.name = name;
        this.manaCost = manaCost;
    }

    public String execute(Actor player, GameMap map){

        return String.format("%s uses %s", player,name);

    }
}
