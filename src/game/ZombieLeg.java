package game;

import edu.monash.fit2099.engine.WeaponItem;

import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.DropItemAction;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.PickUpItemAction;

/**
 * Weapon dropped from knocking off the leg of a Zombie. Can be crafted into a Zombie Mace.
 * 
 * @author Daniel Yuen
 *
 */
public class ZombieLeg extends WeaponItem {
	private Item craftItem;
	private boolean canCraft = true;
	
	public ZombieLeg() {
		super("zombie leg", '}', 20, "whacks");
		this.craftItem = new ZombieMace();
	}
	
	public Item getCraftItem() {
		return this.craftItem;
	}	
	
	public void tick(Location currentLocation, Actor actor) {
		if (this.canCraft == true && this.getCraftItem() != null) {
			super.allowableActions.add(new CraftingAction(this));
			this.canCraft = false;
		}
	}	
}
