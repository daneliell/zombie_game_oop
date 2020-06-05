package game;

import java.util.List;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.WeaponItem;

public class SniperRifle extends WeaponItem {
	
	public SniperRifle() {
		super("sniper rifle", '!', 15, "smacks");
		this.addCapability(ItemCapability.CAN_SHOOT);
	}
	
	public boolean isLoaded(Actor actor) {
		List<Item> inventory = actor.getInventory();
		for (Item item : inventory) {
			if (item.asSniperAmmo() != null) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Item getCraftItem() {
		// TODO Auto-generated method stub
		return null;
	}
}
