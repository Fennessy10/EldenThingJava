package game.actors;

import game.weapons.BareFist;

public class Spirit extends Enemy{
    public Spirit(){
        super("Spirits",'&',100);
        this.setIntrinsicWeapon(new BareFist());
    }

}
