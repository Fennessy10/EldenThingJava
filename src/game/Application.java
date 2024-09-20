package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.positions.World;
import game.actors.FurnaceGolem;
import game.actors.Player;
import game.displays.FancyMessage;
import game.grounds.Dirt;
import game.grounds.Floor;
import game.grounds.Puddle;
import game.grounds.Wall;
import game.consumables.FlaskOfHealing;
import game.consumables.FlaskOfRejuvenation;
import game.consumables.ShadowTreeFragment;
import game.weapons.GreatKnife;import game.weapons.LifeSteal;
import game.weapons.Quickstep;
import game.weapons.ShortSword;

/**
 * The main class to start the game.
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 *
 */
public class Application {

    public static void main(String[] args) {

        World world = new World(new Display());

        FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(),
                new Wall(), new Floor(), new Puddle(), new PoisonSwamp());

        List<String> map = Arrays.asList(
                "..........~~~~~~~...~~~~~~~......~...........",
                "~..........~~~~~....~~~~~~...................",
                "~~.........~~~~.....~~~~~~...................",
                "~~~..#####..~~.....~~~~~~~...................",
                "~~~..#___#........~~~~~~~~~..................",
                "~~~..#___#.......~~~~~~.~~~..................",
                "~~~..##_##......~~~~~~.......................",
                "~~~~...........~~~~~~~...........~~..........",
                "~~~~~.........~~~~~~~~.......~~~~~~~.........",
                "~~~~~~.......~~~~~~~~~~.....~~~~~~~~.........");

        GameMap gameMap = new GameMap("Gravesite Plain", groundFactory, map);
        world.addGameMap(gameMap);

        // Defining layout of the "Belurat, Tower Settlement" map
        List<String> beluratTower = Arrays.asList(
                "###########........................##########",
                "#____#____#......................._____#____#",
                "#____#_.._#.#...~~~.......~~~....#____#____##",
                "###_~~____###...~~~..~~~..~~~...####______###",
                "###...____###..~~~~..~~~~..~~~...######_____#",
                "##~~###..####..~~~...~~~.....~~~..####..#####",
                "##__.....####..~~~.~~~~~..~~~....#####____###",
                "###..##..##.#..~~..~~~~~..~~~~....####~..####",
                "#....__..__.#..~~..~~~~~~..~~....__~~~~######",
                "###########....................##############"
        );

        // Create the Belurat, Tower Settlement" map
        GameMap beluratTowerMap = new GameMap("Belurat, Tower Settlement", groundFactory, beluratTower);
        // Adding the map to the world
        world.addGameMap(beluratTowerMap);

        // Defining the layout of the "Belurat Sewers" map
        List<String> beluratSewers = Arrays.asList(
                "##++++++#####++++++++~~~~~++++",
                "##+++++++###+++++++++~~~~~++++",
                "##++++++++++++++++++~~~~~~~++~",
                "###+++++++++++++++.~~~~~~~~.~~",
                "~~~~~.+++++~~~++++~~~~~~~~~..~",
                "~~~~~~~~~~~~~~~++++~~~~+++~...",
                "~~~~+~~~~~~~~~~+++++~~~~~~~###",
                "+~~~~++####~~~~~++++##.~++~###",
                "++~~+++#####~~~~~++###++~~~###",
                "+~~++++######~~~~++###++~~~###"
        );

        // Creating the "Belurat Sewers" game map
        GameMap beluratSewersMap = new GameMap("Belurat Sewers", groundFactory, beluratSewers);
        // Adding map to world
        world.addGameMap(beluratSewersMap);


        // Create a gate leading to "Belurat, Tower Settlement"
        Gate towerGate = new Gate();
        towerGate.addSampleAction(new MoveActorAction(beluratTowerMap.at(11,0), "to Belurat, Tower Settlement"));

        // Create a gate leading to "Belurat Sewers"
        Gate sewerGate = new Gate();
        sewerGate.addSampleAction(new MoveActorAction(beluratSewersMap.at(4,5), "to Belurat Sewers"));

        // Add the gates to "Gravesite Plain" map at coordinates (0,0)
        gameMap.at(0, 0).addItem(towerGate);
        gameMap.at(0, 0).addItem(sewerGate);

        // BEHOLD, ELDEN THING!
        for (String line : FancyMessage.TITLE.split("\n")) {
            new Display().println(line);
            try {
                Thread.sleep(200);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        Player player = new Player("Tarnished", '@', 150, 10, 100);
        world.addPlayer(player, gameMap.at(7, 4));

        // Place the ShortSword at coordinates (x=7, y=8)
        ShortSword shortSword = new ShortSword(new Quickstep());
        gameMap.at(7, 8).addItem(shortSword);

        // Place the ShortSword at coordinates (x=5, y=8)
        GreatKnife greatKnife = new GreatKnife(new Quickstep());
        gameMap.at(5, 8).addItem(greatKnife);

        GreatKnife greatKnife2 = new GreatKnife(new LifeSteal());
        gameMap.at(15, 6).addItem(greatKnife2);

        FlaskOfHealing flaskOfHealing = new FlaskOfHealing();
        gameMap.at(10, 9).addItem(flaskOfHealing);

        FlaskOfRejuvenation flaskOfRejuvenation = new FlaskOfRejuvenation();
        gameMap.at(14, 9).addItem(flaskOfRejuvenation);

        ShadowTreeFragment shadowTreeFragment1 = new ShadowTreeFragment();
        gameMap.at(20, 8).addItem(shadowTreeFragment1);

        ShadowTreeFragment shadowTreeFragment2 = new ShadowTreeFragment();
        gameMap.at(18, 4).addItem(shadowTreeFragment2);

        ShadowTreeFragment shadowTreeFragment3 = new ShadowTreeFragment();
        gameMap.at(16, 2).addItem(shadowTreeFragment3);

        ShadowTreeFragment shadowTreeFragment4 = new ShadowTreeFragment();
        gameMap.at(14, 5).addItem(shadowTreeFragment4);

        ShadowTreeFragment shadowTreeFragment5 = new ShadowTreeFragment();
        gameMap.at(22, 3).addItem(shadowTreeFragment5);

        gameMap.at(42, 4).addActor(new FurnaceGolem());


        world.run();

    }
}