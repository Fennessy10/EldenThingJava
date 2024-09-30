package game.spawners;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;

/**
 * Creating a parent line allows other children to inherit.
 */
public abstract class Spawner {

    private double chance;

    /**
     *Constructs a spawner with a given spawn chance
     * @param chance the probability to spawn an actor
     */
    public Spawner(double chance){
        this.chance = chance;
    }

    /**
     * Abstract method for spawning an actor. Must be implemented by subclasses
     * @return the spawned actor
     */
    public abstract Actor spawn();

    /**
     * Determines if an actor should spawn at the location
     * @param location the location where an actor may spawn
     * @return true if an actor should spawn, false otherwise
     */
    public boolean chanceToSpawn(Location location){
        if (Math.random() <= chance && !location.containsAnActor()){
            return true;
        }
        return false;
    }
}
