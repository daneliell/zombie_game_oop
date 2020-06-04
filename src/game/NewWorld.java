package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.World;

public class NewWorld extends World {
	private int zombies = 0;
	private int humans = 0;

	public NewWorld(Display display) {
		super(display);
	}
	
	public void run() {
		if (player == null)
			throw new IllegalStateException();

		// initialize the last action map to nothing actions;
		for (Actor actor : actorLocations) {
			lastActionMap.put(actor, new DoNothingAction());
		}

		// This loop is basically the whole game
		while (stillRunning()) {
			GameMap playersMap = actorLocations.locationOf(player).map();
			playersMap.draw(display);

			// Process all the actors.
			for (Actor actor : actorLocations) {
				if (stillRunning())
					processActorTurn(actor);
			}

			// Tick over all the maps. For the map stuff.
			for (GameMap gameMap : gameMaps) {
				gameMap.tick();
			}

		}
		display.println(endGameMessage());
	}
	
	private int getZombieNumber(GameMap map) {
		for(Actor actor: actorLocations) {
			if(actor.hasCapability(ZombieCapability.UNDEAD)) {
				zombies++;
			}
		}
		return zombies;
	}
	
	private int getHumanNumber(GameMap map) {
		for(Actor actor: actorLocations) {
			if(actor.hasCapability(ZombieCapability.ALIVE)) {
				humans++;
			}
		}
		return humans;
	}
}
