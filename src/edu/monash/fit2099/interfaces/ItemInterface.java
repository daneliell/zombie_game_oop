package edu.monash.fit2099.interfaces;

import java.util.ArrayList;

import edu.monash.fit2099.engine.Item;

/**
 * This interface provides the ability to add methods to Ground, without modifying code in the engine,
 * or downcasting references in the game.   
 */
public interface ItemInterface {
	
	
	public void setCraftItems(ArrayList<Item> craftItems);
	public void addCraftItems(Item item);
	public ArrayList<Item> getCraftItems();

}
