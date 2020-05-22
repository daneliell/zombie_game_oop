package game;

/**
 * @author Sravan
 * Eating action for the Player
 * to consume a Food item
 * and regain health
 */
import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

public class EatAction extends Action {
	private Food food;
	
	/**
	 * Constructor.
	 * 
	 * @param food the Food item that is to be consumed
	 */
	public EatAction(Food food) {
		this.food = food;
	}
	
	/**
	 * Executes the action of eating food.
	 * 
	 * The Food item is removed from the
	 * actor's inventory
	 * The actor regains HP which is
	 * the same amount of nutrients of the Food item
	 */
	public String execute(Actor actor, GameMap map) {
		//Food is eaten 
		actor.removeItemFromInventory(this.food);
		//Actor regains HP
		actor.heal(food.getNutrients());
		return actor + " eats some " + this.food + " and restores " + food.getNutrients() + " hitpoints!";
	}
	
	public String menuDescription(Actor actor) {
		return actor + " eats some " + this.food;
	}

}
