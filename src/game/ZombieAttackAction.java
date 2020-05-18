package game;

import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Weapon;

/**
 * Attack action for Zombies to choose between a normal attack
 * or a bite attack. Leaves a ZombieCorpse instance if the target
 * is knocked unconscious.
 * 
 * @author Daniel Yuen
 *
 */
public class ZombieAttackAction extends AttackAction{
	
	private double missChance = 0.5;
	private double biteMissChance = 0.6;
	private int biteDamage = 15;
	private int healthRestored = 5;
	private int armsNumber;
	
	public ZombieAttackAction(Actor target, int armsNumber) {
		super(target);
		this.armsNumber = armsNumber;
	}
	
	@Override
	public String execute(Actor actor, GameMap map) {
		String result = "";

		// 50/50 chance to either use normal attacks or a bite attack
		if (armsNumber == 2) {
			if (Math.random() < 50) {
				return normalAttack(actor,map);
			}
			else {
				return biteAttack(actor,map);
			}
		}
		// 30/60 chance to use normal attacks of a bite attack when Zombie only has 1 arm
		else if (armsNumber == 1) {
			if (Math.random() < 30) {
				return normalAttack(actor,map);
			}
			else {
				return biteAttack(actor,map);
			}
		}
		// Does not attack if Zombie has no arms
		else {
			return result;
		}
	}
	
	private String performAttack(int damage, GameMap map) {
		target.hurt(damage);
		if (!target.isConscious()) {
			ZombieCorpse zombieCorpse = new ZombieCorpse(target.toString());
			map.locationOf(target).addItem(zombieCorpse);
			
			Actions dropActions = new Actions();
			for (Item item : target.getInventory())
				dropActions.add(item.getDropAction());
			for (Action drop : dropActions)		
				drop.execute(target, map);
			map.removeActor(target);	
			
			return System.lineSeparator() + target + " is killed.";
		}
		return "";
	}
	
	private String normalAttack(Actor actor, GameMap map) {
		Weapon weapon = actor.getWeapon();
		
		if (Math.random() <= missChance) {
			return actor + " misses " + target + ".";
		}

		int damage = weapon.damage();
		String result = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";
		result += performAttack(biteDamage, map);
		
		return result;
	}
	
	private String biteAttack(Actor actor, GameMap map) {
		if (Math.random() <= biteMissChance) {
			return actor + " misses " + target + ".";
		}

		String result = actor + " bites " + target + " for " + biteDamage + " damage and restores " + healthRestored
				+ " health.";
		
		actor.heal(healthRestored);
		result += performAttack(biteDamage, map);
		return result;
	}
}
