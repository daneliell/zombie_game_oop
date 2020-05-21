package game;

import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;

/**
 *A Ground subclass that can be harvested when ripe into a Food item.
 *Turns ripe after 20 turns if not fertilized.
 * @author Sravan
 *
 */
public class Crop extends Ground {
	private int ripeAge = 20;
	private Actions allowableActions;
	private boolean canHarvest = true;
	
	public Crop() {
		super('c');
		allowableActions = new Actions();
	}
	
	/**
	 * checks if the Crop is ripe by checking if the attribute ripeAge is 0.
	 */
	public Boolean isRipe() {
		if(this.ripeAge == 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * Gets the current age of the crop.
	 * @return the Age of the crop
	 */
	public int getAge() {
		return this.ripeAge;
	}
	
	/**
	 * Sets the age of the crop to a new value.
	 * @param Age the age of the crop
	 */
	public void setAge(int Age) {
		this.ripeAge = Age;
	}
	
	@Override
	/**
	 * Informs the crop of the passage of time.
	 * Reduces the age of the crop each turn if it is not ripe.
	 * Checks each turn if the crop is ripe.
	 * If the crop is ripe, the display character is changed
	 * and Harvest action is added to the allowable actions of the crop.
	 */
	public void tick(Location location) {
		// Is the crop ripe?
		if(this.ripeAge == 0 && this.canHarvest == true) {
			displayChar = 'C';
			// Crop can now be harvested
			allowableActions.add(new HarvestAction(this, location));
			this.canHarvest = false;
		}
		// Reduce the age of the crop
		if (this.ripeAge > 0) {
			this.ripeAge--;
		}
	}
	
	@Override 
	/**
	 * Returns allowable actions of the crop.
	 */
	public Actions allowableActions(Actor actor, Location location, String direction){
		return allowableActions;
	}
	
	/**
	 * Blocks objects from being thrown onto the crop.
	 */
	public boolean blocksThrownObjects() {
		return true;
	}
	
	@Override
	/**
	 * Checks if the Ground is an instance of a Crop class.
	 * Returns the Ground object as a Crop object if it is an instance of Crop.
	 * Returns null otherwise.
	 */
	public Crop asCrop() {
		return this instanceof Crop ? (Crop) this : null;
	}
}



