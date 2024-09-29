package game.spawners;

import edu.monash.fit2099.engine.actors.Actor;
import game.actors.ManFly;

/**
 * The ManFlySpawner class is responsible for spawning  ManFly actors
 * in the game. It extends the  Spawner class and provides a specific
 * implementation of the spawn method, which creates a new ManFly actor.
 */
public class ManFlySpawner extends Spawner{

    /**
     *Constructs a new ManFlySpawner with a fixed spawn chance of 15%
     */
    public ManFlySpawner(){
        super(0.15);
    }

    /**
     *Spawns a new ManFly actor
     * @return a newly created ManFly instance
     */
    @Override
    public Actor spawn() {
        return new ManFly();
    }
}
