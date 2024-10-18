package game.weapons;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.effects.PoisonedEffect;

import java.util.Random;

public class WeatherWarriorScythe extends IntrinsicWeapon{
    private final static int weatherWarriorDamage = 20;
    private final static String weatherWarriorVerb = "stings";
    private final static int weatherWarriorHitRate = 25;
    private final static int poisonWaterChance = 30;

    private final Random random = new Random();

    public WeatherWarriorScythe() {
        super(weatherWarriorDamage, weatherWarriorVerb, weatherWarriorHitRate);
    }

    /**
     * attack action method only for man_fly
     * 25% chance to hit player, if hit on target, have 30% chance to cause poison effect
     *
     * @param attacker the actor who performed the attack
     * @param target   the actor who is the target of the attack
     * @param map      the map on which the attack was executed
     * @return
     */
    @Override
    public String attack(Actor attacker, Actor target, GameMap map) {
        if (!(random.nextInt(100) < this.hitRate)) {
            return attacker + " misses " + target + ".";
        }
        if(random.nextInt(100) < poisonWaterChance){
            target.addStatusEffect(new PoisonedEffect(10, 2)); //if poison on add effect on player
            return (attacker + " hits target and poison applied to " + target);
        }
        target.hurt(damage);

        return String.format("%s %s %s for %d damage", attacker, verb, target, damage);
    }
}
