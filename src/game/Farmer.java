package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;
/**
 * A Human subclass that act as Farmers.
 * 
 * Farmers follow the Wander Behaviour like regular humans and also follow a Farmer Behaviour.
 * The Farmer Behaviour is given the higher priority.
 * 
 * @author Sravan
 *
 */
public class Farmer extends Human {
	private Behaviour[] behaviours = {new FarmerBehaviour(),
									  new WanderBehaviour()};

	protected Farmer(String name) {
		super(name, 'F', 50);
	}
	
	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		for (Behaviour behaviour : behaviours) {
			Action action = behaviour.getAction(this, map);
			if (action != null)
				return action;
		}
		return new DoNothingAction();
	}
}
