package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.enums.Ability;
import game.enums.Status;

/**
 * A class that represents burning ground.
 */
public class FireGround extends Ground {
    private int duration;  // Duration of the fire in turns
    private Ground previousGround;
    private Actor actor;

    /**
     *
     * @param previousGround
     * @param actor
     */
    public FireGround(Ground previousGround, Actor actor) {
        super('w', "burning ground");
        this.duration = 5;  // Fire lasts for 5 turns
        this.previousGround = previousGround;
        this.actor = actor;
    }

    /**
     *
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        super.tick(location);
        duration--;
        if (duration <= 0) {
            // Replace burning ground with the original ground
            location.setGround(previousGround); // Replace with original ground
        }

        if (location.containsAnActor()) {
            Actor actor = location.getActor();

            // Furnace Golem is immune to burning
            if (!(actor.hasCapability(Ability.FIRE_RESISTANT))) {
                actor.addCapability(Status.BURNING);  // Add BURNING status to the actor
            }
        }
    }
}
