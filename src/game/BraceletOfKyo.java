package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Location;

/**
 * Bracelet of Kyo item that increases the health of the wearer.
 * 
 * @author Daniel Yuen
 *
 */
public class BraceletOfKyo extends PortableItem {
	
	private static final int HEALTH = 50;
	private boolean onGround = true;
	
	/**
	 * Constructor.
	 * 
	 * Sets the display character of the bracelet.
	 */
	public BraceletOfKyo() {
		super("Bracelet Of Kyo", 'o');
	}
	
	/**
	 * Tick method called when the Bracelet is being held by an Actor.
	 * Sets onGround to false to prevent Player HP from increasing
	 * every turn.
	 */
	@Override
	public void tick(Location currentLocation, Actor actor) {
		if (onGround == true) {
			actor.asPlayer().incMaxHealth(HEALTH);
			onGround = false;
		}
	}
	
	/**
	 * Tick method called when the Bracelet is on the ground. Sets
	 * onGround to true if just dropped by a Player to prevent Player
	 * HP from decreasing every turn.
	 */
	@Override
	public void tick(Location currentLocation) {
		if (onGround == false) {
			currentLocation.getActor().asPlayer().decMaxHealth(HEALTH);
			onGround = true;
		}
	}
}
