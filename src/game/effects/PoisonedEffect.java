package game.effects;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.StatusEffect;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Location;
import game.enums.Ability;

/**
 * Class that extends StatusEffect abstract class and represents the poisoned effect applied to actors
 * If multiple poison effects are applied, damage will stack accordingly
 */
public class PoisonedEffect extends StatusEffect {
    private int poisonTurns;// Tracks the number of turns the actor has been poisoned
    private final int poisonDamage; // The amount of damage dealt after each turn
    private final int poisonDuration; // Maximum number of turns the poison effect lasts

    /**
     * Constructor for the PoisonedEffect class
     * Initialises poisoned effect with zero poison turns
     */
    public PoisonedEffect(int poisonDamage, int poisonDuration) {
        super("Poisoned");
        this.poisonDamage = poisonDamage;
        this.poisonDuration = poisonDuration;
    }

    /**
     * Applies the poison damage to the affected actor after each tick.
     * The actor takes 5 damage for every tick they are poisoned. After 3 turns, the effect is removed.
     *
     * @param location the location where the actor with the status effect is currently standing
     * @param actor the actor holding the status effect
     */
    @Override
    public void tick(Location location, Actor actor) {
        if (!actor.hasCapability(Ability.POISON_RESISTANT)){
            poisonTurns ++;
            actor.hurt(poisonDamage);
            new Display().println(String.format("%s is poisoned and takes %d damage!", actor, poisonDamage));
            if (poisonTurns == poisonDuration) {
                actor.removeStatusEffect(this);
            }
        }
    }

}
