package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Exit;

public class ShootAction extends Action{
	
	private Exit direction;
	
	public ShootAction(Exit direction) {
		this.direction = direction;
	}

	@Override
	public String execute(Actor actor, GameMap map) {
		return null;
	}

	@Override
	public String menuDescription(Actor actor) {
		return actor + " shoots " + direction.getName(); 
	}
	
}
