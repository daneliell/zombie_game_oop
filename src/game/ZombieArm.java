package game;

import java.util.ArrayList;

import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.WeaponItem;

/**
 * Weapon dropped from knocking off the arm of a Zombie. Can be crafted into a Zombie Club.
 * 
 * @author Daniel Yuen
 *
 */
public class ZombieArm extends WeaponItem{
	ArrayList<Item> craftItems = new ArrayList<Item>();
	
	public ZombieArm() {
		super("zombie arm", '/', 15, "smacks");
		this.setCraftItems(this.craftItems);
		this.addCraftItems(new ZombieClub());
	}
	
	public void setCraftItems(ArrayList<Item> craftItems) {
		this.craftItems = craftItems;
	}
	
	public ArrayList<Item> getCraftItems() {
		return this.craftItems;
	}
	
	public void addCraftItems(Item item) {
		ArrayList<Item> currentItems = this.getCraftItems();
		currentItems.add(item);
		this.setCraftItems(currentItems);
	}
	

}
