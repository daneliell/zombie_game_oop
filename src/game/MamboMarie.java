package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

public class MamboMarie extends ZombieActor {
	private Behaviour behaviour = new WanderBehaviour();
	private int chantCounter = 0;
	private int spawnCounter = 0;
	private GameMap currentMap;
	
	public MamboMarie() {
		super("Mambo Marie", 'M', 100, ZombieCapability.UNDEAD);
	}
	
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		currentMap = map;
		this.spawnCounter++;
		if(this.chantCounter == 10) {
			this.chantCounter = 0;
			return new ChantAction();
		}
		else {
			this.chantCounter++;
			return behaviour.getAction(this, map);
		}
	}
	
	public int getSpawnCounter() {
		return this.spawnCounter;
	}
	
	public GameMap getCurrentMap() {
		return this.currentMap;
	}

	@Override
	public String loseLimb(GameMap map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addAim() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clearAim() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getAim() {
		// TODO Auto-generated method stub
		return 0;
	}
}
