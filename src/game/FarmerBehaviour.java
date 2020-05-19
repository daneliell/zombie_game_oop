package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;

public class FarmerBehaviour implements Behaviour {
	
	public FarmerBehaviour() {
	}
	
	public Action getAction(Actor actor, GameMap map) {
		Location actorLocation = map.locationOf(actor);
		
		List<Exit> exits = new ArrayList<Exit>(map.locationOf(actor).getExits());
		Collections.shuffle(exits);
		
		if(Math.random()<33) {
			for(Exit exit: exits) {
				Ground ground = exit.getDestination().getGround();
				if(ground == new Dirt()) {
					return new SowAction(exit.getDestination());
				}
			}
		}
		
		if(actorLocation.getGround().asCrop() != null) {
			return new FertilizeAction(map.locationOf(actor).getGround().asCrop());
		}
		
		
		if(actorLocation.getGround().asCrop() != null & actorLocation.getGround().asCrop().isRipe()) {
			return new HarvestAction(actorLocation.getGround().asCrop(), actorLocation);
		}
		else {
			for(Exit exit: exits) {
				Ground ground = exit.getDestination().getGround();
				if(ground.asCrop().isRipe()) {
					return new HarvestAction(ground.asCrop(), exit.getDestination());
				}
			}
		}
		return null;
	}
}