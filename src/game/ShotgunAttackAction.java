package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

public class ShotgunAttackAction extends AttackAction {
	
	private static final double MISS_CHANCE = 0.25;
	private static final int DAMAGE = 40;
	
	public ShotgunAttackAction(Actor target) {
		super(target);
	}
	
	@Override
	public String execute(Actor actor, GameMap map) {
		// Has a 25% chance of missing target
		if (Math.random() < MISS_CHANCE) {
			return actor + "'s bullets miss " + target + ".";
		}
		
		String result = actor + " blasts " + target + " for " + DAMAGE + " damage.";
		target.hurt(DAMAGE);
		
		Item corpse = new PortableItem("dead " + target, '%');
		result += performAttack(DAMAGE, map, corpse);
		
		return result;
	}

}
