package game.actors;

import game.weapons.BareFist;

/**
 * The Spirit class represents a spirit enemy in the game.
 * It extends the Enemy class and has a fixed hit points of 100.
 * The Spirit is equipped with a default weapon, BareFist,
 * and is displayed on the game map with the character '&'.
 */
public class Spirit extends Enemy{
    private static final int spiritHP = 100;
    /**
     * Constructs a Spirit enemy with 100 hit points and set its
     * intrinsic weapon to BareFist
     */
    public Spirit(){
        super("Spirits",'&',spiritHP);
        this.setIntrinsicWeapon(new BareFist());
    }

}
