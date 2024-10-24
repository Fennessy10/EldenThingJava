package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.enums.Ability;

public class Lightning implements DivinePower {
    private int lightningDamage = 50;
    private double chanceForFrost = 0.4;
    private double chanceForWind = 0.8;

    @Override
    public Action divineAttack(Actor beast, GameMap map, Actor target) {
        Location beastLocation = map.locationOf(beast);

        for (Exit exit : beastLocation.getExits()) {

            Location exitLocation = exit.getDestination();
            if (exitLocation.containsAnActor()) {

                Actor actorAtExit = exitLocation.getActor();
                if (actorAtExit.hasCapability(Ability.WET)) {
                    lightningDamage *= 2;
                }
                actorAtExit.hurt(lightningDamage);
            }
        }
        return null;
    }

    @Override
    public DivinePower getNextPower(DivinePower currentPower) {
        double chance = Math.random();
        if (chance < chanceForFrost) {
            return new Frost();
        } else if (chance < chanceForWind) {
            return new Wind();
        } else {
            return new Lightning();
        }
    }
}