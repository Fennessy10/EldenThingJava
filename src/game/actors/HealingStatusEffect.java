package game.actors;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.StatusEffect;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Location;

public class HealingStatusEffect extends StatusEffect {
    private int healingTurns;
    private final int healAmount;

    public HealingStatusEffect(int healingTurns, int healAmount) {
        super("Healing");
        this.healingTurns = healingTurns;
        this.healAmount = healAmount;
    }

    @Override
    public void tick(Location location, Actor actor) {
        while (healingTurns < 5) {
            healingTurns++;
            actor.heal(healAmount); // Apply 30 healing
            new Display().println(String.format("\nThe %s is healed for %d hp!", actor, healAmount));
        }


        if (healingTurns == 5) { // The player heals only for every tick
            actor.removeStatusEffect(this);
            healingTurns = 0; // Reset healing turns
        }
    }
}
