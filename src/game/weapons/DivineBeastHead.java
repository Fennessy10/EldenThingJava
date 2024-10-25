package game.weapons;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.DivinePower;
import game.Wind;

public class DivineBeastHead extends WeaponItem{
     private DivinePower currentPower;

    /**
     * Constructor of DivineBeastHead
     */
    public DivineBeastHead(){
        super("Divine Beast Head",'$',150,"bite",30,0);
        this.currentPower = new Wind();
    }

    /**
     * Get current Divine Power.
     *
     * @return the current Divine Power
     */
    public DivinePower getCurrentPower() {
        return currentPower;
    }


    /**
     *Perform  DivineBeastHead attack
     *
     * @param attacker the actor who performed the attack
     * @param target   the actor who is the target of the attack
     * @param map      the map on which the attack was executed
     * @return
     */
    @Override
    public String attack(Actor attacker, Actor target, GameMap map){
        return null;
    }
}
