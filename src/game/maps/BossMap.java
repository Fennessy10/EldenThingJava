package game.maps;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.DivineBeast;
import game.weapons.Wind;
import game.grounds.*;
import java.util.List;

/**
 * BossMap is a specialized GameMap designed to handle boss encounters within the game.
 * This map is responsible for managing the spawn points of both a boss and a fighter actor
 * (an actor triggering the boss), waking the boss when it arrives and creating a return gate upon the boss's defeat.

 * The class facilitates customization of boss and partner spawn points, providing
 * a flexible framework for managing encounters in different boss maps within the game.
 */
public class BossMap extends GameMap {
    private Location spawnPoint;
    private Location bossSpawnPoint;
    private Location returnLocation; // The map and location that the returnGate will return the actor to
    private DivineBeast divineBeast;

    /**
     * Constructor for BossMap
     *
     * @param name The name of the map
     * @param groundFactory The factory to create ground types
     * @param lines The map layout
     * @param returnLocation The location to which the return gate will lead once the boss is defeated
     */
    public BossMap(String name, FancyGroundFactory groundFactory, List<String> lines, Location returnLocation) {
        super(name, groundFactory, lines);
        this.returnLocation = returnLocation;
    }

    /**
     * Set the spawn point for the fighter, i.e. the actor that will trigger the boss when it arrives
     *
     * @param spawnLocation The location where the dance partner will appear on the map
     */
    public void addFighterSpawnPoint(Location spawnLocation) {
        this.spawnPoint = spawnLocation;
    }

    /**
     * Set the spawn point for the boss.
     *
     * @param spawnLocation The location where the boss will spawn on the map.
     */
    public void addBeastSpawnPoint(Location spawnLocation) {
        this.bossSpawnPoint = spawnLocation;
    }
    /**
     * Creates a return gate at a specified location
     *
     * @param gateLocation The location where the return gate will be places
     */
    public void createReturnGate(Location gateLocation) {
        Gate returnGate = new Gate();

        // Setting the ground to the gate at the specified location
        gateLocation.setGround(returnGate);

        // Adding the returnLocation to the returnGate
        returnGate.addDestination(returnLocation);
    }

    /**
     * Wakes the Boss if there is an actor at the maps spawn point,
     * setting said actor as the boss' fighter, marking the beginning of
     * the fight against the Boss.
     */
    public void wakeTheBeast(){
        if (spawnPoint.containsAnActor()) { // Checking if there is an actor at the map's spawn point
            if (divineBeast == null ) { // Checking that a divineBeast does not already exist
                System.out.println("O Horn-deck'd, from higher sphere deliver'd.\n" +
                        "Take root inside the tower's sculptured keepers.\n" +
                        "And perch'd within, we beg of thee; rise.\n" +
                        "Dance and cavort, cleanse all that thou wilt.\n" +
                        "Cruelty, woe, and those who plague the tower.\n" +
                        "Cleanse away the strumpet's vile progeny.\n");
                Actor fighter = this.getActorAt(spawnPoint); // If there is, getting the actor
                divineBeast = new DivineBeast(this); // Waking up the divine beast. i.e. instantiating a
                divineBeast.setInitialDivinePower(new Wind()); // Setting the boss for this map's initial power to Wind
                bossSpawnPoint.addActor(divineBeast);
                divineBeast.newDancePartner(fighter); // Adding the actor on the spawn point as beast's dance partner
            }

        }
    }

    /**
     * Checks at every tick on the map if there is an actor on the spawn point of the map,
     * waking up the beast if there is.
     */
    @Override
    public void tick() {
        this.wakeTheBeast();
        super.tick();
    }
}
