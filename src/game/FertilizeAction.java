package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
/**
 * An action for a Farmer to fertilize a Crop so that the Crop ripens faster.
 * 
 * @author Sravan
 * 
 */
public class FertilizeAction extends Action{
	private Crop target;
	
	/**
	 * Constructor.
	 * 
	 * @param target the Crop that is to be fertilized
	 */
	public FertilizeAction(Crop target) {
		this.target = target;
	}
	
	/**
	 * Gets the age of the crop and reduces it by 10 turns
	 * or sets the age to 0 if there less that 10 turns remaining for the crop to be ripe.
	 */
	public String execute(Actor actor, GameMap map) {
		//Gets the age of the Crop
		int Age = this.target.getAge();
		
		//Is the number of turns left to ripen less that 10?
		if (Age < 10) {
			//Sets the new Age of the Crop
			this.target.setAge(0);
			return actor + " fertilizes a crop";
		}	
		else {
			int newAge = Age - 10;
			//Sets the new age of the Crop
			this.target.setAge(newAge);
			return actor + " fertilizes a crop";
		}
	}
	
	public String menuDescription(Actor actor) {
		return actor + " fertilizes crop";
	}
}
