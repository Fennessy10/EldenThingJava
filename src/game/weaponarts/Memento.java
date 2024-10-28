package game.weaponarts;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.GameMap;
import game.enums.NewActorAttributes;

import java.util.ArrayList;
import java.util.List;


/**
 * Class that represents Memento weapon art.
 * Extends the WeaponArt class and allows an actor to save or restore its state.
 * The actor's health, mana, and strength are stored and can be restored at a later time.
 * Memento costs 5 health points every time it is used.
 *
 * Created by:
 * @author Sebastian Aisea
 */

public class Memento implements WeaponArt {

    /** The health cost required to use Memento. */
    private final static int HEALTH_COST = 5;

    /** A list of saved states representing the actor's attributes at different points. */
    private final List<ActorState> savedStates = new ArrayList<>();



    /**
     * Activates the Memento ability. If the actor has enough health it spends health points
     * to either restore the most recently saved state or save the current state with a 50% chance for each.
     *
     * @param player The actor performing the Memento action.
     * @param map The game map where the actor is located.
     * @return A string describing the result of the Memento action.
     */
    @Override
    public String activate(Actor player, GameMap map) {
        if (player.getAttribute(BaseActorAttributes.HEALTH) <= HEALTH_COST) {
            return String.format("%s's health is too low to use Memento.", player);
        }

        player.hurt(HEALTH_COST);

        if (Math.random() < 0.5) {
            return restoreState(player);
        } else
            saveState(player);
            return String.format("%s saves their current state using Memento.", player);
    }

    /**
     * Saves the actor's current state, including health, mana, and strength, into the saved states list.
     *
     * @param player The actor whose state is being saved.
     */

    private void saveState(Actor player) {
        savedStates.add(new ActorState(player.getAttribute(BaseActorAttributes.HEALTH), player.getAttribute(BaseActorAttributes.MANA) , player.getAttribute(NewActorAttributes.STRENGTH)));
    }

    /**
     * Restores the actor's most recently saved state. If no saved states exist,
     * a message is returned indicating that nothing happens.
     *
     * @param player The actor whose state is being restored.
     * @return A string describing the result of the restoration.
     */
    private String restoreState(Actor player) {
        if (!savedStates.isEmpty()) {
            ActorState lastState = savedStates.remove(savedStates.size() - 1);
            player.modifyAttribute(BaseActorAttributes.HEALTH,ActorAttributeOperations.UPDATE,lastState.getHealth());
            player.modifyAttribute(BaseActorAttributes.MANA,ActorAttributeOperations.UPDATE,lastState.getMana());
            player.modifyAttribute(NewActorAttributes.STRENGTH,ActorAttributeOperations.UPDATE,lastState.getStrength());
            return String.format("%s restores their last saved state using Memento.", player);
        } else {
            return String.format("No previous state to restore.");
        }
    }
}
