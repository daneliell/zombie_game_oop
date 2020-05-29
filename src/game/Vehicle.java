package game;

import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Action;

public class Vehicle extends Item {

	public Vehicle(String name, char displayChar) {
		super(name, displayChar, false);
	}
	
	public void addAction(Action action) {
		this.allowableActions.add(action);
	}
	
	@Override
	public Item getCraftItem() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
