package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Weapon;

/**
 * Attack action for Zombies to choose between a normal attack
 * or a bite attack. Leaves a ZombieCorpse instance if the target
 * is dead.
 * 
 * @author Daniel Yuen
 *
 */
public class ZombieAttackAction extends AttackAction{
	
	private static final double MISS_CHANCE = 0.5;
	private static final double BITE_MISS_CHANCE = 0.4;
	private static final int BITE_DAMAGE = 15;
	private static final int HEALTH_RESTORED = 5;
	private int armsNumber;
	
	/**
	 * Constructor.
	 * 
	 * Sets the armsNumber attribute accordingly.
	 * 
	 * @param target The Actor to attack
	 * @param armsNumber Number of arms attached to the Zombie on attack
	 */
	public ZombieAttackAction(Actor target, int armsNumber) {
		super(target);
		this.armsNumber = armsNumber;
	}
	
	/**
	 * Performs either a normal attack or a bite attack based on the 
	 * number of arms attached to the Zombie when attacking target.
	 * With 2 arms, has an equal chance to use normal attacks. With 1
	 * arm, chance is reduced by half. With no arms, the Zombie always
	 * uses bite attacks.
	 * 
	 * @return String containing the result either of a normal attack or bite attack
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		// 50/50 chance to either use normal attacks or a bite attack
		if (armsNumber == 2) {
			if (Math.random() < 50) {
				return normalAttack(actor,map);
			}
			else {
				return biteAttack(actor,map);
			}
		}
		// 25/75 chance to use normal attacks of a bite attack when Zombie only has 1 arm
		else if (armsNumber == 1) {
			if (Math.random() < 25) {
				return normalAttack(actor,map);
			}
			else {
				return biteAttack(actor,map);
			}
		}
		// always use bite attacks when Zombie has no arms
		else {
			return biteAttack(actor,map);
		}
	}
	
	/**
	 * A normal attack using either the weapon the Zombie is holding
	 * or by punching. Places a ZombieCorpse instance if the target
	 * is killed.
	 * 
	 * @param actor Actor to be hit
	 * @param map The GameMap containing the Actor
	 * @return String result of the attack
	 */
	private String normalAttack(Actor actor, GameMap map) {
		Weapon weapon = actor.getWeapon();
		
		if (Math.random() < MISS_CHANCE) {
			return actor + " misses " + target + ".";
		}

		int damage = weapon.damage();
		String result = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";
		ZombieCorpse zombieCorpse = new ZombieCorpse(target.toString());
		result += performAttack(damage, map, zombieCorpse);
		
		return result;
	}
	
	/**
	 * A bite attack that restores health. Places a ZombieCorpse
	 * instance if the target is killed.
	 * 
	 * @param actor Actor to be bitten
	 * @param map The GameMap containing the Actor
	 * @return String result of the attack
	 */
	private String biteAttack(Actor actor, GameMap map) {
		if (Math.random() < BITE_MISS_CHANCE) {
			return actor + " misses " + target + ".";
		}

		String result = actor + " bites " + target + " for " + BITE_DAMAGE + " damage and restores " + HEALTH_RESTORED
				+ " health.";
		
		actor.heal(HEALTH_RESTORED);
		ZombieCorpse zombieCorpse = new ZombieCorpse(target.toString());
		result += performAttack(BITE_DAMAGE, map, zombieCorpse);
		return result;
	}
}
