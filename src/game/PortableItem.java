package game;

import java.util.ArrayList;

import edu.monash.fit2099.engine.Item;

/**
 * Base class for any item that can be picked up and dropped.
 */
public class PortableItem extends Item {

	public PortableItem(String name, char displayChar) {
		super(name, displayChar, true);
	}

	@Override
	public void setCraftItems(ArrayList<Item> craftItems) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addCraftItems(Item item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Item> getCraftItem() {
		// TODO Auto-generated method stub
		return null;
	}
}
