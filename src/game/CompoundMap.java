package game;

import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.GroundFactory;

public class CompoundMap extends GameMap {
	private boolean MamboStatus;
	
	public CompoundMap(GroundFactory groundFactory, char groundChar, int width, int height) {
		super(groundFactory, groundChar, width, height);
		this.MamboStatus = true;
	}
	
	public void setMamboStatus(boolean status) {
		this.MamboStatus = status;
	}
	
	public boolean getMamboStatus() {
		return this.MamboStatus;
	}
}
