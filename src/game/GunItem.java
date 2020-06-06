package game;

import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.WeaponItem;

public abstract class GunItem extends WeaponItem {

	public GunItem(String name, char displayChar, int damage, String verb) {
		super(name, displayChar, damage, verb);
		this.addCapability(ItemCapability.CAN_SHOOT);
	}
	
	public Ammo isLoaded(Actor actor) {
		List<Item> inventory = actor.getInventory();
		for (Item item : inventory) {
			if (item.asAmmo() != null) {
				return item.asAmmo();
			}
		}
		return null;
	}
	
	public Action getAction(Display display) {
		return null;
	}
	
	public void reduceAmmo(Actor actor) {
		Ammo ammo = isLoaded(actor);
		ammo.reduceRounds();
		if (!ammo.getRounds()) {
			actor.removeItemFromInventory(ammo);
		}
	}
}
