package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;

/**
 * A class that generates an AttackAction if the current Actor is standing
 * next to an Actor that they can attack.
 * 
 * @author ram
 *
 */
public class AttackBehaviour implements Behaviour {
	protected ZombieCapability attackableTeam;
	
	/**
	 * Constructor.
	 * 
	 * Sets the team (i.e. ZombieCapability) that the owner of this
	 * Behaviour is allowed to attack.
	 * 
	 * @param attackableTeam Team descriptor for ZombieActors that can be attacked
	 */
	public AttackBehaviour(ZombieCapability attackableTeam) {
		this.attackableTeam = attackableTeam;
	}

	/**
	 * Returns an AttackAction that attacks an adjacent attackable Actor.
	 * 
	 * Actors are attackable if their ZombieCapability matches the 
	 * "undeadness status" set 
	 * 
	 * @param actor The actor acting
	 * @param map The GameMap containing the Actor
	 * @return an AttackAction if attacks are possible
	 */
	@Override
	public Action getAction(Actor actor, GameMap map) {
		return getAttackAction(actor, map);
	}
	
	/**
	 * Protected method to check for valid Actors to perform an attack on and
	 * returns an AttackAction if it is possible, null otherwise.
	 * 
	 * @param actor The actor acting
	 * @param map The GameMap containing the Actor
	 * @return an AttackAction if attacks are possible
	 */
	protected Action getAttackAction(Actor actor, GameMap map) {
		// Is there an attackable Actor next to me?
		List<Exit> exits = new ArrayList<Exit>(map.locationOf(actor).getExits());
		Collections.shuffle(exits);

		for (Exit e: exits) {
			if (!(e.getDestination().containsAnActor()))
				continue;
			if (e.getDestination().getActor().hasCapability(attackableTeam)) {
				return new AttackAction(e.getDestination().getActor());
			}
		}
		return null;
	}
}
