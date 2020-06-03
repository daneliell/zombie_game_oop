package game;

import java.util.List;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.Menu;
import edu.monash.fit2099.engine.WeaponItem;

public class Shotgun extends WeaponItem {
	
	private List<Exit> directions;
	private Menu subMenu = new Menu();
	private Actions actions = new Actions();
	
	public Shotgun() {
		super("shotgun", '>', 40, "blasts");
		this.addCapability(ItemCapability.CAN_SHOOT);
	}
	
	@Override
	public void tick(Location currentLocation, Actor actor) {
		this.allowableActions.clear();
		List<Item> inventory = actor.getInventory();
		
		for (Item item : inventory) {
			if (item.asShotgunAmmo() != null) {
				List<Exit> exits = currentLocation.getExits();
				/*int left;
				int right;
				for (int i = 0; i < exits.size(); i++) {
					left = i - 1;
					if (left < 0) {
						left = exits.size() - 1;
					}
					right = (i + 1) % (exits.size() - 1);
					this.allowableActions.add(new ShootAction(exits.get(left), exits.get(i), exits.get(right)));*/
				for (Exit e : exits) {
					this.allowableActions.add(new ShotgunAction(e));
				}
			}
		}
	}
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

	@Override
	public Item getCraftItem() {
		// TODO Auto-generated method stub
		return null;
	}
}
