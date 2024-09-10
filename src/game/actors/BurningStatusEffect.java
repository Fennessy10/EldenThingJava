package game.actors;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.actors.StatusEffect;
import game.enums.Status;

/**
 * Class representing the burning status effect.
 * It deals fire damage over time and removes itself after a few turns.
 */
public class BurningStatusEffect extends StatusEffect {
    private int burningTurns;

    public BurningStatusEffect() {
        super("Burning");
        this.burningTurns = 0;
    }

    @Override
    public void tick(Location location, Actor actor) {
        burningTurns++;
        actor.hurt(5); // Apply 5 damage for being on burning ground
        System.out.println(actor + " takes 5 fire damage from burning ground.");

        // Remove the status after 3 turns
        if (burningTurns >= 3) {
            actor.removeCapability(Status.BURNING);
            burningTurns = 0; // Reset burning turns
            System.out.println(actor + " is no longer burning.");
        }
    }
}
