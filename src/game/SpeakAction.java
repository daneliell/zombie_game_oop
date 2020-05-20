package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * Action to return dialogue spoken by an Actor.
 * 
 * @author Daniel Yuen
 *
 */
public class SpeakAction extends Action {
	private String dialogue;
	
	/**
	 * Constructor.
	 * 
	 * @param dialogue String of dialogue to be spoken by an Actor.
	 */
	public SpeakAction(String dialogue) {
		this.dialogue = dialogue;
	}
	
	/**
	 * @param actor Actor which says the dialogue
	 * @param map The GameMap containing the actor
	 * @return String containing the actor and spoken dialogue
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		return actor + ": " + dialogue;
	}

	@Override
	public String menuDescription(Actor actor) {
		return null;
	}
}

