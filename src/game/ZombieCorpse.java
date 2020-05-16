package game;

import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;

import java.util.Random;

public class ZombieCorpse extends Item {
	
	private int conversionCounter;
	private int minTurns = 5;
	private int maxTurns = 10;
	
	public ZombieCorpse(String name) {
		super(name, 'a', false);
		
		Random rand = new Random();
		conversionCounter = rand.nextInt((maxTurns - minTurns) + 1) + minTurns;
	}
	
	@Override
	public void tick(Location currentLocation) {
		if (conversionCounter == 0) {
			currentLocation.removeItem(this);
			currentLocation.addActor(new Zombie("Zombie " + name));
			System.out.println(name + "rises from the dead!");
		}
		else {
			conversionCounter--;
		}
	}
	
}
