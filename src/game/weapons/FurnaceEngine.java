package game.weapons;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

public class FurnaceEngine extends WeaponItem{
    private StompingFoot stompingFoot;

    /**
     * Constructor for the FurnaceEngine
     * Initializes the engine
     */
    public FurnaceEngine() {
        super("Furnace Engine", 'E', 100, "stomps on", 5, 0);
        this.stompingFoot = new StompingFoot();
    }

    /**
     * Executes the Furnace Engine.
     * @param attacker The Furnace Golem
     * @param target   The actor being attacked.
     * @param map      current map
     * @return A string describing the result of the attack
     */
    @Override
    public String attack(Actor attacker, Actor target, GameMap map) {
        return stompingFoot.attack(attacker, target, map);
    }
}
