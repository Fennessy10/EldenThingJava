package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.AttackAction;
import game.behaviours.AttackBehaviour;
import game.behaviours.WanderBehaviour;
import game.enums.Status;

import java.util.Map;
import java.util.TreeMap;

public abstract class Enemy extends Actor {
    private final Map<Integer, Behaviour> behaviours = new TreeMap<>();

    /**
     * Constructor
     * Initializes enemy with a name, display character, hit points, and default behaviours.
     * By default, an enemy has an attack behaviour with high priority
     * and a wander behaviour with low priority.
     *
     * @param name        The name of the enemy
     * @param displayChar The character that represents the enemy on the map
     * @param hitPoints   The initial hit points (health) of the enemy
     */
    public Enemy(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        this.addCapability(Status.ENEMY);
        this.behaviours.put(1, new AttackBehaviour());
        this.behaviours.put(999, new WanderBehaviour());
    }

    /**
     * Adds or updates a behaviour for the enemy with a specific priority.
     * Lower values indicate higher precedence in behaviour execution.
     *
     * @param priority  The priority of the behaviour in the TreeMap
     * @param behaviour The behaviour to add or update in the map of behaviours
     */
    public void behavioursPut(int priority, Behaviour behaviour) {
        this.behaviours.put(priority, behaviour);
    }

    /**
     * Select and return an action to perform on the current turn.
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn.
     *                   Can do interesting things with Action.getNextAction().
     * @param map        The map containing the Actor
     * @param display    The I/O object to which messages may be written
     * @return The Action to be performed
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        for (Behaviour behaviour : behaviours.values()) {
            Action action = behaviour.getAction(this, map);
            if (action != null) {
                return action;
            }
        }
        return new DoNothingAction();
    }

    /**
     * Returns a collection of Actions that the otherActor can do to this enemy.
     * By default, an actor hostile to the enemy can attack it.
     *
     * @param otherActor The Actor that might perform the attack
     * @param direction  The direction of the other Actor
     * @param map        The current GameMap
     * @return A collection of Actions.
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();

        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            actions.add(new AttackAction(this, direction));
        }
        return actions;
    }
}

