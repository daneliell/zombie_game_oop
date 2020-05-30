package game;

import java.util.ArrayList;
import java.util.List;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.WeaponItem;

public class Shotgun extends WeaponItem {
	
	private List<Exit> directions;
	
	public Shotgun() {
		super("shotgun", '>', 40, "blasts");
	}
	
	@Override
	public void tick(Location currentLocation, Actor actor) {
		List<Item> inventory = actor.getInventory();
		
		for (Item item : inventory) {
			if (item instanceof ShotgunShells) {
				directions = new ArrayList<Exit>(currentLocation.getExits());
				for (Exit e : directions) {
					if (e.getName() == "North" | e.getName() == "East" | e.getName() == "West" | e.getName() == "South") {
						this.allowableActions.add(new ShootAction(e));
					}
				}
			}
		}
		//actor.removeItemFromInventory(new ShotgunShells());
	}

	@Override
	public Item getCraftItem() {
		// TODO Auto-generated method stub
		return null;
	}
}
