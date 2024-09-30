package game.actors;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.StatusEffect;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Location;

public class ManaStatusEffect extends StatusEffect {
    private final int Duration;
    private final int manaAmount;
    private int Count = 0;
    private final boolean recurrent;

    public ManaStatusEffect(Actor actor, int Duration, int manaAmount, Boolean recurrent) {
        super("Healing");
        this.Duration = Duration;
        this.manaAmount = manaAmount;
        this.recurrent = recurrent;
        if (!recurrent) {
            actor.modifyAttributeMaximum(BaseActorAttributes.MANA, ActorAttributeOperations.INCREASE, manaAmount);
            new Display().println(String.format("\nThe %s had their max mana increased for %d !", actor, manaAmount));
        }
    }

    @Override
    public void tick(Location location, Actor actor) {
        Count++;
        if (recurrent) {
            actor.modifyAttribute(BaseActorAttributes.MANA, ActorAttributeOperations.INCREASE, manaAmount);
            new Display().println(String.format("\nThe %s had their mana increased for %d !", actor, manaAmount));
        }


        if (Count == Duration) { // The player heals only for every tick
            actor.removeStatusEffect(this);
            Count = 0; // Reset healing turns
        }
    }
}
