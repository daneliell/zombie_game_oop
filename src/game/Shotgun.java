package game;

import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.WeaponItem;

public class Shotgun extends WeaponItem {
	
	public Shotgun() {
		super("shotgun", '>', 40, "blasts");
	}
	
	

	@Override
	public Item getCraftItem() {
		// TODO Auto-generated method stub
		return null;
	}
}
