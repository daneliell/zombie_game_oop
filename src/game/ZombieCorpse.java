package game;

import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;

import java.util.ArrayList;
import java.util.Random;

/**
 * A Item subclass which represents a corpse Item when an Actor is killed by
 * a Zombie. Turns into a Zombie Actor within 5-10 turns.
 * 
 * @author Daniel Yuen
 *
 */
public class ZombieCorpse extends Item {
	
	private int conversionCounter;
	private int minTurns = 5;
	private int maxTurns = 10;
	
	public ZombieCorpse(String name) {
		super(name, '%', false);
		
		Random rand = new Random();
		conversionCounter = rand.nextInt((maxTurns - minTurns) + 1) + minTurns;
	}
	
	@Override
	public void tick(Location currentLocation) {
		if (conversionCounter == 0) {
			currentLocation.removeItem(this);
			currentLocation.addActor(new Zombie("Zombie " + name));
			System.out.println(name + " rises from the dead!");
		}
		else {
			conversionCounter--;
		}
	}

	@Override
	public void setCraftItems(ArrayList<Item> craftItems) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addCraftItems(Item item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Item> getCraftItem() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
