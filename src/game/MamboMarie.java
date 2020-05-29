package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;

public class MamboMarie extends ZombieActor {
	private Behaviour behaviour = new WanderBehaviour();
	private int chantCounter = 0;
	
	public MamboMarie() {
		super("Mambo Marie", 'M', 100, ZombieCapability.UNDEAD);
	}
	
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		if(this.chantCounter == 10) {
			return new ChantAction();
		}
		else {
			this.chantCounter++;
			return behaviour.getAction(this, map);
		}
	}

	@Override
	public String loseLimb(GameMap map) {
		// TODO Auto-generated method stub
		return null;
	}
}
