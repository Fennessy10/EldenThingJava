package game.grounds;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.spawners.Spawner;

public class Graveyard extends Ground {
    private Spawner spawner;

    public Graveyard(Spawner spawner){
        super('n',"Graveyard");
        this.spawner = spawner;
    }

    public void tick(Location location){
        if (spawner.chanceToSpawn(location)){
            location.addActor(spawner.spawn());
        }
    }

}
