package game.weapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

public class DivineBeastHead extends WeaponItem{
     private DivinePower currentPower;

    /**
     * Constructor of DivineBeastHead
     */
    public DivineBeastHead(){
        super("Divine Beast Head",'$',150,"bite",30,0);
        this.currentPower = new Wind();// set wind as every first power
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
     * @return a String describing the attack result
     */
    @Override
    public String attack(Actor attacker, Actor target, GameMap map){
        Action divineAttackAction = currentPower.divineAttack(attacker,map,target);//get action from divineattack

        String result;
        if (divineAttackAction != null){
            result = divineAttackAction.execute(attacker,map);
        }else { //if divine attack action is null means nothing happens
            result = String.format("%s uses %s on %s, but nothing happens.", attacker, currentPower.getClass().getSimpleName(), target);
        }

        currentPower = currentPower.getNextPower(currentPower);//switch to next divine power
        return result;
    }
}
