package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

public class HarvestAction extends Action {
	protected Crop crop;
	protected Location ground;
	
	public HarvestAction(Crop crop, Location ground) {
		this.crop = crop;
		this.ground = ground;
	}
	
	public String execute(Actor actor, GameMap map) {
		if(this.crop.isRipe()) {
			this.ground.setGround(new Dirt());
			this.ground.addItem(new Food());
			return actor + " harvested the ripe " + this.crop;
		}
		
		return this.crop + " is not ripe";
	}
	
	public String menuDescription(Actor actor) {
		return actor + " harvested food from the " + this.crop;
	}

}
