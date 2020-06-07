package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.WeaponItem;

public abstract class GunItem extends WeaponItem {

	public GunItem(String name, char displayChar, int damage, String verb) {
		super(name, displayChar, damage, verb);
	}
	
	public Ammo getAmmo(Actor actor) {
		return null;
	}
	
	public Action getAction(Display display) {
		return null;
	}
	
	public void reduceAmmo(Actor actor) {
		Ammo ammo = this.getAmmo(actor);
		ammo.reduceRounds();
		if (ammo.getRounds() == 0) {
			actor.removeItemFromInventory(ammo);
		}
	}
}
