package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

/**
 * Action class that shoots the given target based on the number of aims
 * the Player has.
 *  
 * @author Daniel Yuen
 *
 */
public class SniperShootAction extends AttackAction {
	
	private Actor target;
	private static final int DAMAGE = 30;
	private static final double NO_AIM_CHANCE = 0.75;
	private static final double AIM_CHANCE = 0.9;
	private SniperRifle sniper;
	
	/**
	 * Constructor.
	 * 
	 * @param target Actor to be shot
	 * @param sniper SniperRifle item instance in the Player's inventory
	 */
	public SniperShootAction(Actor target, SniperRifle sniper) {
		super(target);
		this.target = target;
		this.sniper = sniper;
	}
	
	/*
	 * Damages the target based on the number of aims the Player has.
	 * 
	 * @return result of the shot
	 */
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
			actor.asPlayer().clearAim();
			result += killTarget(map, corpse);
		}
		return result;
	}
	
	/**
	 * Internal method to shoot the target and return the result.
	 * 
	 * @param aimChance Chance to hit the target
	 * @param damage Damage the shot deals
	 * @param map The map where the target is
	 * @param corpse Corpse Item to be dropped on Actor death
	 * @param actor Actor performing the shot
	 * @param target Actor to be hit by the shot
	 * @return result of the shot
	 */
	private String shootTarget(double aimChance, int damage, GameMap map, Item corpse, Actor actor, Actor target) {
		if (Math.random() < aimChance) {
			String result = actor + " snipes " + target + " for " + damage + " damage.";
			result += performAttack(damage, map, corpse);
			if (target.isConscious() == false) {
				actor.asPlayer().clearAim();
			}
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
