package game.weapons;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.enums.Ability;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import game.items.Fire;

/**
 * Class representing the Stomp weapon.
 */
public class StompingFoot extends IntrinsicWeapon {
    private final int EXPLOSION_DAMAGE = 50;
    private final double EXPLOSION_CHANCE = 10;


    /**
     * Constructor for the StompingFood weapon
     * Initializes weapon with damage, attack verb and hit rate
     */
    public StompingFoot() {
        super(100, "stomps on", 5);
    }

    @Override
    public String attack(Actor attacker, Actor target, GameMap map) {
        Random random = new Random();
        StringBuilder result = new StringBuilder();

        if (!(random.nextInt(100) < this.hitRate)) {
            return attacker + " misses " + target + ".";
        }
        target.hurt(damage);
        result.append(String.format("%s %s %s for %d damage", attacker, verb, target, damage));

        if (random.nextInt(100) < EXPLOSION_CHANCE) {
            result.append("\n%s's stomp attack results in an Explosion!");

            Location location = map.locationOf(attacker);
            List<Location> surroundingLocations = new ArrayList<>();
            for (Exit exit : location.getExits()) {
                surroundingLocations.add(exit.getDestination());
            }

            for (Location surroundingLocation : surroundingLocations) {
                Ground surroundingGround = surroundingLocation.getGround();
                if (!surroundingGround.hasCapability(Ability.FIRE_RESISTANT)) {
                    surroundingLocation.addItem(new Fire());
                }
            }

            for (Location surroundingLocation : surroundingLocations) {
                if (surroundingLocation.containsAnActor()) {
                    Actor nearbyActor = surroundingLocation.getActor();
                    if (!nearbyActor.hasCapability(Ability.FIRE_RESISTANT)) {
                        nearbyActor.hurt(EXPLOSION_DAMAGE);
                        result.append(String.format("\nThe explosion burns %s for %d damage!", nearbyActor, EXPLOSION_DAMAGE));
                    }

                }
            }
        }
        return result.toString();
    }
}