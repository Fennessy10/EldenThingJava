package game.effects;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.StatusEffect;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Location;

/**
 * Represents a status effect that causes an actor to heal overtime or heal temporarily depending on whether the ability
 * is labelled as recurrent or not as the last parameter
 */

public class HealingStatusEffect extends StatusEffect {
    private final int duration;
    private final int healAmount;
    private int healingCount = 0;
    private final boolean recurrent;


    /**
     * Constructor that contains healing properties if the health is supposed to be a one time occurrence
     * rather than healing overtime
     */
    public HealingStatusEffect(Actor actor, int Duration, int healAmount,  Boolean recurrent) {
        super("Healing");
        this.duration = Duration;
        this.healAmount = healAmount;
        this.recurrent = recurrent;
        if (!recurrent) {
            actor.modifyAttributeMaximum(BaseActorAttributes.HEALTH, ActorAttributeOperations.INCREASE, healAmount);
            new Display().println(String.format("\nThe %s had their hp increased for %d !", actor, healAmount));
        }
    }

    /**
     * Heals the actor overtime if healing is done slowly (recurrent) and removes the status effect after enough turns
     * @param location the location where the actor with the status effect is currently standing
     * @param actor the actor holding the status effect
     */
    @Override
    public void tick(Location location, Actor actor) {
        healingCount++;
        if (recurrent) {
            actor.heal(healAmount); // Apply healing
            new Display().println(String.format("\nThe %s is healed for %d hp!", actor, healAmount));
        }


        if (healingCount == duration) { // The player heals only for every tick
            if (!recurrent) {
                actor.modifyAttributeMaximum(BaseActorAttributes.HEALTH, ActorAttributeOperations.DECREASE, healAmount);
            }
            actor.removeStatusEffect(this);
            healingCount = 0; // Reset healing turns
        }
    }
}

