package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
/**
 * 
 * @author Sravan
 *
 */
public class HarvestAction extends Action {
	protected Crop crop;
	protected Location ground;
	
	public HarvestAction(Crop crop, Location ground) {
		this.crop = crop;
		this.ground = ground;
	}
	
	public String execute(Actor actor, GameMap map) {
			this.ground.setGround(new Dirt());
			if (actor instanceof Player) {
				actor.addItemToInventory(new Food());
			}
			else {
				this.ground.addItem(new Food());
			}
			return actor + " harvests the ripe crop";
	}
	
	public String menuDescription(Actor actor) {
		return actor + " harvests food from ripe crop";
	}

}
