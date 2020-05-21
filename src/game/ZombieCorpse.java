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
	private int minTurns = 5;
	private int maxTurns = 10;
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
		conversionCounter = rand.nextInt((maxTurns - minTurns) + 1) + minTurns;
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
				List<Exit> exits = new ArrayList<Exit>(location.getExits());
				Collections.shuffle(exits);
				for (Exit exit : exits) {
					if (exit.getDestination().canActorEnter(zombie)) {
						exit.getDestination().addActor(zombie);
						break;
					}
				}
			}
			else {
				location.addActor(new Zombie("Zombie " + name));
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
			List<Exit> exits = new ArrayList<Exit>(location.getExits());
			Collections.shuffle(exits);
			for (Exit exit : exits) {
				if (exit.getDestination().canActorEnter(zombie)) {
					exit.getDestination().addActor(zombie);
					break;
				}
			}
		}
		else {
			conversionCounter--;
		}
	}


	@Override
	public Item getCraftItem() {
		// TODO Auto-generated method stub
		return null;
	}
}
