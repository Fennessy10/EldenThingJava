package game.actors;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.StatusEffect;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Location;

public class HealingStatusEffect extends StatusEffect {
    private final int maxHealingTurns;
    private final int healAmount;
    private int healingCount = 0;

    public HealingStatusEffect(int healingTurns, int healAmount) {
        super("Healing");
        this.maxHealingTurns = healingTurns;
        this.healAmount = healAmount;
    }

    @Override
    public void tick(Location location, Actor actor) {
        while (healingCount < maxHealingTurns) {
            healingCount++;
            actor.heal(healAmount); // Apply healing
            new Display().println(String.format("\nThe %s is healed for %d hp!", actor, healAmount));
        }


        if (healingCount == maxHealingTurns) { // The player heals only for every tick
            actor.removeStatusEffect(this);
            healingCount = 0; // Reset healing turns
        }
    }
}

