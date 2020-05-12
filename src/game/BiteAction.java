package game;

import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Weapon;

public class BiteAction extends AttackAction{

	private int missChance;
	private int damage;
	private int healthRestored = 5;
	
	public BiteAction(Actor target) {
		super(target);
	}
	
	@Override
	public String execute(Actor actor, GameMap map) {

		if (rand.nextBoolean()) {
			return actor + " misses " + target + ".";
		}

		String result = actor + " bites " + target + " for " + damage + " damage and restores " + healthRestored
				+ " health.";
		
		actor.heal(healthRestored);
		target.hurt(damage);
		if (!target.isConscious()) {
			Item corpse = new PortableItem("dead " + target, '%');
			map.locationOf(target).addItem(corpse);
			
			Actions dropActions = new Actions();
			for (Item item : target.getInventory())
				dropActions.add(item.getDropAction());
			for (Action drop : dropActions)		
				drop.execute(target, map);
			map.removeActor(target);	
			
			result += System.lineSeparator() + target + " is killed.";
		}

		return result;
	}

}
