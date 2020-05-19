package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;

/**
 * An impenetrable barrier.
 * 
 * @author ram
 *
 */
public class Fence extends Ground {

	public Fence() {
		super('#');
	}
	
	@Override
	public boolean canActorEnter(Actor actor) {
		return false;
	}
	
	@Override
	public boolean blocksThrownObjects() {
		return true;
	}

	@Override
	public Crop asCrop() {
		// TODO Auto-generated method stub
		return null;
	}
}
