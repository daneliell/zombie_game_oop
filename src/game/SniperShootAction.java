package game;

import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

public class SniperShootAction extends AttackAction {
	
	private Actor target;
	private static final int DAMAGE = 30;
	
	public SniperShootAction(Actor target) {
		super(target);
		this.target = target;
	}
	
	@Override
	public String execute(Actor actor, GameMap map) {
		String result = "";
		Item corpse = new PortableItem("dead " + target, '%');
		int aims = actor.asPlayer().getAim();
		if (aims == 0) {
			if (Math.random() < 0.75) {
				result = actor + " snipes " + target + " for " + DAMAGE + " damage.";
				result += performAttack(DAMAGE, map, corpse);
			}
		}
		else if (aims == 1) {
			if (Math.random() < 90) {
				result = actor + " snipes " + target + " for " + DAMAGE*2 + " damage.";
				result += performAttack(DAMAGE*2, map, corpse);
			}
		}
		else {
			result = actor + " snipes " + target + " for an instakill!";
			result += killTarget(map, corpse);
		}
		actor.asPlayer().clearAim();
		List<Item> inventory = actor.getInventory();
		for (Item item : inventory) {
			if (item.asSniper() != null) {
				item.asSniper().reduceAmmo(actor);
			}
		}
		return result;
	}

	@Override
	public String menuDescription(Actor actor) {
		// TODO Auto-generated method stub
		return actor + " shoots " + target;
	}

}
