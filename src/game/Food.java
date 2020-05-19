package game;

import edu.monash.fit2099.engine.Item;

public class Food extends Item {
	protected int nutrients = 10;
	
	public Food() {
		super("food", 'f', true);
	}

	@Override
	public Item getCraftItem() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isCraftable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public CraftingAction getCraftingAction() {
		if(this.isCraftable()) {
			return new CraftingAction(this);
		}
		return null;
	}



}
