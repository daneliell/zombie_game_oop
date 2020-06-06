package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

public class SniperAimAction extends Action {
	
	private Actor target;
	
	public SniperAimAction(Actor target) {
		this.target = target;
	}
	
	@Override
	public String execute(Actor actor, GameMap map) {
		actor.asPlayer().addAim();
		return actor + " aims at " + target;
	}

	@Override
	public String menuDescription(Actor actor) {
		return actor + " aims at " + target;
	}
	
	@Override
	public Action getNextAction() {
		return new SniperShootAction(target);
	}

}
