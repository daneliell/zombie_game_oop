package game;

import java.util.ArrayList;

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
	
	public CraftingAction(Item rawItem, Item craftedItem) {
		this.rawItem = rawItem;
		this.craftedItem = craftedItem;
	}
	
	public String execute(Actor actor, GameMap map) {
		ArrayList<Item> craftItems = new ArrayList<Item>();
		craftItems = this.rawItem.getCraftItems();
		for(Item item: craftItems) {
			if(item == this.craftedItem) {
				actor.removeItemFromInventory(this.rawItem);
				actor.addItemToInventory(this.craftedItem);
				return this.rawItem + " was crafted into a " + this.craftedItem + ".";
			}
		}
		return null;
		
	}
	
	
	public String menuDescription(Actor actor) {
		return actor + " crafted a " + this.craftedItem + "."; 
	}
	
}
