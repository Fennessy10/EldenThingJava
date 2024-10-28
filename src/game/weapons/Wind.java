package game.weapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.weapons.DivinePower;
import game.weapons.Frost;
import game.weapons.Lightning;

import java.util.ArrayList;
import java.util.Random;

/**
 * Represents the Wind divine power for a boss character in the game.
 * The wind power allows the boss to move the target to a random adjacent location if the target is within its immediate surroundings
 * The power has a chance to switch to either Frost or Lightning power upon attack.
 */
public class Wind implements DivinePower {
    private double chanceForFrost = 0.3;
    private boolean targetMoved;

    /**
     * Executes the Wind divine power attack, attempting to move the target to a tandom adjacent location
     * if the target is within the immediate surroundings of the boss.
     *
     * @param boss The beast performing the attack
     * @param map The current GameMap
     * @param target The target of the attack
     * @return An Action that moves the target to a random adjacent location if conditions are met, DoNothingAction otherwise
     */
    @Override
    public Action divineAttack(Actor boss, GameMap map, Actor target) {
        Location targetLocation = map.locationOf(target);
        Location bossLocation = map.locationOf(boss);

        if (Math.abs(targetLocation.x() - bossLocation.x()) > 1 || Math.abs(targetLocation.y() - bossLocation.y()) > 1) {
            targetMoved = false;
            return null;
        }
        // Find all adjacent locations that are free (not occupied by another actor)
        ArrayList<Location> adjacentLocations = new ArrayList<>();

        // Collect valid adjacent locations
        for (int x = targetLocation.x() - 1; x <= targetLocation.x() + 1; x++) {
            for (int y = targetLocation.y() - 1; y <= targetLocation.y() + 1; y++) {
                // Check if the location is within map bounds and is not the current location
                if (map.isAnActorAt(map.at(x, y)) || (x == targetLocation.x() && y == targetLocation.y())) {
                    continue;  // Skip occupied or the current location of the Tarnished
                }
                adjacentLocations.add(map.at(x, y));  // Add valid adjacent locations
            }
        }

        // If there are no valid adjacent locations, return DoNothingAction
        if (adjacentLocations.isEmpty()) {
            targetMoved = false;
            return null;  // Tarnished cannot be moved
        }

        // Randomly select an adjacent location
        Random random = new Random();
        Location randomAdjacentLocation = adjacentLocations.get(random.nextInt(adjacentLocations.size()));
        targetMoved = true;

        // Return the MoveActorAction to move the Tarnished to the randomly selected location
        return new MoveActorAction(randomAdjacentLocation, "a random adjacent location");
    }

    /**
     * Determines the next divine power for the boss with a 30% chance to switch to Frost and a 70% chance to switch
     * to Lightning
     *
     * @return a new DivinePower instance of either Frost or Lightning, based on the chance
     */
    @Override
    public DivinePower getNextPower() {
        //30% chance to switch to Frost, 70% to switch to Lightning
        double chance = Math.random();
        if (chance < chanceForFrost) {
            return new Frost();
        } else {
            return new Lightning();
        }
    }

    /**
     * Provides a description of the attack based on whether the target was moved.
     *
     * @return A string describing the effect of the Wind attack if the target is moved, null otherwise
     */
    @Override
    public String attackDescription() {
        if (targetMoved) {
            return "The winds howl and rage, lifting you like a lead in the storm.";
        }
        return null;
    }

    /**
     * Provides an initialisation message when the Wind power is activated.
     *
     * @return A string describing the awakening of the Wind Power
     */
    @Override
    public String initialisePower() {
        return "The winds howl like a forgotten storm, swirling with untamed power. The sky shifts, and the tempest awakes.";
    }


}
