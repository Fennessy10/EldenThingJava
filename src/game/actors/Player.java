package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttribute;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.displays.Menu;
import game.enums.NewActorAttributes;
import game.enums.Status;
import game.displays.FancyMessage;
import game.weapons.BareFist;
import game.actors.BurningStatusEffect;

/**
 * Class representing the Player.
 * Created by:
 * @author Adrian Kristanto
 * Modified by: Brianna Vaughan
 */
public class Player extends Actor {
    /**
     * Constructor.
     *
     * @param name        Name to call the player in the UI
     * @param displayChar Character to represent the player in the UI
     * @param hitPoints   Player's starting number of hitpoints
     */
    public Player(String name, char displayChar, int hitPoints, int strength, int mana) {
        super(name, displayChar, hitPoints);
        this.addCapability(Status.HOSTILE_TO_ENEMY);
        this.addCapability(Status.FOLLOW);
        this.setIntrinsicWeapon(new BareFist());
        this.addAttribute(NewActorAttributes.STRENGTH, new BaseActorAttribute(strength));
        this.addAttribute(BaseActorAttributes.MANA, new BaseActorAttribute(mana));

    }

    /**
     * Represents the player's turn in the game.
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return The next action to be performed by the player
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        // Handle multi-turn Actions
        if (lastAction.getNextAction() != null)
            return lastAction.getNextAction();

        // show strength + mana in menu console
        display.println("Strength: " + getAttributeMaximum(NewActorAttributes.STRENGTH));
        display.println("Mana: " + getAttribute(BaseActorAttributes.MANA) + "/" + getAttributeMaximum(BaseActorAttributes.MANA));

        // Return/print the console menu
        Menu menu = new Menu(actions);
        return menu.showMenu(this, display); // Ensure this returns a valid Action

    }

    /**
     * Handles the behaviour when an actor becomes unconscious (i.e. when their hitpoints >= 0)
     * In this implementation, it displays a "YOU DIED" message line by line, with a delay between each line.
     * Afterward, it calls the superclass' unconscious method to handle any additional logic.
     *
     * @param actor the perpetrator
     * @param map   where the actor fell unconscious
     * @return a String representing the result of the unconscious state
     */
    @Override
    public String unconscious(Actor actor, GameMap map) {
        for (String line : FancyMessage.YOU_DIED.split("\n")) {
            new Display().println(line);
            try {
                Thread.sleep(200);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        return super.unconscious(actor, map);
    }
}

