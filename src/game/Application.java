package game;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.FancyGroundFactory;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.MoveActorAction;
import edu.monash.fit2099.engine.World;

/**
 * The main class for the zombie apocalypse game.
 *
 */
public class Application {

	public static void main(String[] args) {
		World world = new World(new Display());
		int x, y;

		FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Fence(), new Tree());
		
		List<String> map = Arrays.asList(
		"................................................................................",
		"................................................................................",
		"....................................##########..................................",
		"..........................###########........#####..............................",
		"............++...........##......................########.......................",
		"..............++++.......#..............................##......................",
		".............+++...+++...#...............................#......................",
		".........................##..............................##.....................",
		"..........................#...............................#.....................",
		".........................##...............................##....................",
		".........................#...............................##.....................",
		".........................###..............................##....................",
		"...........................####......................######.....................",
		"..............................#########.........####............................",
		"............+++.......................#.........#...............................",
		".............+++++....................#.........#...............................",
		"...............++........................................+++++..................",
		".............+++....................................++++++++....................",
		"............+++.......................................+++.......................",
		"................................................................................",
		".........................................................................++.....",
		"........................................................................++.++...",
		".........................................................................++++...",
		"..........................................................................++....",
		"................................................................................");
		GameMap gameMap = new GameMap(groundFactory, map );
		world.addGameMap(gameMap);
		
		Actor player = new Player("Player", '@', 100);
		world.addPlayer(player, gameMap.at(42, 15));
		
	    // Place some random humans
		String[] humans = {"Carlton", "May", "Vicente", "Andrea", "Wendy",
				"Elina", "Winter", "Clem", "Jacob", "Jaquelyn"};
		for (String name : humans) {
			do {
				x = (int) Math.floor(Math.random() * 20.0 + 30.0);
				y = (int) Math.floor(Math.random() * 7.0 + 5.0);
			} 
			while (gameMap.at(x, y).containsAnActor());
			if (Math.random() < 0.2) {
				gameMap.at(x,  y).addActor(new Farmer(name));
			}
			else {
				gameMap.at(x,  y).addActor(new Human(name));
			}
		}
		
		// place a simple weapon
		gameMap.at(74, 20).addItem(new Plank());
		//place test shotgun
		gameMap.at(43, 15).addItem(new Shotgun());
		gameMap.at(43,16).addItem(new ShotgunShells());
		
