package game.weapons;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

public class LifeSteal extends WeaponArt{

    private final static int MANA_COST = 10;
    private final static int HEALING_POINTS = 20;

    public LifeSteal() {
        super("Life Steal", MANA_COST);
    }

    @Override
    public String execute(Actor player, GameMap map){
        player.heal(HEALING_POINTS);
        return String.format("%s uses %s", player,name);

    }
}
