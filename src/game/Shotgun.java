package game;


import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.Item;

public class Shotgun extends GunItem {
	
	public Shotgun() {
		super("shotgun", '>', 15, "smacks");
	}
	
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
