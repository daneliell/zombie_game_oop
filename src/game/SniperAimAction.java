package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * Action class that spends a turn aiming at the target given.
 * Increases number of aims of the Player. Responsible for its
 * own SniperShootAction against the same target.
 * 
 * @author Daniel Yuen
 *
 */
public class SniperAimAction extends Action {
	
	private Actor target;
	private SniperShootAction shootAction;
	
	/**
	 * Constructor.
	 * 
	 * @param target Actor to be aimed at
	 * @param sniper SniperRifle item instance in the Player's inventory
	 */
	public SniperAimAction(Actor target, SniperRifle sniper) {
		this.target = target;
		this.shootAction = new SniperShootAction(target, sniper);
	}
	
	/**
	 * Adds the number of aims in the Player Actor.
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		actor.asPlayer().addAim();
		return actor + " aims at " + target;
	}

	@Override
	public String menuDescription(Actor actor) {
		return actor + " aims at " + target;
	}
	
	/**
	 * @return SniperShootAction against the same target
	 */
	@Override
	public Action getNextAction() {
		return shootAction;
	}

}
