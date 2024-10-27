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
import game.behaviours.FollowBehaviour;
import game.items.RemembranceOfDancingLion;
import game.enums.Status;
import game.weapons.SharpTeeth;

/**
 * Class representing the Divine Beast Dancing Lion, a powerful enemy in the game.
 * The Divine Beast has special divine powers that cycle periodically and a unique interaction with its opponent. i.e. its dancePartner
 */
public class DivineBeast extends Enemy {
    private DivinePower currentPower;
    private Actor dancePartner;
    private BossMap stageFrontMap;
    /**
     * Constructor
     * Initializes Divine Beast with a name, display character, hit points and default behaviours.
     * By default, as it extends from enemy, it has an attack behaviour with high priority and a wander behaviour priority with a low priority.
     *
     */
    public DivineBeast(BossMap stageFrontMap) {
        super("Divine Beast Dancing Lion", 'S', 10000);
        this.stageFrontMap = stageFrontMap;
        this.setIntrinsicWeapon(new SharpTeeth());
    }

    /**
     * Assigns a new dance partner to the Divine Beast, which will be its target during attacks.
     *
     * @param dancePartner The actor that the Divine Beast will interact with in combat.
     */
    public void newDancePartner(Actor dancePartner) {
        this.dancePartner = dancePartner;
    }

    /**
     * Sets the initial divine power for the Divine Beast to start with during its fight
     *
     * @param divinePower the power to set as current for the Divine Beast
     */
    public void setInitialDivinePower(DivinePower divinePower) {
        this.currentPower = divinePower;
    }

    /**
     * Gets the current divine power of the Divine Beast
     *
     * @return the current divine pwoer
     */
    public DivinePower getDivinePower() {
        return currentPower;
    }

    /**
     * The Divine Beast's playTurn method, which detemines its actions each turn.
     * The DivineBeast may cycle its divine pwoers and attack its dance partner if present
     * If no partner is present, it follows other defined behaviours (e.g. wander)
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the selected action for this turn
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {

        DivinePower nextPower = currentPower.getNextPower();
         if (nextPower != currentPower) {
             System.out.println(nextPower.initialisePower());
             currentPower = nextPower;
         }

        if (dancePartner != null) {
            return new DivineAttackAction(this, currentPower, dancePartner);
        }
        for (Behaviour behaviour : super.behaviours.values()) {
            Action action = behaviour.getAction(this, map);
            if (action != null)
                return action;
        }

        return new DoNothingAction();
    }

    /**
     * Handles the actions upon the Divine Beast's degeat, including awarding an item and creating
     * a return gate on the map, at the location where the DivineBeast died.
     *
     * @param actor the perpetrator
     * @param map   where the actor fell unconscious
     * @return A message dictating the defeat of the Divine Beast
     */
    @Override
    public String unconscious(Actor actor, GameMap map) {

        //Creating and adding Remembrance
        RemembranceOfDancingLion remembrance = new RemembranceOfDancingLion();
        actor.addItemToInventory(remembrance);

        Location beastLocation = map.locationOf(this);
        stageFrontMap.createReturnGate(beastLocation);
        return "The boss has been defeated! You can now return back to Belurat Tower Settlement!";
    }

    /**
     * Determines the actions allowed the Divine Beast when interacting with actors within its surroundings.
     * Namely, the beast can attack if the actor is hostile and follow them if the actor has the FOLLOW status
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return a list of allowable actions
     */
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
