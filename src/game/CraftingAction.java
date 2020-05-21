package game;

import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

/**
 * Crafting action for players to craft an item into a superior item.
 * Removes old item from player's inventory and adds the newly crafted item into the player's inventory.
 * 
 * @author Sravan Krsna Rao
 *
 */

public class CraftingAction extends Action {
	private Item rawItem;
	private Item craftedItem;
	
	/**
	 * Constructor.
	 * 
	 * @param rawItem the item that is to be crafted
	 */
	public CraftingAction(Item rawItem) {
			this.rawItem = rawItem;
			this.craftedItem = rawItem.getCraftItem();
	}
	
	/**
	 * Executes the action of crafting the item.
	 * 
	 * Takes the original item that is to be crafted
	 * and removes it from the player's inventory.
	 * Gets the crafted item that is linked to the original item
	 * and adds it to the players inventory.
	 */
	public String execute(Actor actor, GameMap map) {
		//Original item is removed from the actor's inventory
		actor.removeItemFromInventory(this.rawItem);
		//Crafted item is added to the actor's inventory
		actor.addItemToInventory(this.craftedItem);
		return menuDescription(actor);
	}
	
	public String menuDescription(Actor actor) {
		return actor + " crafts a " + this.craftedItem; 
	}
}
