package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

/**
 * 
 * @author Sravan Krsna Rao
 *
 */

public class CraftingAction extends Action {
	protected Item rawItem;
	protected Item craftedItem;
	
	public CraftingAction(Item rawItem) {
		this.rawItem = rawItem;
	}
	
	public String execute(Actor actor, GameMap map) {
		this.craftedItem = this.rawItem.getCraftItem();
		actor.removeItemFromInventory(this.rawItem);
		actor.addItemToInventory(this.craftedItem);
		return this.rawItem + " was crafted into a " + this.craftedItem + ".";
		
	}
	
	public String menuDescription(Actor actor) {
		return actor + " crafted a " + this.craftedItem + "."; 
	}
	
}
