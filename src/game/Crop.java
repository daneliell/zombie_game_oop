package game;

import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.Weapon;

public class Crop extends Ground {
	private int ripeAge = 20;
	
	public Crop() {
		super('c');
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
	
	public void tick(Location location) {
		super.tick(location);
		this.ripeAge--;
		
		if(this.ripeAge == 0) {
			displayChar = 'C';
		}
		
	}

	@Override
	public Crop asCrop() {
		return this instanceof Crop ? (Crop) this : null;
	}


}
