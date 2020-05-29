package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;

public abstract class SolidTerrain extends Ground {

	public SolidTerrain(char displayChar) {
		super(displayChar);
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
		return null;
	}

	@Override
	public Boolean isRipe() {
		return null;
	}
}
