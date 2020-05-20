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
	private Item rawItem;
	private Item craftedItem;
	
	public CraftingAction(Item rawItem) {
			this.rawItem = rawItem;
			this.craftedItem = rawItem.getCraftItem();
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
		actor.removeItemFromInventory(this.rawItem);
		actor.addItemToInventory(this.craftedItem);
		return menuDescription(actor);
	}
	
	public String menuDescription(Actor actor) {
		return actor + " crafts a " + this.craftedItem; 
	}
	
}
