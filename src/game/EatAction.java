package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

public class EatAction extends Action {
	protected Food food;
	
	public EatAction(Food food) {
		this.food = food;
	}
	
	public String execute(Actor actor, GameMap map) {
		actor.removeItemFromInventory(this.food);
		actor.heal(this.food.getNutrients());
		return actor + " ate some food.";
		
	}
	
	public String menuDescription(Actor actor) {
		return actor + " was healed.";
	}

}
