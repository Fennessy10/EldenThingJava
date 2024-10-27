package game.actors;

public class ActorState {
    private final int health;
    private final int mana;
    private final int strength;

    public ActorState(int health, int mana, int strength) {
        this.health = health;
        this.mana = mana;
        this.strength = strength;
    }

    public int getHealth() {
        return health;
    }

    public int getMana() {
        return mana;
    }

    public int getStrength() {
        return strength;
    }
}
