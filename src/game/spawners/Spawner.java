package game.spawners;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;

public abstract class Spawner {
    private double chance;

    public Spawner(double chance){
        this.chance = chance;
    }

    public abstract Actor spawn();

    public boolean chanceToSpawn(Location location){
        if (Math.random() <= chance && !location.containsAnActor()){
            return true;
        }
        return false;
    }
}
