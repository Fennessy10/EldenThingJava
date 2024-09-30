package game.actors;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.StatusEffect;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Location;

public class HealingStatusEffect extends StatusEffect {
    private final int Duration;
    private final int healAmount;
    private int healingCount = 0;
    private final boolean recurrent;

    public HealingStatusEffect(Actor actor, int Duration, int healAmount,  Boolean recurrent) {
        super("Healing");
        this.Duration = Duration;
        this.healAmount = healAmount;
        this.recurrent = recurrent;
        if (!recurrent) {
            actor.modifyAttributeMaximum(BaseActorAttributes.HEALTH, ActorAttributeOperations.INCREASE, healAmount);
            new Display().println(String.format("\nThe %s had their hp increased for %d !", actor, healAmount));
        }
    }

    @Override
    public void tick(Location location, Actor actor) {
        healingCount++;
        if (recurrent) {
            actor.heal(healAmount); // Apply healing
            new Display().println(String.format("\nThe %s is healed for %d hp!", actor, healAmount));
        }


        if (healingCount == Duration) { // The player heals only for every tick
            if (!recurrent )
            actor.removeStatusEffect(this);
            healingCount = 0; // Reset healing turns
        }
    }
}

