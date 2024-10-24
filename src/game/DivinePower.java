package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

public interface DivinePower {

    /**
     * Executes the divine attack for the current divine power
     *
     * @param beast The beast performing the attack
     * @param map The current GameMap
     * @param target The target of the attack
     * @return The Action that corresponds to the special attack, or null if no attack occurs
     */
    public Action divineAttack(Actor beast, GameMap map, Actor target);

    /**
     * Determines the next divine power based on the current one
     *
     * @param currentPower The current Divine Power instance
     * @return The next Divine Power instance
     */
    public DivinePower getNextPower (DivinePower currentPower);
}
