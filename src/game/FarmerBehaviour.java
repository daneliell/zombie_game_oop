package game;

import java.util.ArrayList;
import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;

public class FarmerBehaviour implements Behaviour {
	private int minProb = 1;
	private int maxProb = 100;
	private int sowProbability;
	private Random random;
	
	public FarmerBehaviour() {
		Random rand = new Random();
		sowProbability = rand.nextInt((maxProb - minProb) + 1) + minProb;
	}
	
	public Action getAction(Actor actor, GameMap map) {
		ArrayList<Action> actions = new ArrayList<Action>();
		
		Location farmerLocation = map.locationOf(actor);
		Location nextLocation = new Location(map, farmerLocation.x()+1, farmerLocation.y());
		
		if(this.sowProbability <= 33) {
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
			return actions.get(random.nextInt(actions.size()));
		}
		else {
			return null;
		}
		
	}

}
