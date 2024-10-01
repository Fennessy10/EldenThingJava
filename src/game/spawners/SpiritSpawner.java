package game.spawners;

import edu.monash.fit2099.engine.actors.Actor;
import game.actors.Spirit;

/**
 * The SpiritSpawner class is responsible for spawning Spirit actors
 * in the game. It extends the Spawner class and provides a specific
 * implementation of the spawn method, which creates a new Spirit actor.
 */
public class SpiritSpawner extends Spawner{

    /**
     * Constructs a new SpiritSpawner with a fixed spawn chance of 20%
     */
    public SpiritSpawner(){
        super(0.2);
    }

    /**
     * spawn a new actor: Spirit
     * @return a newly created instance
     */
    @Override
    public Actor spawn(){
        return new Spirit();
    }
}
