package game.weaponarts;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.ActorState;
import game.enums.NewActorAttributes;

import java.util.ArrayList;
import java.util.List;

public class Memento extends WeaponArt {

    private final static int HEALTH_COST = 5;
    private final List<ActorState> savedStates = new ArrayList<>();

    public Memento() {
        super(HEALTH_COST);
    }

    @Override
    public String activate(Actor player, GameMap map) {
        if (player.getAttribute(BaseActorAttributes.HEALTH) <= HEALTH_COST) {
            saveState(player);
            return String.format("%s's health is too low to use Memento.", player);
        }

        player.hurt(HEALTH_COST);

        if (Math.random() < 0.5) {
            return restoreState(player);
        } else
            saveState(player);
            return String.format("%s saves their current state using Memento.", player);
    }

    private void saveState(Actor player) {
        savedStates.add(new ActorState(player.getAttribute(BaseActorAttributes.HEALTH), player.getAttribute(BaseActorAttributes.MANA) , player.getAttribute(NewActorAttributes.STRENGTH)));
    }

    private String restoreState(Actor player) {
        if (!savedStates.isEmpty()) {
            ActorState lastState = savedStates.remove(savedStates.size() - 1);
            player.modifyAttribute(BaseActorAttributes.HEALTH,ActorAttributeOperations.UPDATE,lastState.getHealth());
            player.modifyAttribute(BaseActorAttributes.MANA,ActorAttributeOperations.UPDATE,lastState.getMana());
            player.modifyAttribute(NewActorAttributes.STRENGTH,ActorAttributeOperations.UPDATE,lastState.getStrength());
            return String.format("%s restores their last saved state using Memento.", player);
        } else {
            return String.format("No previous state to restore.");
        }
    }
}
