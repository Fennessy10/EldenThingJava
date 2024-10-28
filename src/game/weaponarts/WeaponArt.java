package game.weaponarts;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
/**
 * An interface representing a weapon art
 *
 * Created by:
 * @author Sebastian Aisea
 *
 **/

public interface WeaponArt {


    String activate(Actor player, GameMap map);

}
