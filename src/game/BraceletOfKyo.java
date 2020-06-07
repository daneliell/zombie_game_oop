package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Location;

public class BraceletOfKyo extends PortableItem {
	
	private static final int healthInc = 50;
	
	public BraceletOfKyo(String name, char displayChar) {
		super("Bracelet Of Kyo", 'o');
	}
	
	@Override
	public void tick(Location currentLocation, Actor actor) {
		actor.asPlayer().incMaxHealth(healthInc);
	}
}
