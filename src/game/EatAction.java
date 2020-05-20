package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

public class EatAction extends Action {
	private Food food;
	
	public EatAction(Food food) {
		this.food = food;
	}
	
	public String execute(Actor actor, GameMap map) {
		actor.removeItemFromInventory(this.food);
		actor.heal(food.getNutrients());
		return actor + " eats some " + this.food + " and restores " + food.getNutrients() + " hitpoints!";
	}
	
	public String menuDescription(Actor actor) {
		return actor + " eats some " + this.food;
	}

}
