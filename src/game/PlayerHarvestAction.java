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
public class PlayerHarvestAction extends HarvestAction{
	protected Crop crop;
	protected Location ground;
	
	public PlayerHarvestAction(Crop crop, Location ground) {
		super(crop, ground);
	}
	
	public String execute(Actor actor, GameMap map) {
			this.ground.setGround(new Dirt());
			actor.addItemToInventory(new Food());
			return super.menuDescription(actor);
		}
		

}
