package game;


import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
/**
 * An action to plant a Crop at a specified location.
 * 
 * @author Sravan 
 *
 */
public class SowAction extends Action {
	protected Location target;
	
	/**
	 * Constructor.
	 * 
	 * @param target the location where the Crop object is to be planted.
	 * 
	 */
	public SowAction(Location target) {
		this.target = target;
	}
	
	/**
	 * Sets the ground at the specified location to Crop object.
	 */
	public String execute(Actor actor, GameMap map) {
		this.target.setGround(new Crop());
		return actor + " sows a patch of dirt";
	}
	
	public String menuDescription(Actor actor) {
		return actor + " sows a patch of dirt";
	}
}
