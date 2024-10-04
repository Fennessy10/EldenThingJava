package game.enums;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.StatusEffect;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Location;

/**
 * Poison effect for man_flies attack
 * 10 points of damage over 2 turns and can stack if applied multiple times
 */
public class ManFlyPoisonEffect extends StatusEffect {
    private int poisonTurns = 0;
    private static final int poisonDamage = 10;
    private static final int maxTurns = 2;
    /**
     * constructor for ManFlyPoisonEffect class
     */
    public ManFlyPoisonEffect() {
        super("ManFlyPoisonEffect");
    }

    /**
     * Act poison damage to actor
     * @param actor actor damaged by man fly
     * @param location current location
     */
    @Override
    public void tick(Location location, Actor actor) {
        poisonTurns++;
        actor.hurt(poisonDamage);
        new Display().println(actor + " now is poisoned by ManFly and takes " + poisonDamage + " damage");
        if (poisonTurns >= maxTurns) { //remove effect in two turns
            actor.removeStatusEffect(this);
        }
    }
}
