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
 * Modified by:
 */
public class Player extends Actor {
    private BurningStatusEffect burningEffect;

    /**
     * Constructor.
     *
     * @param name        Name to call the player in the UI
     * @param displayChar Character to represent the player in the UI
     * @param hitPoints   Player's starting number of hitpoints
     */
    public Player(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        this.addCapability(Status.HOSTILE_TO_ENEMY);
        this.setIntrinsicWeapon(new BareFist());

        BaseActorAttribute manaAttribute = new BaseActorAttribute(100);
        this.addAttribute(BaseActorAttributes.MANA, manaAttribute);

        BaseActorAttribute strengthAttribute = new BaseActorAttribute(5);
        this.addAttribute(NewActorAttributes.STRENGTH, strengthAttribute);

        // Initialize the burning status effect
        burningEffect = new BurningStatusEffect();
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
        if (getAttribute(BaseActorAttributes.HEALTH) == 0) {
            display.println(FancyMessage.YOU_DIED);
            return null;
        } else {
            display.println("Strength: " + getAttributeMaximum(NewActorAttributes.STRENGTH));
            display.println("Mana: " + getAttribute(BaseActorAttributes.MANA) + "/" + getAttributeMaximum(BaseActorAttributes.MANA));

            // Check for BURNING status
            if (this.hasCapability(Status.BURNING)) {
                burningEffect.tick(map.locationOf(this), this);
            }

            // Handle multi-turn Actions
            if (lastAction.getNextAction() != null)
                return lastAction.getNextAction();

            // Return/print the console menu
            Menu menu = new Menu(actions);
            return menu.showMenu(this, display); // Ensure this returns a valid Action
        }
    }
}
