package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Location;

public class PlayerHarvestAction extends HarvestAction {
	protected Crop target;
	protected Location ground;
	
	public PlayerHarvestAction(Crop target, Location ground) {
		super(target, ground);
	}
	
	
	public String execute(Actor actor) {
		this.ground.setGround(new Dirt());
		actor.addItemToInventory(new Food());
		return actor + " harvested the ripe " + this.crop;
	}
}
