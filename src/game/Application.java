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
		World world = new World(new Display());

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
		int x, y;
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
		
		
		FancyGroundFactory townGroundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Path());
		List<String> townMap = Arrays.asList(
				"................................................................................",
				"................................................................................",
				".....................::.........................................................",
				".....................::.........................................................",
				".....................::.........................................................",
				".....................::.........................................................",
				".....................::.........................................................",
				".....................::.........................................................",
				".....................::.........................................................",
				".....=======.======..::.........................................................",
				".....................::.........................................................",
				".....::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::.....",
				".....................::.........................................................",
				".....=======,======..::.........................................................",
				".....=,,,,,,,,,,,,=..::.........................................................",
				".....=,,,,,,,,,,,,=..::.........................................................",
				".....=,,,,,,,,,,,,=..::.........................................................",
				".....==============..::.........................................................",
				".....................::.........................................................",
				".....==============..::.........................................................",
				".....=,,,,,,,,,,,,=..::.........................................................",
				".....=,,,,,,,,,,,,,..::.........................................................",
				".....=,,,,,,,,,,,,=..::.........................................................",
				".....==============.............................................................",
				"................................................................................",
				"................................................................................");
		GameMap townGameMap = new GameMap(townGroundFactory, townMap);
		world.addGameMap(townGameMap);

        Vehicle carToTown = new Vehicle("Car", '&');
        carToTown.addAction(new MoveActorAction(townGameMap.at(3,12), "to Town!"));
        gameMap.at(73,2).addItem(carToTown);
        
        //testcar
        gameMap.at(42,16).addItem(carToTown);
        
        Vehicle carToBack = new Vehicle("Car", '&');
        carToBack.addAction(new MoveActorAction(gameMap.at(72,2), "back!"));
        townGameMap.at(2,12).addItem(carToBack);
        
        //testing the map
        //gameMap.at(79, 24).addItem(carToTown);
		
		world.run();
	}
}
