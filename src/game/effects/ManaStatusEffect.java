package game.effects;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.StatusEffect;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Location;

/**
 * Represents a status effect that causes an actor to gain mana overtime or gain mana temporarily depending on whether the ability
 * is labelled as recurrent or not as the last parameter
 */
public class ManaStatusEffect extends StatusEffect {
    private final int duration;
    private final int manaAmount;
    private int Count = 0;

    /**
     * Constructor
     *
     */
    public ManaStatusEffect(Actor actor, int Duration, int manaAmount) {
        super("Healing");
        this.duration = Duration;
        this.manaAmount = manaAmount;
        actor.modifyAttributeMaximum(BaseActorAttributes.MANA, ActorAttributeOperations.INCREASE, manaAmount);
        new Display().println(String.format("\nThe %s had their max mana increased for %d !", actor, manaAmount));
    }

    /**
     * allows the status effect of gaining mana to occur overtime. Also removes the status effect/temporary mana
     * increase
     * @param location the location where the actor with the status effect is currently standing
     * @param actor the actor holding the status effect
     */
    @Override
    public void tick(Location location, Actor actor) {
        Count++;
        if (Count == duration) {
            actor.modifyAttributeMaximum(BaseActorAttributes.MANA, ActorAttributeOperations.DECREASE, manaAmount);
            actor.removeStatusEffect(this);
            Count = 0;
        }
    }
}
