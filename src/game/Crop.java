package game;

import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;

/**
 * 
 * @author Sravan
 *
 */
public class Crop extends Ground {
	private int ripeAge = 20;
	private Actions allowableActions;
	
	public Crop() {
		super('c');
		allowableActions = new Actions();
	}
	
	public Boolean isRipe() {
		if(this.ripeAge == 0) {
			return true;
		}
		return false;
	}
	
	public int getAge() {
		return this.ripeAge;
	}
	
	public void setAge(int Age) {
		this.ripeAge = Age;
	}
	
	@Override
	public void tick(Location location) {
		super.tick(location);
		this.ripeAge--;
		
		if(this.ripeAge == 0) {
			displayChar = 'C';
			allowableActions.add(new HarvestAction(this, location));
		}
	}
	
	@Override 
	public Actions allowableActions(Actor actor, Location location, String direction){
		return allowableActions;
	}

	@Override
	public Crop asCrop() {
		return this instanceof Crop ? (Crop) this : null;
	}


}
