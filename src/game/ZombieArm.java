package game;

import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.WeaponItem;

/**
 * Weapon dropped from knocking off the arm of a Zombie. Can be crafted into a Zombie Club.
 * 
 * @author Daniel Yuen
 *
 */
public class ZombieArm extends WeaponItem{
	private Item craftItem;
	
	public ZombieArm() {
		super("zombie arm", '/', 15, "smacks");
		this.craftItem = new ZombieClub();
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
