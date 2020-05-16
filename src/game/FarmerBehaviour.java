package game;

import java.util.ArrayList;
import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

public class FarmerBehaviour implements Behaviour {
	private int minProb = 1;
	private int maxProb = 100;
	private int sowProbability;
	
	public FarmerBehaviour() {
		Random rand = new Random();
		sowProbability = rand.nextInt((maxProb - minProb) + 1) + minProb;
	}
	
	public Action getAction(Actor actor, GameMap map) {
		ArrayList<Action> actions = new ArrayList<Action>();
		
		Location farmerLocation = map.locationOf(actor);
		Location cropLocation = new Location(map, farmerLocation.x()+1, farmerLocation.y());
		
		if(this.sowProbability <= 33) {
			actions.add(new SowAction(cropLocation));
			
		}
		
		if(farmerLocation.getDisplayChar() == 'c') {
			Crop crop = farmerLocation.getGround();
			}
		
	}
	
	
	

}
