package game;


import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.Item;

public class Shotgun extends GunItem {
	
	public Shotgun() {
		super("shotgun", '>', 15, "smacks");
		this.addCapability(ItemCapability.CAN_SHOOT);
	}
	
	@Override
	public Ammo isLoaded(Actor actor) {
		Ammo ammo = this.isLoaded(actor);
		if (ammo.asShotgunAmmo() != null) {
			return ammo.asShotgunAmmo();
		}
		return null;
	}
	
	@Override
	public Action getAction(Display display) {
		return new ShotgunAction(display);
	}
	
	@Override
	public Item getCraftItem() {
		// TODO Auto-generated method stub
		return null;
	}
}
