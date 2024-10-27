package game.actors;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.enums.Ability;
import game.enums.Status;
import game.actions.AttackAction;
import game.behaviours.FollowBehaviour;
import game.items.RemembranceOfTheFurnaceGolem;
import game.weapons.StompingFoot;

/**
 * Class representing the Furnace Golem
 * It can wander, follow, and stomp its enemies.
 */
public class FurnaceGolem extends Enemy {

    private static final int furnaceGolemHitpoints = 1000;
    /**
     * Constructor
     */
    public FurnaceGolem() {
        super("Furnace Golem", 'A', furnaceGolemHitpoints);
        this.setIntrinsicWeapon(new StompingFoot());
        this.addCapability(Ability.FIRE_RESISTANT);
    }

    /**
     * Determines what actions other actors can perform on the Furnance Golem
     *
     * Also handles implementation of follow behaviour by checking if a followable actor enters its
     * surroundings, and if so, adds follow behaviour to actor's treemap of behaviours.
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return An ActionList of available actions an actor can perform on the Furnace Golem
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();

        if (otherActor.hasCapability(Status.FOLLOW)) {
            super.behavioursPut(500, new FollowBehaviour(otherActor));
        }
        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            actions.add(new AttackAction(this, direction));
        }
        return actions;
    }

    /**
     *Called when Furnace Golem was defeated and player get remembrance
     *
     * @param actor the actor
     * @param map   where the Furnace Golem fell unconscious
     * @return a String representing the result of the unconscious state
     */
    @Override
    public String unconscious(Actor actor, GameMap map) {
        RemembranceOfTheFurnaceGolem remembranceOfTheFurnaceGolem = new RemembranceOfTheFurnaceGolem();
        actor.addItemToInventory(remembranceOfTheFurnaceGolem);
        return super.unconscious(actor, map);
    }
}
