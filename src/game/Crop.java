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
	private boolean canHarvest = true;
	
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
		if(this.ripeAge == 0 && this.canHarvest == true) {
			displayChar = 'C';
			allowableActions.add(new HarvestAction(this, location));
			this.canHarvest = false;
		}
		
		if (this.ripeAge > 0) {
			this.ripeAge--;
		}
	}
	
	@Override 
	public Actions allowableActions(Actor actor, Location location, String direction){
		return allowableActions;
	}
	
	public boolean blocksThrownObjects() {
		return true;
	}

	@Override
	public Crop asCrop() {
		return this instanceof Crop ? (Crop) this : null;
	}


}



