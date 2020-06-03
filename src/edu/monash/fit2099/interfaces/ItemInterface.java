package edu.monash.fit2099.interfaces;

import edu.monash.fit2099.engine.Item;
import game.Food;
import game.Shotgun;
import game.ShotgunAmmo;

/**
 * This interface provides the ability to add methods to Ground, without modifying code in the engine,
 * or downcasting references in the game.   
 */
public interface ItemInterface {
	
	/**
	 * Returns the crafted form of an Item.
	 * Returns null if the Item does not have a crafted form.
	 * 
	 * @return crafted form of an Item
	 */
	public Item getCraftItem();
	/**
	 * Returns the Item instance as an instance of the Food class
	 * if it is an instance of the Food class.
	 * Returns null otherwise.
	 * 
	 * @return an instance of the Food class
	 */
	default Food asFood() {
		return this instanceof Food ? (Food) this : null;
	}
	
	default ShotgunAmmo asShotgunAmmo() {
		return this instanceof ShotgunAmmo ? (ShotgunAmmo) this : null;
	}
	
	default Shotgun asShotgun() {
		return this instanceof Shotgun ? (Shotgun) this : null;
	}
}
