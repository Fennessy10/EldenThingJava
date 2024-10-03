package game.weapons;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

public abstract class WeaponArt {
    protected String name;
    protected int manaCost;

    public WeaponArt(String name, int manaCost){
        this.name = name;
        this.manaCost = manaCost;
    }

    public String execute(Actor player, GameMap map){

        return String.format("%s uses %s", player,name);

    }
}
