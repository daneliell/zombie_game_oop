package game;

import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.DropItemAction;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.PickUpItemAction;
import edu.monash.fit2099.engine.WeaponItem;

/**
 * Weapon dropped from knocking off the arm of a Zombie. Can be crafted into a Zombie Club.
 * 
 * @author Daniel Yuen
 *
 */
public class ZombieArm extends WeaponItem{
	private Item craftItem;
	protected Actions allowableActions;
	
	public ZombieArm() {
		super("zombie arm", '/', 15, "smacks");
		this.allowableActions = new Actions();
		this.craftItem = new ZombieClub();	
	}
	
	public Item getCraftItem() {
		return this.craftItem;
	}
	
	public void tick(Location currentLocation, Actor actor) {
		if(this.craftItem != null) {
			this.allowableActions.add(new CraftingAction(this));
			}
		}
}
