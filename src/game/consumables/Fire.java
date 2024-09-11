package game.consumables;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.enums.Ability;
import game.enums.Status;

public class Fire extends Item {
    private int FIRE_DAMAGE = 5;
    private int MAX_BURN_DURATION = 5;

    public Fire() {
        super("Fire", 'w', false);
    }

    @Override
    public void tick(Location location) {
        super.tick(location);
        MAX_BURN_DURATION--;
        if (MAX_BURN_DURATION == 0) {
            location.removeItem(this);// Remove the fire once the burn duration has passed.
        }

        if (location.containsAnActor()) {
            Actor actor = location.getActor();
                if (!actor.hasCapability(Ability.FIRE_RESISTANT)) {
                    actor.hurt(FIRE_DAMAGE);
                    new Display().println(String.format("\nThe explosion burns %s for %d damage!", actor, FIRE_DAMAGE)); // Add BURNING status to the actor
            }
        }
    }
}
