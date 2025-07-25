package game.weaponarts;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * Class representing the life steal weapon art
 * Extends the WeaponArt class and allows the player to move to regenerate health after attacking another actor
 * when using a weapon with the life steal weapon art.
 * Mana cost to use the life steal weapon art is 10.
 * Heals 20 points when used
 *
 *  * Created by:
 *  * @author Sebastian Aisea
 */
public class LifeSteal implements WeaponArt{

    private final static int MANA_COST = 10;
    private final static int HEALING_POINTS = 20;

    /**
     * Executes the Life Steal effect, healing the player if they have enough mana.
     * If the player does not have sufficient mana, the life steal effect does not activate.
     *
     * @param player The player using this weapon art.
     * @param map The game map where the action is taking place.
     * @return A string indicating the result of using Life Steal.
     */
    @Override
    public String activate(Actor player, GameMap map){
        if (player.getAttribute(BaseActorAttributes.MANA) >= MANA_COST){
            player.heal(HEALING_POINTS);
            player.modifyAttribute(BaseActorAttributes.MANA, ActorAttributeOperations.DECREASE, MANA_COST);
            return String.format("%s uses Life Steal", player);
        }
        return null;
    }
}
