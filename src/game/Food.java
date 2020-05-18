package game;

import edu.monash.fit2099.engine.Item;

public class Food extends Item {
	protected int nutrients = 10;
	
	public Food() {
		super("food", 'f', true);
	}

}
