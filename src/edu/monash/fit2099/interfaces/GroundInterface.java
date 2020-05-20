package edu.monash.fit2099.interfaces;

import game.Crop;


/**
 * This interface provides the ability to add methods to Ground, without modifying code in the engine,
 * or downcasting references in the game.   
 */

public interface GroundInterface {
	public Crop asCrop();
	public Boolean isRipe();
}
