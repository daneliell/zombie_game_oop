package game;

import java.util.ArrayList;

import edu.monash.fit2099.engine.Item;

public class Food extends Item {
	protected int nutrients = 10;
	
	public Food() {
		super("food", 'f', true);
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
	public ArrayList<Item> getCraftItems() {
		// TODO Auto-generated method stub
		return null;
	}

}
