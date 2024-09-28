package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.consumables.Consumable;

/**
 * An Action that allows an actor to consume a consumable item
 *
 * This action interacts with the {@link Consumable} interface to trigger the
 * consume behaviour, which can vary depending on the specific consumable item.
 */
public class ConsumeAction extends Action {
    /**
     * The consumable item that will be consumed by the actor
     */
    private Consumable consumable;

    /**
     * Constructor.
     *
     * @param consumable the consumable item that will be consumed by the actor.
     */
    public ConsumeAction(Consumable consumable) {
        this.consumable = consumable;
    }

    /**
     * Executes the consume action.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a string describing the result of consumption.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        return consumable.consume(actor, map);
    }

    /**
     * Returns a description of the action to display in the menu.
     *
     * @param actor The actor performing the action.
     * @return a string description of the action
     */
    @Override
    public String menuDescription(Actor actor) {
        return consumable + " consumed by " + actor;
    }
}
