package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.positions.NumberRange;
import game.weapons.StompWeapon;

/**
 * Class representing the Stomp behaviour.
 * Handles the logic for determining when and how the actor performs the stomp.
 */
public class StompBehaviour extends Action implements Behaviour {
    private final Actor target;
    private final StompWeapon stompWeapon;  // Reference to the StompWeapon

    /**
     *
     * @param target
     */
    public StompBehaviour(Actor target) {
        this.target = target;
        this.stompWeapon = new StompWeapon();  // Initialize the StompWeapon
    }

    /**
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        // Delegate the attack action to the weapon
        return stompWeapon.attack(actor, target, map);
    }

    /**
     *
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        Location here = map.locationOf(actor);
        Location there = map.locationOf(target);

        // If the target is aligned horizontally or vertically with no obstacles, return this action
        NumberRange xs, ys;
        if (here.x() == there.x() || here.y() == there.y()) {
            xs = new NumberRange(Math.min(here.x(), there.x()), Math.abs(here.x() - there.x()) + 1);
            ys = new NumberRange(Math.min(here.y(), there.y()), Math.abs(here.y() - there.y()) + 1);

            for (int x : xs) {
                for (int y : ys) {
                    if (map.at(x, y).getGround().blocksThrownObjects()) {
                        return null;
                    }
                }
            }
            return this;
        }
        return null;
    }

    /**
     *
     * @param actor The actor performing the action.
     * @return
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " prepares to stomp " + target;
    }
}
