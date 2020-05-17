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
		
		Location farmerLocation = map.locationOf(actor);
		Location nextLocation = new Location(map, farmerLocation.x()+1, farmerLocation.y());
		
		Random rand = new Random();
		int sowProbability = rand.nextInt((100 - 1) + 1) + 1;
		if(sowProbability <= 33 && nextLocation.getGround() instanceof Dirt) {
			actions.add(new SowAction(nextLocation));
			
		}
		
		if(farmerLocation.getGround() instanceof Crop) {
			actions.add(new FertilizeAction((Crop) farmerLocation.getGround()));
			
		}
		
		if(farmerLocation.getGround() instanceof Crop) {
			actions.add(new HarvestAction((Crop) farmerLocation.getGround(), farmerLocation));
		}
		else if(nextLocation.getGround() instanceof Crop) {
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
