package game;

import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Action;

/**
 * A class that represents a vehicle item.
 * 
 * @author Daniel Yuen
 *
 */
public class Vehicle extends Item {

	public Vehicle(String name, char displayChar) {
		super(name, displayChar, false);
	}
	
	/**
	 * Public method to add actions to the list of allowable actions.
	 * 
	 * @param action Action to be added
	 */
	public void addAction(Action action) {
		this.allowableActions.add(action);
	}
	
	@Override
	public Item getCraftItem() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
