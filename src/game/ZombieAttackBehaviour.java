package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Weapon;
import edu.monash.fit2099.engine.PickUpItemAction;

public class ZombieAttackBehaviour extends AttackBehaviour{
	
	public ZombieAttackBehaviour(ZombieCapability attackableTeam) {
		super(attackableTeam);
	}
	
	@Override
	public Action getAction(Actor actor, GameMap map) {
		// Is there an item at my location?
		List<Item> items = new ArrayList<Item>(map.locationOf(actor).getItems());
		for (Item item : items) {
			if (item.asWeapon() != null) {
				return new PickUpItemAction(item);
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
}
