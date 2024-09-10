package game.weapons;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.actors.FurnaceGolem;
import game.enums.Ability;
import game.enums.Status;
import game.grounds.FireGround;
import game.grounds.Puddle;

import java.util.Random;

/**
 * Class representing the Stomp weapon.
 */
public class StompWeapon implements Weapon {
    private final int damage = 100;
    private final int hitRate = 5;
    private final String verb = "stomps";
    private final float damageMultiplier = 1.0f;
    private final int explosionDamage = 50;
    private final double explosionChance = 0.10;

    /**
     *
     * @param attacker the actor who performed the attack
     * @param target   the actor who is the target of the attack
     * @param map      the map on which the attack was executed
     * @return
     */
    @Override
    public String attack(Actor attacker, Actor target, GameMap map) {
        Random rand = new Random();
        StringBuilder result = new StringBuilder();

        // Handle stomp attack
        if (rand.nextInt(100) >= this.hitRate) {
            result.append(attacker).append(" misses ").append(target).append(".");
        } else {
            int inflictedDamage = Math.round(damage * damageMultiplier);
            target.hurt(inflictedDamage);
            result.append(String.format("%s %s %s for %d damage.", attacker, verb, target, inflictedDamage));
        }

        // Handle explosion chance
        if (rand.nextDouble() < explosionChance) {
            result.append("\nThe ground shakes as an explosion erupts! Watch Out!");

            // Get the Golem's current location
            Location golemLocation = map.locationOf(attacker);

            // Apply damage to all actors in the surrounding area
            for (Exit exit : golemLocation.getExits()) {
                Location adjacentLocation = exit.getDestination();
                if (adjacentLocation.containsAnActor()) {
                    Actor adjacentActor = adjacentLocation.getActor();
                    if (adjacentActor != null) { // Ensure there's an actor at the location
                        if (!(adjacentActor instanceof FurnaceGolem)) { // Golem is fire-resistant
                            adjacentActor.hurt(explosionDamage);
                            result.append(String.format("\n%s is caught in the explosion and takes %d damage.", adjacentActor, explosionDamage));
                        }
                    }
                }

                // Apply burning ground effect
                if (!(adjacentLocation.getGround().hasCapability(Ability.FIRE_RESISTANT))) {
                    adjacentLocation.setGround(new FireGround(adjacentLocation.getGround(), target));

                }
            }
        }

        return result.toString();
    }
}
