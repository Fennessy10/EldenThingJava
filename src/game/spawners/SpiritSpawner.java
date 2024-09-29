package game.spawners;

import edu.monash.fit2099.engine.actors.Actor;
import game.actors.Spirit;

public class SpiritSpawner extends Spawner{

    public SpiritSpawner(){
        super(0.2);
    }

    @Override
    public Actor spawn(){
        return new Spirit();
    }
}
