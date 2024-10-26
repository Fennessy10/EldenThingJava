package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.SuspiciousTrader;
import game.effects.HealingStatusEffect;
import game.enums.ItemCapability;
import game.weapons.DivineBeastHead;
import game.weapons.FurnaceEngine;

import java.util.ArrayList;
import java.util.List;

public class TradeAction extends Action{

    /**
     * The trader
     */
    private SuspiciousTrader trader;

    /**
     * Constructor with trader
     * @param trader Suspicious Trader
     */
    public TradeAction(SuspiciousTrader trader) {
        this.trader = trader;
    }


    /**
     *
     * @param actor actor which trade with
     * @param map current game map
     * @return A string description of the trade action
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        List<Item> inventory  = new ArrayList<>(actor.getItemInventory());
        boolean hasTraded = false;
        String result = "";
        for (Item item : inventory) {
            if (item.hasCapability(ItemCapability.REMEMBRANCE_OF_DANCING_LION)){
                actor.removeItemFromInventory(item); //remove remembrance

                DivineBeastHead divineBeastHead = new DivineBeastHead();
                actor.addItemToInventory(divineBeastHead);//add to player inventory

                actor.modifyAttributeMaximum(BaseActorAttributes.HEALTH, ActorAttributeOperations.INCREASE,50);
                actor.modifyAttributeMaximum(BaseActorAttributes.MANA, ActorAttributeOperations.INCREASE,100);
                //increase health and mana

                result += "You trade " + item + "with" + trader + "and got Divine Beast Head\n";
                result += "Your maximum health increased by 50 and mana increased by 100\n";

                hasTraded = true;
            }
            else if (item.hasCapability(ItemCapability.REMEMBRANCE_OF_FURNACE_GOLEM)){
                actor.removeItemFromInventory(item); //remove remembrance

                FurnaceEngine furnaceEngine = new FurnaceEngine();
                actor.addItemToInventory(furnaceEngine);

                actor.addStatusEffect(new HealingStatusEffect(actor, /*duration*/ 5, /*healAmount*/ 30, /*recurrent*/ true));

                result += "You trade " + item + "with" + trader + "and got Furnace Engine\n";

                hasTraded = true;
            }
        }
        if (!hasTraded) {
            return trader  + "You have nothing to trade!";
        }
        return result;
    }


    /**
     * Description of the action to display on the menu
     *
     * @param actor The actor performing the action
     * @return A string description of the attack action
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " trade " + trader;
    }
}
