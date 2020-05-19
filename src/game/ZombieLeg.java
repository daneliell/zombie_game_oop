package game;

import edu.monash.fit2099.engine.WeaponItem;
import edu.monash.fit2099.engine.Item;

/**
 * Weapon dropped from knocking off the leg of a Zombie. Can be crafted into a Zombie Mace.
 * 
 * @author Daniel Yuen
 *
 */
public class ZombieLeg extends WeaponItem {
	private Item craftItem;
	
	public ZombieLeg() {
		super("zombie leg", '}', 20, "whacks");
		this.craftItem = new ZombieMace();
	}
	
	
	public Item getCraftItem() {
		return this.craftItem;
	}
	
	
	public boolean isCraftable() {
		return true;
	}


	@Override
	public CraftingAction getCraftingAction() {
		if(this.isCraftable()) {
			return new CraftingAction(this);
		}
		return null;
	}

}
