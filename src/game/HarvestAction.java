package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
/**
 * A harvesting action for the Player and Farmers to harvest a ripe Crop into a Food item
 * 
 * @author Sravan
 *
 */
public class HarvestAction extends Action {
	protected Crop crop;
	protected Location ground;
	
	/**
	 * Constructor.
	 * 
	 * @param crop the Crop that is to be harvested
	 * @param ground the Location of the Crop
	 */
	public HarvestAction(Crop crop, Location ground) {
		this.crop = crop;
		this.ground = ground;
	}
	
	/**
	 * Executes the harvesting action.
	 * 
	 * If the Player harvests the Crop, a Food item is added directly into the Player's inventory.
	 * If a Farmer harvests the Crop, a Food item is placed at the Location of the Crop.
	 * Once harvested, the Ground at the Location of the Crop turns into a patch of Dirt.
	 */
	public String execute(Actor actor, GameMap map) {
			this.ground.setGround(new Dirt());
			//Is the actor a Player?
			if (actor instanceof Player) {
				actor.addItemToInventory(new Food());
			}
			//Is the actor a Farmer?
			else {
				this.ground.addItem(new Food());
			}
			return actor + " harvests the ripe crop";
	}
	
	public String menuDescription(Actor actor) {
		return actor + " harvests food from ripe crop";
	}
}
