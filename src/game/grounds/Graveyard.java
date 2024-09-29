package game.grounds;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.spawners.Spawner;

/**
 *The Graveyard class represents a ground type where actors can be spawned
 *in the game. It extends the Ground class and utilizes a Spawner
 *to generate actors at specified locations.
 */
public class Graveyard extends Ground {
    private Spawner spawner;

    /**
     * Constructs a Graveyard with a specific Spawner
     * @param spawner responsible for spawning actors in this graveyard
     */
    public Graveyard(Spawner spawner){
        super('n',"Graveyard");
        this.spawner = spawner;
    }

    /**
     * Triggers the spawning logic at the specified location
     * @param location The location of the Ground
     */
    public void tick(Location location){
        if (spawner.chanceToSpawn(location)){
            location.addActor(spawner.spawn());
        }
    }

}
