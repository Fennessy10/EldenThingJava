package game.effects;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.actors.StatusEffect;

/**
 * Represents a status effect that causes an actor to take burning damage.
 * Effect lasts only one turn, for every turn spent on the burning ground, applying damage and then removing itself.
 */
public class BurningStatusEffect extends StatusEffect {
    private int burningTurns = 0;

    /**
     * Constructor
     */
    public BurningStatusEffect() {
        super("Burning");
    }

    /**
     * Applied the burning damage to the actor and handles the effect's duration
     * The effect deals damage and removes itself after one turn
     *
     * @param location the location where the actor with the status effect is currently standing
     * @param actor the actor holding the status effect
     */
    @Override
    public void tick(Location location, Actor actor) {
        int burnDamage = 5;
        burningTurns++;
        actor.hurt(burnDamage); // Apply 5 damage for being on burning ground
        new Display().println(String.format("\nThe explosion burns %s for %d damage!", actor, burnDamage));

        if (burningTurns == 1) { // The player burns only for every tick they spend on the burning ground
            actor.removeStatusEffect(this);
            burningTurns = 0; // Reset burning turns
        }
    }
}
