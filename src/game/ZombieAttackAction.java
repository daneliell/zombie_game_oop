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
	
	public ZombieAttackAction(Actor target) {
		super(target);
	}
	
	@Override
	public String execute(Actor actor, GameMap map) {
		String result;
		Random choice = new Random();
		
		// 50/50 chance to either use normal attacks or a bite attack
		if (choice.nextBoolean()) {
			Weapon weapon = actor.getWeapon();
			
			double rand = Math.random(); 
			if (rand <= missChance) {
				return actor + " misses " + target + ".";
			}

			int damage = weapon.damage();
			result = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";

			result += System.lineSeparator() + performAttack(damage, map);
			}
		else {
			double rand = Math.random(); 
			if (rand <= biteMissChance) {
				return actor + " misses " + target + ".";
			}

			result = actor + " bites " + target + " for " + biteDamage + " damage and restores " + healthRestored
					+ " health.";
			
			actor.heal(healthRestored);
			result += System.lineSeparator() + performAttack(biteDamage, map);
			}
		return result;
	}
	
	public String performAttack(int damage, GameMap map) {
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
		}
		return target + "is killed.";
	}
}
