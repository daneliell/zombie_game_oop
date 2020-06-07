package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Location;

public class BraceletOfKyo extends PortableItem {
	
	private static final int HEALTH = 50;
	private boolean onGround = true;
	
	public BraceletOfKyo() {
		super("Bracelet Of Kyo", 'o');
	}
	
	@Override
	public void tick(Location currentLocation, Actor actor) {
		if (onGround == true) {
			actor.asPlayer().incMaxHealth(HEALTH);
			onGround = false;
		}
	}
	
	@Override
	public void tick(Location currentLocation) {
		System.out.println(this.onGround);
		if (onGround == false) {
			currentLocation.getActor().asPlayer().decMaxHealth(HEALTH);
		}
		onGround = true;
	}
}
