package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.WeaponItem;

public class CraftingAction extends Action {
	protected WeaponItem rawWeapon;
	protected WeaponItem craftedWeapon;
	
	public CraftingAction(WeaponItem rawWeapon) {
		this.rawWeapon = rawWeapon;
	}
	
	public String execute(Actor actor, GameMap map) {
		if(this.rawWeapon == new ZombieArm()) {
			this.craftedWeapon = new ZombieClub();
			actor.removeItemFromInventory(this.rawWeapon);
			actor.addItemToInventory(this.craftedWeapon);
			return "A zombie club was crafted.";
		}
		
		if(this.rawWeapon == new ZombieLeg()) {
			this.craftedWeapon = new ZombieMace();
			actor.removeItemFromInventory(this.rawWeapon);
			actor.addItemToInventory(this.craftedWeapon);
			return "A zombie mace was crafted.";
		}
		
		return null;
	}
	
	public String menuDescription(Actor actor) {
		return actor + " crafted a " + this.craftedWeapon + "."; 
	}
	
}
