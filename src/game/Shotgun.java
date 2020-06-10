package game;

import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.Item;

/**
 * Class representing a Shotgun item. Uses ShotgunAmmo
 * as ammo.
 * 
 * @author Daniel Yuen
 *
 */
public class Shotgun extends GunItem {
	
	/**
	 * Constructor.
	 * 
	 * Creates a Shotgun instance with default melee damage of 15.
	 */
	public Shotgun() {
		super("shotgun", '>', 15, "smacks");
	}
	
	/**
	 * @return an instance of ShotgunAmmo if the Actor is carrying
	 * it, null otherwise
	 */
	@Override
	public Ammo getAmmo(Actor actor) {
		List<Item> inventory = actor.getInventory();
		for (Item item : inventory) {
			if (item.asShotgunAmmo() != null) {
				return item.asShotgunAmmo();
			}
		}
		return null;
	}
	
	/**
	 * @return an instance of ShotgunAction
	 */
	@Override
	public Action getAction(Display display) {
		return new ShotgunAction(display, this);
	}
	
	@Override
	public Item getCraftItem() {
		// TODO Auto-generated method stub
		return null;
	}
}
