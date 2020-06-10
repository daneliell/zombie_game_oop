package game;

import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.Item;

/**
 * Class representing a SniperRifle item. Uses SniperAmmo
 * as ammo.
 * 
 * @author Daniel Yuen
 *
 */
public class SniperRifle extends GunItem {
	
	/**
	 * Constructor.
	 * 
	 * Creates a SniperRifle instance with default melee damage of 15.
	 */
	public SniperRifle() {
		super("sniper rifle", '!', 15, "smacks");
	}
	
	/**
	 * @return an instance of SniperAmmo if the Actor is carrying
	 * it, null otherwise
	 */
	@Override
	public Ammo getAmmo(Actor actor) {
		List<Item> inventory = actor.getInventory();
		for (Item item : inventory) {
			if (item.asSniperAmmo() != null) {
				return item.asSniperAmmo();
			}
		}
		return null;
	}
	
	/**
	 * @return an instance of SniperAction
	 */
	@Override
	public Action getAction(Display display) {
		return new SniperAction(display, this);
	}

	@Override
	public Item getCraftItem() {
		// TODO Auto-generated method stub
		return null;
	}
}
