package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.AttackAction;
import game.actors.Enemy;
import game.behaviours.AttackBehaviour;
import game.behaviours.FollowBehaviour;
import game.behaviours.WanderBehaviour;
import game.enums.Status;
import game.grounds.Gate;

import java.util.HashMap;
import java.util.Map;

public class DivineBeast extends Enemy {
    private DivinePower currentPower;
    private Actor dancePartner = null;
    /**
     * Constructor
     * Initializes enemy with a name, display character, hit points and default behaviours.
     * By default, an enemy has an attack behaviour with high priority and a wander behaviour priority with a low priority.
     *
     */
    public DivineBeast() {
        super("Divine Beast Dancing Lion", 'S', 10000);
        this.currentPower = new Wind();

    }

    public void newDancePartner(Actor dancePartner) {
        this.dancePartner = dancePartner;
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {

        if (dancePartner != null) {
            Action divineAttackAction = currentPower.divineAttack(this, map, dancePartner);
            if (divineAttackAction != null) {
                return divineAttackAction;
            }
        }
        for (Behaviour behaviour : super.behaviours.values()) {
            Action action = behaviour.getAction(this, map);
            if (action != null)
                return action;
        }

        return new DoNothingAction();
    }

    @Override
    public String unconscious(Actor actor, GameMap map) {
        Location escapeGateLocation = map.locationOf(dancePartner);
        int x_coord = escapeGateLocation.x();
        int y_coord = escapeGateLocation.y();

        Gate returnGate = new Gate();
        map.at(x_coord, y_coord).setGround(returnGate);
        return super.unconscious(actor, map);
    }

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();

        dancePartner = otherActor;

        if (otherActor.hasCapability(Status.FOLLOW)) {
            super.behavioursPut(500, new FollowBehaviour(otherActor));
        }
        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            actions.add(new AttackAction(this, direction));
        }
        return actions;

    }
}
