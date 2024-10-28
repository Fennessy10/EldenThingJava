package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.Scarab;

import java.util.Random;

/**
 * A class that represents a random puddle of water.
 * Consuming the water restores mana and has a chance to spawn a scarab.
 */
public class Puddle extends Water {
    /**
     * Constructor for the Puddle class.
     * Initializes with '~' character and the name "Puddle".
     */
    public Puddle() {
        super('~', "Puddle");
    }

    /**
     * Consumes the puddle, restoring mana and potentially spawning a Scarab.
     * @param actor The actor consuming the water.
     * @param map The map where the action is taking place.
     * @return A descriptive string of the action.
     */
    @Override
    public String consume(Actor actor, GameMap map) {
        Random random = new Random();
        int manaIncrease = 5;

        // Restore mana to the actor
        actor.modifyAttribute(BaseActorAttributes.MANA, ActorAttributeOperations.INCREASE, manaIncrease);

        // Chance to spawn a scarab
        int spawnChance = 10;  // 10% chance
        if (random.nextInt(100) < spawnChance) {
            Location scarabLocation = findScarabSpawnLocation(map, actor);
            if (scarabLocation != null) {
                scarabLocation.addActor(new Scarab());
                return String.format("The puddle was consumed by %s. A Scarab has spawned nearby!", actor);
            }
        }

        return String.format("The puddle was consumed by %s. %s feels a surge of mana.", actor, actor);
    }

    /**
     * Helper method to find a suitable location for spawning a scarab within the surrounding area.
     * @param map The map where the actor is located.
     * @param actor The actor consuming the puddle.
     * @return A location for spawning the scarab, or null if no suitable location is found.
     */
    private Location findScarabSpawnLocation(GameMap map, Actor actor) {
        Location actorLocation = map.locationOf(actor);

        // Iterate over each exit to find a suitable adjacent location
        for (Exit exit : actorLocation.getExits()) {
            Location adjacentLocation = exit.getDestination();
            if (!adjacentLocation.containsAnActor()) {
                return adjacentLocation;
            }
        }
        return null;  // No suitable location found
    }



}
