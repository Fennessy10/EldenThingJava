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


import java.util.Random;

/**
 * Class representing items that can be used as a weapon.
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
     *
     * @param attacker the actor who performed the attack
     * @param target   the actor who is the target of the attack
     * @param map      the map on which the attack was executed
     * @return
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
     *
     * @param actor
     * @return
     */
    @Override
    public PickUpAction getPickUpAction(Actor actor) {
        if (strengthReq <= actor.getAttributeMaximum(NewActorAttributes.STRENGTH)) {
            return new PickUpAction(this);
        }
        return null;
    }


    @Override
    public ActionList allowableActions(Actor actor, Location location) {
        ActionList actions = super.allowableActions(location);
        actions.add(new AttackAction(actor, location.toString(), this));
        actions.add(getDropAction(actor));
        return actions;
    }
}



