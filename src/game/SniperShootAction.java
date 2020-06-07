package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

public class SniperShootAction extends AttackAction {
	
	private Actor target;
	private static final int DAMAGE = 30;
	private static final double NO_AIM_CHANCE = 0.75;
	private static final double AIM_CHANCE = 0.9;
	private SniperRifle sniper;
	
	public SniperShootAction(Actor target, SniperRifle sniper) {
		super(target);
		this.target = target;
		this.sniper = sniper;
	}
	
	@Override
	public String execute(Actor actor, GameMap map) {
		String result = "";
		Item corpse = new PortableItem("dead " + target, '%');
		int aims = actor.asPlayer().getAim();
		sniper.reduceAmmo(actor);
		if (aims == 0) {
			result += shootTarget(NO_AIM_CHANCE, DAMAGE, map, corpse, actor, target);
		}
		else if (aims == 1) {
			result += shootTarget(AIM_CHANCE, DAMAGE*2, map, corpse, actor, target);
		}
		else {
			result = actor + " snipes " + target + " for an instant kill!";
			result += killTarget(map, corpse);
		}
		return result;
	}
	
	private String shootTarget(double aimChance, int damage, GameMap map, Item corpse, Actor actor, Actor target) {
		if (Math.random() < aimChance) {
			String result = actor + " snipes " + target + " for " + damage + " damage.";
			result += performAttack(damage, map, corpse);
			return result;
		}
		else {
			return actor + " misses " + target;
		}
	}

	@Override
	public String menuDescription(Actor actor) {
		// TODO Auto-generated method stub
		return actor + " shoots " + target;
	}

}
