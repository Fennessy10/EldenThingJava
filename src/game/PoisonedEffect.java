package game;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.StatusEffect;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Location;

import java.util.ArrayList;

/**
 * Class that extends StatusEffect abstract class and represents the poisoned effect applied to actors who traverse over a PoisonSwamp
 * The effect deals 5 damage per tick and last for three turns
 * If multiple poison effects are applied, damage will stack accordingly
 */
public class PoisonedEffect extends StatusEffect {
    private int poisonTurns;// Tracks the number of turns the actor has been poisoned
    private int POISON_DAMAGE = 5; // The amount of damage dealt after each turn
    private int MAX_POISON_TURNS = 3; // Maximum number of turns the poison effect lasts

    /**
     * Constructor for the PoisonedEffect class
     * Initialises poisoned effect with zero poison turns
     */
    public PoisonedEffect() {
        super("Poisoned");
        this.poisonTurns = 0;
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
        poisonTurns ++;
        actor.hurt(POISON_DAMAGE);
        new Display().println(String.format("%s is poisoned and takes %d damage!", actor, POISON_DAMAGE));

        if (poisonTurns == MAX_POISON_TURNS) {
            actor.removeStatusEffect(this);
        }

    }

}
