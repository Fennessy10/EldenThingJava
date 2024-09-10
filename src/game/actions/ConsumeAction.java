package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.consumables.ConsumableItem;

/**
 *
 */
public class ConsumeAction extends Action {
    private ConsumableItem consumable;

    public ConsumeAction(ConsumableItem consumable) {
        this.consumable = consumable;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        return consumable.consume(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return consumable + " consumed by " + actor;
    }
}
