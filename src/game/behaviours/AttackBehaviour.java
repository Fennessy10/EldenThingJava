package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.actions.AttackAction;
import game.enums.Status;

/**
 * A behaviour that allows an actor to Attack another actor (e.g., the Tarnished).
 * This behaviour checks if the other actor is HOSTILE and attacks with weapon, if in inventory, or with intrinsic weapon otherwise.
 */
public class AttackBehaviour implements Behaviour {
    Weapon weapon;

    /**
     * Constructor for AttackBehaviour with no specific weapon.
     * Initializes attack behaviour with no specific weapon
     */
    public AttackBehaviour() {weapon = null;}

    /**
     * Constructor for AttackBehaviour using a specific weapon.
     * @param weapon The weapon to use during the attack
     */
    public AttackBehaviour(Weapon weapon) {
        this.weapon = weapon;
    }

    /**
     * Determines the action to take during the actor's turn based on the nearby enemies.
     * Checks all exits from actor's location and will attempt to attack them if a hostile actor is found.
     * If a weapon is provided, this will be used in the attack.
     *
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return an AttackAction if a hostile actor is found, otherwise null.
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        for (Exit exit: map.locationOf(actor).getExits()) {
            if (!exit.getDestination().containsAnActor()) {
                continue;
            }

            Actor enemyActor = exit.getDestination().getActor();

            if (enemyActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
                Action action = new AttackAction(enemyActor, map.locationOf(enemyActor).toString(), this.weapon);
                return action;
            }
        }

        return null;
    }
}
