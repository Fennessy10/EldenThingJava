package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.World;
import game.actors.FurnaceGolem;
import game.actors.Player;
import game.actors.SuspiciousTrader;
import game.actors.WeatherWizard;
import game.grounds.Gate;
import game.displays.FancyMessage;
import game.grounds.*;
import game.consumables.FlaskOfHealing;
import game.consumables.FlaskOfRejuvenation;
import game.consumables.ShadowTreeFragment;
import game.maps.BossMap;
import game.spawners.ManFlySpawner;
import game.spawners.SpiritSpawner;
import game.weaponarts.LifeSteal;
import game.weaponarts.Memento;
import game.weaponarts.QuickStep;
import game.weapons.*;
import game.weather.Atmosphere;

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

        // Defining the layout of the "Belurat Sewers" map
        List<String> stageFront = Arrays.asList(
                "#################",
                "#~~~..........~~#",
                "#~~~...........~#",
                "#~~.............#",
                "#............~~~#",
                "#..........~~~~~#",
                "#######...#######"
        );
        // Creating the "Stage Front" boss map
        BossMap stageFrontMap = new BossMap("StageFrontMap", groundFactory, stageFront, beluratTowerMap.at(25,1));
        stageFrontMap.addBeastSpawnPoint(stageFrontMap.at(8, 1));
        stageFrontMap.addFighterSpawnPoint(stageFrontMap.at(8, 6));
        // Adding map to world
        world.addGameMap(stageFrontMap);



        // Creating a gate for Gravestite Plains to allow actors travel between maps.
        Gate gravestiteGate = new Gate();

        Atmosphere atmosphere = new Atmosphere();
        gameMap.at(42, 8).addActor(new WeatherWizard(atmosphere));

        // Adding possible destination locations actor can travel to, to the Gate
        gravestiteGate.addDestination(beluratTowerMap.at(11,0));
        gravestiteGate.addDestination(beluratSewersMap.at(4,5));

        // Placing the Gate on Gravestite Plains
        gameMap.at(7,6).setGround(gravestiteGate);

        Gate beluratTowerGate = new Gate();

        beluratTowerGate.addDestination(gameMap.at(7,6));

        beluratTowerMap.at(11,0).setGround(beluratTowerGate);

        Gate beluratSewerGate = new Gate();

        beluratSewerGate.addDestination(gameMap.at(7,6));

        beluratSewersMap.at(4, 5).setGround(beluratSewerGate);


        // BEHOLD, ELDEN THING!
        for (String line : FancyMessage.TITLE.split("\n")) {
            new Display().println(line);
            try {
                Thread.sleep(200);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        Player player = new Player("Tarnished", '@', 150, 5, 100);
        world.addPlayer(player, gameMap.at(7, 4));

        // Place the ShortSword at coordinates (x=7, y=8)
        ShortSword shortSword = new ShortSword(new QuickStep());
        gameMap.at(7, 8).addItem(shortSword);

        // Place the ShortSword at coordinates (x=5, y=8)
        GreatKnife greatKnife = new GreatKnife(new QuickStep());
        gameMap.at(5, 8).addItem(greatKnife);

        GreatKnife greatKnife2 = new GreatKnife(new LifeSteal());
        gameMap.at(15, 6).addItem(greatKnife2);

        GreatKnife greatKnife3 = new GreatKnife(new Memento());
        gameMap.at(6, 8).addItem(greatKnife3);

        TallAxe tallAxe = new TallAxe(new LifeSteal(), atmosphere);
        gameMap.at(21, 8).addItem(tallAxe);

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

        beluratTowerMap.at(26,6).setGround(new Graveyard(new SpiritSpawner()));

        beluratSewersMap.at(19,4).setGround(new Graveyard(new ManFlySpawner()));

        SuspiciousTrader suspiciousTrader = new SuspiciousTrader();
        gameMap.at(7, 3).addActor(suspiciousTrader);


        world.run();

    }
}