package game;

import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.Actor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * An Item subclass which represents a corpse Item when an Actor is killed by
 * a Zombie. Turns into a Zombie within 5-10 turns.
 * 
 * @author Daniel Yuen
 *
 */
public class ZombieCorpse extends PortableItem {
	private int conversionCounter;
	private static final int MIN_TURNS = 5;
	private static final int MAX_TURNS = 10;
	private Zombie zombie;
	
	/**
	 * Constructor.
	 * 
	 * Sets the conversionCounter for each instance of ZombieCorpse to a
	 * random integer between 5-10.
	 * 
	 * @param name Name of the human that is killed
	 */
	public ZombieCorpse(String name) {
		super(name , '%');
		Random rand = new Random();
		conversionCounter = rand.nextInt((MAX_TURNS - MIN_TURNS) + 1) + MIN_TURNS;
		assert (conversionCounter >= 5 & conversionCounter <= 11) : "Zombie corpse did not rise in 5-10 turns";
		zombie = new Zombie("Zombie " + name);
	}
	
	/**
	 * Tick method for when the corpse is on the ground. Removes the 
	 * ZombieCorpse and adds a new Zombie in the location after
	 * conversionCounter reaches 0.
	 */
	@Override
	public void tick(Location location) {
		if (conversionCounter == 0) {
			if (location.containsAnActor()) {
				placeZombie(location);
			}
			else {
				location.addActor(zombie);
			}
			location.removeItem(this);
		}
		else {
			conversionCounter--;
		}
	}
	
	/**
	 * Tick method for when the corpse is being carried. Removes the
	 * ZombieCorpse from the inventory of the Actor carrying the corpse
	 * and adds a new Zombie in a random location adjacent to the Actor
	 * after conversionCounter reaches 0.
	 */
	@Override
	public void tick(Location location, Actor actor) {
		if (conversionCounter == 0) {
			actor.removeItemFromInventory(this);
			placeZombie(location);
		}
		else {
			conversionCounter--;
		}
	}
	
	/**
	 * Private method to place the Zombie Actor at an exit which is 
	 * a location adjacent to the ZombieCorpse location.
	 * 
	 * @param location Location of the ZombieCorpse
	 */
	private void placeZombie(Location location) {
		List<Exit> exits = new ArrayList<Exit>(location.getExits());
		Collections.shuffle(exits);
		for (Exit exit : exits) {
			if (exit.getDestination().canActorEnter(zombie)) {
				exit.getDestination().addActor(zombie);
				break;
			}
		}
	}

	@Override
	public Item getCraftItem() {
		// TODO Auto-generated method stub
		return null;
	}
}
