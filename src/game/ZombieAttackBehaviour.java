package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.PickUpItemAction;

/**
 * A class that either generates a PickUpItemAction if there is an item 
 * where current Zombie is standing or a ZombieAttackAction if there is an
 * attackable Actor standing beside it.
 * 
 * @author Daniel Yuen
 *
 */
public class ZombieAttackBehaviour extends AttackBehaviour{
	
	private int armsNumber;
	
	public ZombieAttackBehaviour(ZombieCapability attackableTeam) {
		super(attackableTeam);
	}
	
	@Override
	public Action getAction(Actor actor, GameMap map) {
		// Is there a weapon at my location for me to use?
		if (armsNumber != 0) {
			List<Item> items = new ArrayList<Item>(map.locationOf(actor).getItems());
			for (Item item : items) {
				if (item.asWeapon() != null) {
					return new PickUpItemAction(item);
				}
			}
		}
		// Is there an attackable Actor next to me?
		List<Exit> exits = new ArrayList<Exit>(map.locationOf(actor).getExits());
		Collections.shuffle(exits);
		
		for (Exit e: exits) {
			if (!(e.getDestination().containsAnActor()))
				continue;
			if (e.getDestination().getActor().hasCapability(attackableTeam)) {
				return new ZombieAttackAction(e.getDestination().getActor());
			}
		}
		return null;
	}
	
	public void setArms(int newArmsNumber) {
		armsNumber = newArmsNumber;
	}
}
