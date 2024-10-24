package game.effects;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.StatusEffect;
import edu.monash.fit2099.engine.positions.Location;

public class WetStatusEffect extends StatusEffect {
    private int wetTurns = 0;
    private int maxWetTurns = 1;

    public WetStatusEffect() {
        super("wet");
    }

    @Override
    public void tick(Location location, Actor actor) {
        wetTurns++;
        if (wetTurns == maxWetTurns) {
            actor.removeStatusEffect(this);
            wetTurns = 0;
        }
    }
}
