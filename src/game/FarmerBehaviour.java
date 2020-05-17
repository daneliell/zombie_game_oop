package game;

import java.util.ArrayList;
import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;

public class FarmerBehaviour implements Behaviour {
	
	public FarmerBehaviour() {
		
	}
	
	public Action getAction(Actor actor, GameMap map) {
		ArrayList<Action> actions = new ArrayList<Action>();
		
		Location currentLocation = map.locationOf(actor);
		Location nextLocation = new Location(map, currentLocation.x()+1, currentLocation.y());
		Crop currentCrop = (Crop) currentLocation.getGround();
		Crop nextCrop = (Crop) nextLocation.getGround();
		
		Random rand = new Random();
		int sowProbability = rand.nextInt((100 - 1) + 1) + 1;
		if(sowProbability <= 33 && nextLocation.getGround() instanceof Dirt) {
			actions.add(new SowAction(nextLocation));
			
		}
		
		if(currentLocation.getGround() instanceof Crop && !currentCrop.isRipe()) {
			actions.add(new FertilizeAction((Crop) currentLocation.getGround()));
			
		}
		
		if(currentLocation.getGround() instanceof Crop && currentCrop.isRipe()) {
			actions.add(new HarvestAction((Crop) currentLocation.getGround(), currentLocation));
		}
		else if(nextLocation.getGround() instanceof Crop && nextCrop.isRipe()) {
			actions.add(new HarvestAction((Crop) nextLocation.getGround(), nextLocation));
		}
		
		if (!actions.isEmpty()) {
			Random random = new Random();
			return actions.get(random.nextInt(actions.size()));
		}
		else {
			return null;
		}
		
	}

}
