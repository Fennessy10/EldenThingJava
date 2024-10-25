package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.TradeAction;
import game.enums.Status;

/**
 * Determine a new actor class for Suspicious Trader,
 * which can trade with actor, not be attacked by other actor and stay on the land of shadow
 */
public class SuspiciousTrader extends Actor {
    /**
     * Constructor of SuspiciousTrader
     * with 50 HP(not assigned i set it up randomly.) char as 'ඞ'
     */
    public SuspiciousTrader() {
        super("Suspicious Trader ", 'ඞ', 50);
        this.addCapability(Status.FRIENDLY);
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction(); //trader will just stay former place
    }

    /**
     * Determines what actions other actors can perform on the SuspiciousTrader
     *
     * implement if trade with actor
     *
     * @param otherActor the Actor that might be trade with
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return An ActionList of available actions an actor can perform on the SuspiciousTrader
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        //if actor is player, can trade with him
        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            //actions.add(new TradeAction(this,trader));
        }
        return actions;
    }
}
