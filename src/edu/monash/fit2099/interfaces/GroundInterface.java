package edu.monash.fit2099.interfaces;

import game.Crop;


/**
 * This interface provides the ability to add methods to Ground, without modifying code in the engine,
 * or downcasting references in the game.   
 */

public interface GroundInterface {
	
	/**
	 * Returns the Ground instance an instance of the Crop class
	 * if it is an instance of the Crop class.
	 * Returns null otherwise.
	 * 
	 * @return an instance of the Crop class
	 */
	public Crop asCrop();
	/**
	 * Returns true is the ripeAge of the Crop is 0.
	 * Returns false otherwise.
	 * 
	 * @return boolean if the Crop is ripe
	 */
	public Boolean isRipe();
}
