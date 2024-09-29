package game.spawners;

import edu.monash.fit2099.engine.actors.Actor;
import game.actors.ManFly;

public class ManFlySpawner extends Spawner{

    public ManFlySpawner(){
        super(0.15);
    }

    @Override
    public Actor spawn() {
        return new ManFly();
    }
}
