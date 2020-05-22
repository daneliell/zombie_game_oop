package game;

import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;

/**
 * A class that generates a ZombieAttackAction if there is an
 * attackable Actor standing beside it.
 * 
 * @author Daniel Yuen
 *
 */
public class ZombieAttackBehaviour extends AttackBehaviour{
	private int armsNumber;
	
	/**
	 * Constructor.
	 * 
	 * Calls the parent class constructor to set the team that the
	 * owner of this Behaviour is allowed to attack.
	 * 
	 * @param attackableTeam Team descriptor for ZombieActors that can be attacked
	 * @param armsNumber Number of arms belonging to the owner of the behaviour
	 */
	public ZombieAttackBehaviour(ZombieCapability attackableTeam, int armsNumber) {
		super(attackableTeam);
		this.armsNumber = armsNumber;
	}
	
	/**
 	 * Looks for attackable Actors next to the Zombie and
	 * returns an AttackAction that attacks the Actor.
	 * 
	 */
	@Override
	public Action getAction(Actor actor, GameMap map) {
		// Is there an attackable Actor next to me?
		List<Exit> exits = generateExits(actor, map);

		for (Exit e: exits) {
			if (!(e.getDestination().containsAnActor()))
				continue;
			if (e.getDestination().getActor().hasCapability(attackableTeam)) {
				return new ZombieAttackAction(e.getDestination().getActor(), armsNumber);
			}
		}
		return null;
	}
	
	/**
	 * Public method to reduce the armsNumber by 1
	 */
	public void removeArm() {
		if (armsNumber > 0) {
			armsNumber--;
		}
	}
}
