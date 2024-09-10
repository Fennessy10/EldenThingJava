package game.grounds;

import edu.monash.fit2099.engine.positions.Ground;
import game.enums.Ability;

/**
 * A class that represents a random puddle of water.
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 *
 */
public class Puddle extends Ground {
    public Puddle() {
        super('~', "Puddle");
        this.addCapability(Ability.FIRE_RESISTANT);
    }
}
