package game;

import edu.monash.fit2099.engine.WeaponItem;

import java.util.ArrayList;

import edu.monash.fit2099.engine.Item;

/**
 * Weapon dropped from knocking off the leg of a Zombie. Can be crafted into a Zombie Mace.
 * 
 * @author Daniel Yuen
 *
 */
public class ZombieLeg extends WeaponItem {
	ArrayList<Item> craftItems = new ArrayList<Item>();
	
	public ZombieLeg() {
		super("zombie leg", '}', 20, "whacks");
		this.setCraftItems(this.craftItems);
		this.addCraftItems(new ZombieMace());
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
