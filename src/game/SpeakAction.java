package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

public class SpeakAction extends Action {
	
	protected String dialogue;
	protected double dialogueChance;
	
	public SpeakAction(String dialogue, double dialogueChance) {
		this.dialogue = dialogue;
	}
	
	@Override
	public String execute(Actor actor, GameMap map) {
		return actor + ": " + dialogue;
	}

	@Override
	public String menuDescription(Actor actor) {
		return null;
	}
}

