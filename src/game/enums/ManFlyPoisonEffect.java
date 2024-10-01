package game.enums;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.StatusEffect;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Location;

/**
 * Posion effect for man_flies attack
 * 10 points of damage over 2 turns and can stack if applied multiple times
 */
public class ManFlyPoisonEffect extends StatusEffect {
    private int poisonTurns = 0;
    private static final int POISON_DAMAGE = 10;
    private static final int MAX_TURNS = 2;
    /**
     * constructor for ManFlyPisonEffect class
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
        actor.hurt(POISON_DAMAGE);
        new Display().println(actor + " now is poisoned by Manfly and takes" + POISON_DAMAGE + " damage");
        if (poisonTurns >= MAX_TURNS) { //remove effect in two turns
            actor.removeStatusEffect(this);
        }
    }
}
