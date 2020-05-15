package game;

import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;

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
	
	public void tick(Location location) {
		super.tick(location);
		this.ripeAge--;
		
		if(this.ripeAge == 0) {
			displayChar = 'C';
		}
		
	}

}
