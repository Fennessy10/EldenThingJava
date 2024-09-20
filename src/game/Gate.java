package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.positions.World;

/**
 * A class representing a Gate Item, which allows actors to travel between different maps.
 */
public class Gate extends Item {
    private Action transportAction;

    /**
     * Constructor for Gate
     * Initializes the Gate with the name "Gate" and the symbol 'H'
     */
    public Gate() {
        super("Gate", 'H', false);
    }

    /**
     * Adds a new action for transporting the actor when using the Gate
     *
     * @param newAction The action to be added for transportation
     */
    public void addSampleAction(Action newAction){
        this.transportAction = newAction;
    }

    /**
     * Returns a list of allowable actions for the Gate, including the transport action.
     *
     * @param location the location of the ground on which the item lies
     * @return An ActionList containing all allowable actions for the Gate
     */
    @Override
    public ActionList allowableActions(Location location) {
        ActionList actions = super.allowableActions(location);
        actions.add(transportAction);
        return actions;
    }
}
