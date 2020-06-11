package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * 
 * @author Action to quit the game players inputs "Q".
 *
 */
public class QuitGameAction extends Action {

	@Override
	public String execute(Actor actor, GameMap map) {
		return menuDescription(actor);
	}

	@Override
	public String menuDescription(Actor actor) {
		return actor + " quit the game";
	}
	
	/**
	 * Game ends when player inputs the hotkey "Q"
	 */
	public String hotkey() {
		return "Q";
	}
}
