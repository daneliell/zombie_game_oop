package game;

import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
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
	protected Actions allowableActions;
	
	public ZombieArm() {
		super("zombie arm", '/', 15, "smacks");
		this.allowableActions = new Actions();
		this.craftItem = new ZombieClub();
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
		if(this.isCraftable()) {
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
