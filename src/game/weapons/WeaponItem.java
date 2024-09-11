package game.weapons;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.items.PickUpAction;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.enums.NewActorAttributes;
import game.actions.AttackAction;
import game.enums.Status;


import java.util.Random;

/**
 * An abstract class representing a weapon item
 * Extends the Item class and implements the Weapon interface providing basic function
 * for weapons such as attacking and picking up the weapon, as well as handling
 * weapon damage, hit rate and strength requirements
 */
public abstract class WeaponItem extends Item implements Weapon {
    private static final float DEFAULT_DAMAGE_MULTIPLIER = 1.0f;
    private int damage;
    private int hitRate;
    private final String verb;
    private float damageMultiplier;
    private int strengthReq;


    /**
     * Constructor.
     *
     * @param name        name of the item
     * @param displayChar character to use for display when item is on the ground
     * @param damage      amount of damage this weapon does
     * @param verb        verb to use for this weapon, e.g. "hits", "zaps"
     * @param hitRate     the probability/chance to hit the target.
     * @param strengthReq the minimum strength required to use this weapon
     */
    public WeaponItem(String name, char displayChar, int damage, String verb, int hitRate, int strengthReq) {
        super(name, displayChar, true);
        this.damage = damage;
        this.verb = verb;
        this.hitRate = hitRate;
        this.damageMultiplier = DEFAULT_DAMAGE_MULTIPLIER;
        this.strengthReq = strengthReq;
    }

    /**
     * Executes the attack with the weapon
     *
     * @param attacker the actor who performed the attack
     * @param target   the actor who is the target of the attack
     * @param map      the map on which the attack was executed
     * @return A string describing the result of the attack
     */
    @Override
    public String attack(Actor attacker, Actor target, GameMap map) {
        Random rand = new Random();
        if (!(rand.nextInt(100) < this.hitRate)) {
            return attacker + " misses " + target + ".";
        }

        target.hurt(Math.round(damage * damageMultiplier));

        return String.format("%s %s %s for %d damage", attacker, verb, target, damage);
    }

    /**
     * Returns a pick-up actions if the actor has sufficient strength to wield a weapon
     *
     * @param actor The actor attempting to pick up the weapon
     * @return A PickUpAction if the actor has enough strength, otherwise null
     */
    @Override
    public PickUpAction getPickUpAction(Actor actor) {
        if (actor.hasCapability(NewActorAttributes.STRENGTH)) {
            if (strengthReq <= actor.getAttributeMaximum(NewActorAttributes.STRENGTH)) {
                return new PickUpAction(this);
            }
        }
        return null;
    }

    /**
     * List of allowable actions an actor can perform with a WeaponItem
     * i.e. if there is an enemy in the surroundings, a player can use a WeaponItem to attack it
     *
     * @param otherActor the actor the action is against
     * @param location the location of the other actor
     * @return an unmodifiable list of Actions
     */

    @Override
    public ActionList allowableActions(Actor otherActor, Location location) {
        ActionList actions = new ActionList();
        if (otherActor.hasCapability(Status.ENEMY)) {
            actions.add(new AttackAction(otherActor, location.toString(), this));
        }
        return actions;
    }
}





