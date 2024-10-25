package game.items;

import edu.monash.fit2099.engine.items.Item;
import game.enums.ItemCapability;

/**
 * Represents a Remembrance Of The Furnace Golem,
 * It can be traded with merchants to obtain a Furnace Engine
 */
public class RemembranceOfTheFurnaceGolem extends Item {

    /**
     * Constructor
     * Initializes the Remembrance of the furnace golem with its name, character, and capability
     */
    public RemembranceOfTheFurnaceGolem(){
        super("Remembrance Of The Furnace Golem", '*', true);
        this.addCapability(ItemCapability.REMEMBRANCE_OF_FURNACE_GOLEM);
    }
}
