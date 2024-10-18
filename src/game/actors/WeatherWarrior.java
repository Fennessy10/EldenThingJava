package game.actors;



import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.AttackAction;
import game.behaviours.FollowBehaviour;
import game.enums.Ability;
import game.enums.Status;
import game.weapons.ManFlyStinger;
import game.weapons.WeatherWarriorScythe;

/**
 * New actor class for WeatherWarrior which wandering in the sewers
 * WeatherWarrior can attack nearby actors by poison and following it
 */
public class WeatherWarrior extends Enemy {
    private static final int weatherWarriorHP = 90;
    private static final char displayChar = '¤';
    private static final String name = "WeatherWarrior";

    /**
     * constructor for WeatherWarrior
     * initialize it behaviour, char and hit point(50)
     */
    public WeatherWarrior() {
        super(name, displayChar, weatherWarriorHP);
        this.addCapability(Status.ENEMY);
        this.addCapability(Ability.POISON_RESISTANT);
        this.addCapability(Ability.FIRE_RESISTANT);
        this.setIntrinsicWeapon(new WeatherWarriorScythe());
    }


    /**
     * Determine allowable action towards WeatherWarrior
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction attack direction
     * @param map game map
     * @return a list for allowable action
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList(); //create a list of actions

        if (otherActor.hasCapability(Status.FOLLOW)) {
            super.behavioursPut(500, new FollowBehaviour(otherActor));
        }
        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            actions.add(new AttackAction(this, direction));
        }
        return actions;
    }

}