		 //FIXME: Add more zombies!
		gameMap.at(30, 20).addActor(new Zombie("Groan"));
		gameMap.at(30,  18).addActor(new Zombie("Boo"));
		gameMap.at(10,  4).addActor(new Zombie("Uuuurgh"));
		gameMap.at(50, 18).addActor(new Zombie("Mortalis"));
		gameMap.at(1, 10).addActor(new Zombie("Gaaaah"));
		gameMap.at(62, 12).addActor(new Zombie("Aaargh"));	
		
		
		FancyGroundFactory townGroundFactory = new FancyGroundFactory(new Dirt(), new HorizontalWall(), new Path(), 
				new Floor(), new Water(), new VerticalWall(), new Fence(), new Tree());
		List<String> townMap = Arrays.asList(
				"......................................................................................",
				"......................................................................................",
				"........==================================......=============================.........",
				"........|,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,|......|,,,,,,,|,,,,,,,,,,,,|,,,,,,|.........",
				"........|,,,,,,,|,,,,,,,,,,,,,,,,|,,,,,,,|......|,,,,,,,|,,,,,,,,,,,,|,,,,,,|.........",
				"........|,,,,,,,|,,,,,,,,,,,,,,,,|,,,,,,,|......|,,,,,,,|,,,,,,,,,,,,|,,,,,,|.........",
				"........================,,================......|,,,,,,,,,,,,,,,,,,,,,,,,,,,|.........",
				"........................::......................|========,,,,,,,,,,,,========.........",
				".........========..==...:.....++++...++.........|,,,,,,,,,,,,,,,,,,,,,,,,,,,|.........",
				".........|,,,,,,,,,,|...:.......++++++..........|,,,,,,,|,,,,,,,,,,,,|,,,,,,|.........",
				".........|,,,,,,,,,,|...::........+++.....+++...|,,,,,,,|,,,,,,,,,,,,|,,,,,,|.........",
				".........|,,,,,,,,,,|....:.....++++.....++++++..|,,,,,,,|,,,,,,,,,,,,|,,,,,,|.........",
				".........=====,,=====...::...............++.....=============,,==============.........",
				"..............::........::...................................::.......................",
				"...::::::::::::::::::::::::::::.::::::::.::::::::::::::::::::::::::::.::::::..........",
				"..............::........::............................................................",
				".........=====,,=====...::..............#######.##################.#..#######.........",
				".........|.,,..,,,..|...::..........................+++.....................#.........",
				".........|...,,,,,,,|...::.....++..+++............++++++....................#.........",
				".........=========,==...::......++++...............++........~~~~~~~~.......#.........",
				".........#..........#...::.......++.........+++...........~~~~.+++..~~~~~.............",
				".........#..........#...::................+++.+++++.....~~~~~~~.++++.~~~~~~...........",
				".........#..........#...::...............++++....++.........~~~~.++.~~~~~~............",
				".........#..........#...:.......+++........+++++.+++.......~~~~~~~~~~.......#.........",
				".........############....:.....++++++++.....................~~~~............#.........",
				"........................::........++........................................#.........",
				".................................#######..######################..###########.........",
				".........................:............................................................",
				"......................................................................................");
		GameMap townGameMap = new GameMap(townGroundFactory, townMap);
		world.addGameMap(townGameMap);

        Vehicle carToTown = new Vehicle("Car", '^');
        carToTown.addAction(new MoveActorAction(townGameMap.at(3,14), "to Town!"));
        gameMap.at(73,2).addItem(carToTown);
        
        //testcar
        gameMap.at(42,16).addItem(carToTown);
        
        Vehicle carToBack = new Vehicle("Car", '^');
        carToBack.addAction(new MoveActorAction(gameMap.at(72,2), "back!"));
        townGameMap.at(2,14).addItem(carToBack);
        
        //testing the map
        //townGameMap.at(72, 11).addItem(carToTown);
        //townGameMap.at(46, 3).addItem(carToTown);
        
        /*
        String[] townHumans = {"Sheriff Bill", "Policeman Jerry", "Firefighter Jenny", "Doctor Aaron", 
        		"Baker Andrea", "Normal Guy Bill", "Teacher Robin"};
		for (String name : townHumans) {
			do {
		        Random r = new Random();
		        x = r.nextInt(72-49) + 49;
				y = r.nextInt(11-3) + 3;
			} 
			while (townGameMap.at(x, y).containsAnActor());
			townGameMap.at(x,  y).addActor(new Human(name));
		}
		
		townGameMap.at(10, 23).addActor(new Farmer("Crazy Farmer Bill"));
        
        String[] townZombies = {"Ooga", "Booga", "Bruh", "Bruhh", "Bruhhh"};
		for (String name : townZombies) {
			do {
		        Random r = new Random();
		        x = r.nextInt(79-6) + 6;
				y = r.nextInt(26-6) + 6;;
			} 
			while (townGameMap.at(x, y).containsAnActor());
			townGameMap.at(x,  y).addActor(new Zombie(name));
		}
		*/
		
		world.run();
		boolean spawn = false;
		MamboMarie mambo = new MamboMarie();
		if(spawn) {
			if (mambo.getSpawnCounter()%10==0) {
				gameMap.removeActor(mambo);
				spawn = false;
			}
		}
		else {
			if(Math.random()<0.05) {
				gameMap.addActor(mambo, gameMap.at(0, 0));
				spawn = true;
			}
		}
	}
}
