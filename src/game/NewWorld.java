package game;

import java.util.Objects;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.World;

/**
 * 
 * @author Sravan
 * 
 * A subclass of the World class. Ends the game when the player wins, loses or quits.
 *
 */

public class NewWorld extends World {
	private int zombies = 0;
	private int humans = 0;

	public NewWorld(Display display) {
		super(display);
	}
	
	/**
	 * Loop that runs the game. Loop is broken if the Player wins, loses or quits.
	 */
	public void run() {
		if (player == null)
			throw new IllegalStateException();

		// initialize the last action map to nothing actions;
		for (Actor actor : actorLocations) {
			lastActionMap.put(actor, new DoNothingAction());
		}
		
		int gameStatus = 1;
		// This loop is basically the whole game
		while (stillRunning()) {
			GameMap playersMap = actorLocations.locationOf(player).map();
			playersMap.draw(display);
			
			// Player quits the game
			if(super.lastActionMap.get(player) instanceof QuitGameAction) {
				gameStatus = 0;
				break;
			}
			
			// Is the Player in the Compound
			if (playersMap instanceof CompoundMap) {
				// Have all the Humans died?
				if (getHumanNumber((CompoundMap) playersMap) == 0) {
					gameStatus = 1;
					break;
				} 
				// Have all the zombies and Mambo Marie died?
				else if (!getMamboStatus((CompoundMap) playersMap) && getZombieNumber((CompoundMap) playersMap) == 0) {
					gameStatus = 2;
					break;
				}
				// Process all the actors.
				for (Actor actor : actorLocations) {
					if (stillRunning())
						processActorTurn(actor);
					if(lastActionMap.get(actor) instanceof QuitGameAction) {
						break;
					}
				}
				// Tick over all the maps. For the map stuff.
				for (GameMap gameMap : gameMaps) {
					gameMap.tick();
				} 
			}
			else {
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

		}
		display.println(endingMessage(gameStatus));
	}
	
	/**
	 * 
	 * @param map game map the Player is on
	 * @return number of zombies on the map
	 */
	private int getZombieNumber(CompoundMap map) {
		return map.getZombieNumber();
	}
	
	/**
	 * 
	 * @param map game map the player is on
	 * @return number of humans on the map excluding the player
	 */
	private int getHumanNumber(CompoundMap map) {
		return map.getHumanNumber();
	}
	
	/**
	 * 
	 * @param cond key for the message depending if the player won, lost or quit
	 * @return end game message
	 */
	protected String endingMessage(int cond) {
		if(cond == 1) {
			return "Player Loses. Game Over";
		}
		else if(cond == 2) {
			return "Player Wins. Game Over";
		}
		else {
			return "Game Over";
		}

	}
	
	/**
	 * 
	 * @param map map the player is on
	 * @return is Mambo Marie alive?
	 */
	private boolean getMamboStatus(CompoundMap map) {
		return map.getMamboStatus();
	}
}
