package game;

import java.util.Arrays;
import java.util.List;

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
		World world = new NewWorld(new Display());
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
		CompoundMap gameMap = new CompoundMap(groundFactory, map );
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
		
		 //FIXME: Add more zombies!
		gameMap.at(30, 20).addActor(new Zombie("Groan"));
		gameMap.at(30,  18).addActor(new Zombie("Boo"));
		gameMap.at(10,  4).addActor(new Zombie("Uuuurgh"));
		gameMap.at(50, 18).addActor(new Zombie("Mortalis"));
		gameMap.at(1, 10).addActor(new Zombie("Gaaaah"));
		gameMap.at(62, 12).addActor(new Zombie("Aaargh"));	
		
		
		FancyGroundFactory townGroundFactory = new FancyGroundFactory(new Dirt(), new HorizontalWall(), new Path(), 
				new Floor(), new VerticalWall(), new Fence(), new Tree());
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
				".........=====,,=====...::...............++.....=============##==============.........",
				"..............::........::...................................::.......................",
				"..#::::::::::::::::::::::::::::.::::::::.::::::::::::::::::::::::::::.::::::..........",
				"..............::........::............................................................",
				".........=====,,=====...::..............#######.##################.#..#######.........",
				".........|.,,..,,,..|...::..........................+++.....................#.........",
				".........|...,,,,,,,|...::.....++..+++............++++++....................#.........",
				".........=========,==...::......++++...............++........++++++++.......#.........",
				".........#..........#...::.......++.........+++...........++++.+++..+++++.............",
				".........#..........#...::................+++.+++++.....+++++++.++++.++++++...........",
				".........#..........#...::...............++++....++.........++++.++.++++++............",
				".........#..........#...:.......+++........+++++.+++.......++++++++++.......#.........",
				".........############....:.....++++++++.....................++++............#.........",
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
        //gameMap.at(42,16).addItem(carToTown);
        
        Vehicle carToBack = new Vehicle("Car", '^');
        carToBack.addAction(new MoveActorAction(gameMap.at(72,2), "to the Compound!"));
        townGameMap.at(2,14).addItem(carToBack);
        
		// place shotgun and ammo
		townGameMap.at(10, 18).addItem(new Shotgun());
		townGameMap.at(11, 18).addItem(new ShotgunAmmo());
		townGameMap.at(36, 5).addItem(new ShotgunAmmo());
		townGameMap.at(72, 4).addItem(new ShotgunAmmo());
		gameMap.at(14, 16).addItem(new ShotgunAmmo());
		
		// place sniper and ammo 
		townGameMap.at(50, 4).addItem(new SniperRifle());
		townGameMap.at(51, 5).addItem(new SniperAmmo());
		townGameMap.at(50, 5).addItem(new SniperAmmo());
		townGameMap.at(66, 20).addItem(new SniperAmmo());
		gameMap.at(17, 6).addItem(new SniperAmmo());
		gameMap.at(71, 3).addItem(new SniperAmmo());
		
		// place bracelet
		townGameMap.at(74, 9).addItem(new BraceletOfKyo());
		
		// place a simple weapon
		townGameMap.at(37, 5).addItem(new Plank());
		
		//place food
		townGameMap.at(13, 4).addItem(new Food());
        
	    // Place some random town humans in the safe house
        String[] townHumans = {"Sheriff Bill", "Policeman Jerry", "Firefighter Jenny", "Doctor Aaron", 
        		"Baker Andrea", "Normal Guy Bill", "Teacher Robin"};
		for (String name : townHumans) {
			do {
				x = (int) Math.floor(Math.random() * 26.0 + 49.0);
				y = (int) Math.floor(Math.random() * 9.0 + 4.0);
			} 
			while (townGameMap.at(x, y).containsAnActor());
			townGameMap.at(x,  y).addActor(new Human(name));
		}
		
		townGameMap.at(10, 23).addActor(new Farmer("Crazy Farmer Bill"));

	    // Place some random zombies in town
        String[] townZombies = {"Ooga", "Booga", "Bruh", "Bruhh"};
		for (String name : townZombies) {
			do {
				x = (int) Math.floor(Math.random() * 30.0 + 50.0);
				y = (int) Math.floor(Math.random() * 14.0 + 15.0);
			} 
			while (townGameMap.at(x, y).containsAnActor());
			townGameMap.at(x,  y).addActor(new Zombie(name));
		}
		
		townGameMap.at(11, 5).addActor(new Zombie("Bruh"));
		townGameMap.at(35,  8).addActor(new Zombie("Oof"));
		townGameMap.at(12, 10).addActor(new Zombie("Ooooof"));
		
		
		world.run();
	}
}
