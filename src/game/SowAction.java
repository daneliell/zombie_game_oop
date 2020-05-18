package game;


import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;

public class SowAction extends Action {
	protected Location target;
	
	public SowAction(Location target) {
		this.target = target;

	}
	
	public String execute(Actor actor, GameMap map) {
		Ground ground = this.target.getGround();
		if(ground.getDisplayChar() == '.') {
			this.target.setGround(new Crop());
			return actor + " sowed the patch of dirt";
		}
		return "The ground is not a patch of dirt";
	}
	
	public String menuDescription(Actor actor) {
		return actor + " planted a crop";
	}
	

}
