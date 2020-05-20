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

/**
 * 
 * @author Sravan
 *
 */
public class FarmerBehaviour implements Behaviour {
	
	public FarmerBehaviour() {
	}
	
	public Action getAction(Actor actor, GameMap map) {
		// Is there dirt next to me?
		List<Exit> exits = new ArrayList<Exit>(map.locationOf(actor).getExits());
		Collections.shuffle(exits);
		// Current Location of actor
		Location actorLocation = map.locationOf(actor);
		
		// If the Farmer is standing on a ripe crop, harvest it!
		if(actorLocation.getGround().asCrop() != null) {
			if(actorLocation.getGround().isRipe()) {
				return new HarvestAction(actorLocation.getGround().asCrop(), actorLocation);
			}
		}
		// If there is a ripe crop next to the Farmer, harvest it!
		else {
			for(Exit exit: exits) {
				Ground ground = exit.getDestination().getGround();
				if(ground.asCrop() != null)
					if(ground.isRipe()) {
						return new HarvestAction(ground.asCrop(), exit.getDestination());
					}
			}
		}
		// Is there dirt next to the Farmer to sow?
		if (Math.random() < 0.33) {
			for(Exit exit: exits) {
				Ground ground = exit.getDestination().getGround();
				if (ground instanceof Dirt) {
					return new SowAction(exit.getDestination());
				}
			}
		}
		// Is there an unripe crop to fertilize?
		if (actorLocation.getGround().asCrop() != null) {
			// Is there an unripe crop next to me?
			if (actorLocation.getGround().isRipe() == false) {
				return new FertilizeAction(actorLocation.getGround().asCrop());
			}
		}
		
		return null;
	}
}