package game;

import edu.monash.fit2099.engine.WeaponItem;

import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Item;

/**
 * Weapon dropped from knocking off the leg of a Zombie. Can be crafted into a Zombie Mace.
 * 
 * @author Daniel Yuen
 *
 */
public class ZombieLeg extends WeaponItem {
	private Item craftItem;
	protected Actions allowableActions;
	
	public ZombieLeg() {
		super("zombie leg", '}', 20, "whacks");
		this.allowableActions = new Actions();
		this.craftItem = new ZombieMace();
		this.addAction(this.getCraftingAction());
	}
	
	
	public Item getCraftItem() {
		return this.craftItem;
	}
	
	
	public boolean isCraftable() {
		return true;
	}


	@Override
	public CraftingAction getCraftingAction() {
		if(this.isCraftable() == true) {
			return new CraftingAction(this);
		}
		return null;
	}
	
	private void addAction(Action action) {
		List<Action> actions = super.getAllowableActions();
		this.allowableActions.add(actions);
		this.allowableActions.add(action);
	}
	
	public List<Action> getAllowableActions() {
		return allowableActions.getUnmodifiableActionList();
	}

}
