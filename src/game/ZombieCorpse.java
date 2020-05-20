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
 * a Zombie. Turns into a Zombie Actor within 5-10 turns.
 * 
 * @author Daniel Yuen
 *
 */
public class ZombieCorpse extends PortableItem {
	private int conversionCounter;
	private int minTurns = 5;
	private int maxTurns = 10;
	
	/**
	 * Constructor.
	 * 
	 * Sets the conversionCounter for each instance of ZombieCorpse to a
	 * random integer between 5-10.
	 * 
	 * @param name Name of the human that is killed
	 */
	public ZombieCorpse(String name) {
		super(name, '%');
		
		Random rand = new Random();
		conversionCounter = rand.nextInt((maxTurns - minTurns) + 1) + minTurns;
	}
	
	/**
	 * Tick method for when the corpse is on the ground. Removes the 
	 * ZombieCorpse and adds a new Zombie in the location after
	 * conversionCounter reaches 0.
	 */
	@Override
	public void tick(Location location) {
		if (conversionCounter == 0) {
			location.removeItem(this);
			location.addActor(new Zombie("Zombie " + name));
			System.out.println(name + " rises from the dead!");
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
			exits.get(0).getDestination().addActor(new Zombie("Zombie " + name));
			System.out.println(name + " rises from the dead!");
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

	@Override
	public boolean isCraftable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public CraftingAction getCraftingAction() {
		if(this.isCraftable()) {
			return new CraftingAction(this);
		}
		return null;

	}
	
}
