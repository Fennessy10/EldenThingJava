package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.DivineBeast;
import game.weapons.DivinePower;

/**
 * Represents an action where the Divine Beast performs a special attack on ts target using its current divine pwoer
 */
public class DivineAttackAction extends Action {
    private DivineBeast divineBeast;
    private Actor target;
    private DivinePower divinePower;

    /**
     * Constructor for DivineAttackAction
     *
     * @param divineBeast the Divine Beast performing the attack
     * @param divinePower the current Divine Power used for the attack
     * @param target the target of the Divine Beast's attack
     */
    public DivineAttackAction(DivineBeast divineBeast, DivinePower divinePower, Actor target) {
        this.divinePower = divinePower;
        this.target = target;
        this.divineBeast = divineBeast;
    }

    /**
     * Executes the divine attack action by the Divine Beast on the target, utilizing the current power's
     * specific attack behaviour
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a description of the attack action
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Action divineAction = divinePower.divineAttack(divineBeast, map, target);
        if (divineAction != null) {
            divineAction.execute(actor, map);
        }
        return divinePower.attackDescription();
    }

    /**
     * Returns the menu description for this action
     * Since this action is specific to the Divine Beast's attack, no description is needed.
     *
     * @param actor The actor performing the action.
     * @return null as this actor does not require a menu description
     */
    @Override
    public String menuDescription(Actor actor) {
        return null;
    }
}
