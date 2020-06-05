package game;

import java.util.List;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.WeaponItem;

public class Shotgun extends WeaponItem {
	
	public Shotgun() {
		super("shotgun", '>', 15, "smacks");
		this.addCapability(ItemCapability.CAN_SHOOT);
	}
	
	/*@Override
	public void tick(Location currentLocation, Actor actor) {
		this.allowableActions.clear();
		List<Item> inventory = actor.getInventory();
		
		for (Item item : inventory) {
			if (item.asShotgunAmmo() != null) {
				List<Exit> exits = currentLocation.getExits();
				for (Exit e : exits) {
					this.allowableActions.add(new ShotgunAction(e));
				}
			}
		}
	}*/
	//int hotKey1 = Integer.parseInt(e1.getHotKey());
	//int hotKeyRight = Math.abs(hotKey1 + 1)%9;
	//int hotKeyLeft = Math.abs(hotKey1 - 1)%9;
	/*for (Exit e2 : exits) {
		int hotKey2 = Integer.parseInt(e2.getHotKey());
		if (hotKey2 == hotKeyLeft) {
			leftExit = e2;
		}
		else if (hotKey2 == hotKeyRight) {
			rightExit = e2;
		}
	}*/
		//int hotKeyDifference = Math.abs(hotkey1 - hotkey2);
			//if (hotKeyDifference%9 == 1 | hotKeyDifference%9 == -1) {
				//adjacentHotKeys.add(
			//}
		//actor.removeItemFromInventory(new ShotgunShells());
	
	public boolean isLoaded(Actor actor) {
		List<Item> inventory = actor.getInventory();
		for (Item item : inventory) {
			if (item.asShotgunAmmo() != null) {
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
