package game.items;

import edu.monash.fit2099.engine.items.Item;
import game.enums.ItemCapability;

/**
 * Represents a Remembrance Of Dancing Lion,
 * It can be traded with merchants to obtain a Divine Beast Head
 */
public class RemembranceOfDancingLion extends Item {
    /**
     * Constructor
     * Initializes the Remembrance Dancing Lion with its name, character, and capability
     */
    public RemembranceOfDancingLion() {
        super("Remembrance Dancing Lion", '*', true);
        this.addCapability(ItemCapability.REMEMBRANCE_OF_DANCING_LION);
    }
}
