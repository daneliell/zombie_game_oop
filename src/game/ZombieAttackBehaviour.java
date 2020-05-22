package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

/**
 * A class that either generates a PickUpItemAction if there is an item 
 * where current Zombie is standing or a ZombieAttackAction if there is an
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
	 * If Zombie has at least 1 arm:
	 * Calls the getPickUpAction() method in Item class to return a PickUpItemAction 
	 * that picks up the item at the location of the Zombie if the Item is
	 * a Weapon.
	 * 
	 * If no Item is at location, looks for attackable Actors next to the Zombie and
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
