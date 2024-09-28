package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.StatusEffect;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.BurningStatusEffect;
import game.enums.Ability;
import game.enums.Status;

/**
 * Class represents a fire item that can cause burning damage to actors.
 * The fire persists for a certain number of turns and inflicts damage to actors who stand on it.
 */
public class Fire extends Item {
    private int burnDuration = 0;

    public Fire() {
        super("Fire", 'w', false);
    }

    /**
     * Manages the duration of the fire.
     * Increases burn duration and removes the fire from the location if it has reached the MAX_BURN_DURATION
     * Applied burning damage present to actors in the location that are not fire-resistant
     *
     * @param location The location of the ground on which we lie.
     */
    @Override
    public void tick(Location location) {
        int maxBurnDuration = 5;
        super.tick(location);
        burnDuration++;
        if (burnDuration == maxBurnDuration) {
            location.removeItem(this); // Remove the fire once the burn duration has passed.
        }

        if (location.containsAnActor()) {
            Actor actor = location.getActor();
                if (!actor.hasCapability(Ability.FIRE_RESISTANT)) {
                    actor.addStatusEffect(new BurningStatusEffect());
            }
        }
    }
}
