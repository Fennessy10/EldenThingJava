package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.effects.PoisonedEffect;

/**
 * A concrete class that extends Ground and inflicts poison damage to actors who step on it
 */
public class PoisonSwamp extends Ground {

    /**
     * Constructor for PoisonSwamp
     * Initializes the Poison Swamp with the '+' symbol and the name "poison swamp"
     */
    public PoisonSwamp() {
        super('+', "Poison Swap");
    }

    /**
     * Applies the poison effect to any actor standing on the Poison Swamp at every tick
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        if (location.containsAnActor()) {
            Actor actor = location.getActor();
            actor.addStatusEffect(new PoisonedEffect());
        }
    }
}

