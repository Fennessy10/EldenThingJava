package game.weapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.enums.Status;

/**
 * Lightning class represents a Divine Power used by a Divine Beast, capable of dealing damage.
 * This power strikes adjacent locations to the beast with lightning, doubling damage if it hits a wet surface, e.g. puddle.
 */
public class Lightning implements DivinePower {
    private int lightningDamage = 50;
    private double chanceForFrost = 0.4;
    private double chanceForWind = 0.8;

    /**
     * Executes the lightning attack, dealing damage to actors in adjacent locations.
     * If the ground at the target's location is WET, the lightning damage is double.
     *
     * @param beast The beast performing the attack
     * @param map The current GameMap
     * @param target The target of the attack
     * @return null, this action does not return an action
     */
    @Override
    public Action divineAttack(Actor beast, GameMap map, Actor target) {
        Location beastLocation = map.locationOf(beast);

        for (Exit exit : beastLocation.getExits()) {

            Location exitLocation = exit.getDestination();
            if (exitLocation.containsAnActor()) {

                Actor actorAtExit = exitLocation.getActor();
                if (exitLocation.getGround().hasCapability(Status.WET)) {
                    lightningDamage *= 2;
                }
                actorAtExit.hurt(lightningDamage);
            }
        }
        return null;
    }

    /**
     * Determines the next Divine Power to switch to, with a 40% chance of switching to Frost,
     * 40% chance to switch to Wind, and 20% chance to retain Lightning
     *
     * @return the new Divine Power instance
     */
    @Override
    public DivinePower getNextPower() {
        double chance = Math.random();
        if (chance < chanceForFrost) {
            return new Frost();
        } else if (chance < chanceForWind) {
            return new Wind();
        } else {
            return new Lightning();
        }
    }

    /**
     * Provides a description of the lightning attack
     * Indicates if the lightning strike has electrified the
     *
     * @return
     */
    @Override
    public String attackDescription() {
        return "The storm answers with a vengeful strike. Lightning surges forth, electrifying the ground itself";
    }

    /**
     * Provides an initialisation message when the Lightning power is activated.
     *
     * @return A string describing the awakening of the Lightning Power
     */
    @Override
    public String initialisePower() {
        return "Lightning courses through the earth and sky. Beware the fury of the tempest, for none shall escape its wrath!";
    }
}