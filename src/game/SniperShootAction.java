package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

public class SniperShootAction extends AttackAction {
	
	private Actor target;
	private static final int DAMAGE = 50;
	
	public SniperShootAction(Actor target) {
		super(target);
		this.target = target;
	}
	
	@Override
	public String execute(Actor actor, GameMap map) {
		String result = "";
		Item corpse = new PortableItem("dead " + target, '%');
		if (actor.getAim() == 0) {
			if (Math.random() < 0.75) {
				result = actor + " blasts " + target + " for " + DAMAGE + " damage.";
				result += performAttack(DAMAGE, map, corpse);
			}
		}
		else if (actor.getAim() == 1) {
			if (Math.random() < 90) {
				result = actor + " blasts " + target + " for " + DAMAGE*2 + " damage.";
				result += performAttack(DAMAGE*2, map, corpse);
			}
		}
		else {
			result = actor + " blasts " + target + " for an instakill!";
			result += performAttack(1000000, map, corpse);
		}
		actor.clearAim();
		return result;
	}

	@Override
	public String menuDescription(Actor actor) {
		// TODO Auto-generated method stub
		return actor + " shoots " + target;
	}

}
