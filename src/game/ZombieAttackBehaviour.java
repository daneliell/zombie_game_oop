package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;

public class ZombieAttackBehaviour extends AttackBehaviour{
	private String zombieDialogue = "Braaaaaaaains...";
	private double dialogueChance = 0.1;
	
	@Override
	public Action getAction(Actor actor, GameMap map) {
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
