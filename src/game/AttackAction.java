package game;

import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Weapon;

/**
 * Special Action for attacking other Actors.
 */
public class AttackAction extends Action {

	/**
	 * The Actor that is to be attacked
	 */
	protected Actor target;
	/**
	 * Random number generator
	 */
	protected Random rand = new Random();

	/**
	 * Constructor.
	 * 
	 * @param target the Actor to attack
	 */
	public AttackAction(Actor target) {
		this.target = target;
	}
	
	/**
	 * Have a chance to cause Zombies to lose their limbs on hit.
	 */
	@Override
	public String execute(Actor actor, GameMap map) {

		Weapon weapon = actor.getWeapon();

		if (rand.nextBoolean()) {
			return actor + " misses " + target + ".";
		}

		int damage = weapon.damage();
		String result = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";

		Item corpse = new PortableItem("dead " + target, '%');
		result += performAttack(damage, map, corpse);
		
		return result;
	}

	@Override
	public String menuDescription(Actor actor) {
		return actor + " attacks " + target; 
	}
	
	/**
	 * Damages the target and checks if the target is still alive. If not,
	 * a corpse Item is added in its place.
	 * 
	 * @param damage Amount of damage done to the target
	 * @param map The GameMap containing the target
	 * @param corpse Corpse Item to be left at target location
	 * @return String with the result if the target is killed
	 */
	protected String performAttack(int damage, GameMap map, Item corpse) {
		String result = "";
		target.hurt(damage);
		
		// Calls the loseLimb method if the attacked Actor is a Zombie
		if (target.hasCapability(ZombieCapability.UNDEAD)) {
			result += target.loseLimb(map);
		}
		
		if (!target.isConscious()) {
			map.locationOf(target).addItem(corpse);
			
			Actions dropActions = new Actions();
			for (Item item : target.getInventory())
				dropActions.add(item.getDropAction());
			for (Action drop : dropActions)		
				drop.execute(target, map);
			map.removeActor(target);	
			
			result += System.lineSeparator() + target + " is killed.";
			return result;
		}
		return result;
	}
}
