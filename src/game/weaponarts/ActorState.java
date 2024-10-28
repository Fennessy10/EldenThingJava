package game.weaponarts;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import game.enums.NewActorAttributes;

/**
 * A class that represents the state of an actor at a particular time.
 * This class stores key attributes such as health, mana, and strength,
 * allowing these values to be saved and restored as needed.
 * It can be extended to store attributes such as stamina or even location of an actor
 * Created by:
 * @author Sebastian Aisea
 */

public class ActorState {
    private final int health;
    private final int mana;
    private final int strength;

    /**
     * Constructor to initialize a new ActorState with the given health, mana, and strength values.
     *
     * @param health   The actor's health at the time of saving.
     * @param mana     The actor's mana at the time of saving.
     * @param strength The actor's strength at the time of saving.
     */

    public ActorState(int health, int mana, int strength) {
        this.health = health;
        this.mana = mana;
        this.strength = strength;
    }


    /**
     * Retrieves the health value stored in this state.
     *
     * @return The saved health value.
     */
    public int getHealth() {
        return health;
    }

    /**
     * Retrieves the mana value stored in this state.
     *
     * @return The saved mana value.
     */
    public int getMana() {
        return mana;
    }

    /**
     * Retrieves the strength value stored in this state.
     *
     * @return The saved strength value.
     */
    public int getStrength() {
        return strength;
    }

    /**
     * Restores the actor's attributes to the attributes saved in the ActorState
     *
     * @param player The actor whose state is being restored.
     * @return null
     */
    public String restoreState(Actor player) {
        player.modifyAttribute(BaseActorAttributes.HEALTH, ActorAttributeOperations.UPDATE, getHealth());
        player.modifyAttribute(BaseActorAttributes.MANA, ActorAttributeOperations.UPDATE, getMana());
        player.modifyAttribute(NewActorAttributes.STRENGTH, ActorAttributeOperations.UPDATE, getStrength());
        return null;
    }

    /**
     * Creates and returns a new ActorState based on the actor's current attributes.
     *
     * @param player The actor whose state is being captured.
     * @return A new ActorState instance.
     */
    public static ActorState newState(Actor player) {
        int health = player.getAttribute(BaseActorAttributes.HEALTH);
        int mana = player.getAttribute(BaseActorAttributes.MANA);
        int strength = player.getAttribute(NewActorAttributes.STRENGTH);
        return new ActorState(health, mana, strength);
    }
}