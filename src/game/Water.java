package game;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.ConsumeAction;
import game.actors.Scarab;
import game.consumables.Consumable;
import game.enums.Ability;
import game.enums.Status;
import java.util.Random;

/**
 * Represents a water ground in the game world, which actors can consume to restore mana.
 * Water provides fire resistance and has a WET status.
 */
public abstract class Water extends Ground implements Consumable {

    /**
     * Constructor.
     * Initialises the water terrain with a specific display character, ad name, and grants it abilities of fire resistance
     * and wet status.
     *
     * @param displayChar character to display for this type of terrain
     * @param name the name of this terrain type
     */
    public Water(char displayChar, String name) {
        super(displayChar, name);
        this.addCapability(Ability.FIRE_RESISTANT);
        this.addCapability(Status.WET);
    }

    /**
     * Provides specific actions to actors standing on the water
     * Allows actors on this location to consume water.
     *
     * @param actor the Actor standing on the Puddle.
     * @param location the current location of the Puddle.
     * @param direction the direction from which the actor is coming (ignored here).
     * @return ActionList containing all actions available to this actor.
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = new ActionList();

        if (location.containsAnActor() && actor.equals(location.getActor())) {
            // Add the ConsumeAction if the actor is directly on the puddle.
            actions.add(new ConsumeAction(this));
        }
        return actions;
    }

    /**
     * Executes the consumption of water.
     * This method should be implemented by subclasses to define specific consume effects
     *
     * @param actor The actor consuming the water.
     * @param map The map where the action is taking place.
     * @return A descriptive string of the action.
     */
    @Override
    public abstract String consume(Actor actor, GameMap map);
}