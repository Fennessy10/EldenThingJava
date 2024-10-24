package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class Wind implements DivinePower{
    private double chanceForFrost = 0.3;
    @Override
    public Action divineAttack(Actor beast, GameMap map, Actor target) {
        Location targetLocation = map.locationOf(target);

        // Find all adjacent locations that are free (not occupied by another actor)
        ArrayList<Location> adjacentLocations = new ArrayList<>();
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
            return new DoNothingAction();  // Tarnished cannot be moved
        }

        // Randomly select an adjacent location
        Random random = new Random();
        Location randomAdjacentLocation = adjacentLocations.get(random.nextInt(adjacentLocations.size()));

        // Return the MoveActorAction to move the Tarnished to the randomly selected location
        return new MoveActorAction(randomAdjacentLocation, "a random adjacent location");
    }

    @Override
    public DivinePower getNextPower(DivinePower currentPower) {
        //30% chance to switch to Frost, 70% to switch to Lightning
        double chance = Math.random();
        if (chance < chanceForFrost) {
            return new Frost();
        } else {
            return new Lightning();
        }
    }
}
