package game;

import java.util.List;

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
	
	public boolean itemInInventory(Actor actor) {
		List<Item> items = actor.getInventory();
		for(Item item: items) {
			if(item == this.rawItem) {
				return true;
			}
		}
		return false;
	}
	
	public String execute(Actor actor, GameMap map) {
		if (this.itemInInventory(actor)) {
			this.craftedItem = this.rawItem.getCraftItem();
			actor.removeItemFromInventory(this.rawItem);
			actor.addItemToInventory(this.craftedItem);
			return menuDescription(actor);
		}		
		return null;
	}
	
	public String menuDescription(Actor actor) {
		return actor + " crafted a " + this.craftedItem + "."; 
	}
	
}
