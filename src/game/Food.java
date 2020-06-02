package game;


import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
/**
 * An Item subclass that can be consumed by the Player to regain HP.
 * 
 * @author Sravan
 *
 */
public class Food extends Item {
	private static final int NUTRIENTS = 10;
	private boolean canEat = true;
	private EatAction eat;
	
	public Food() {
		super("food", 'f', true);
		this.eat = new EatAction(this);
	}
	
	/**
	 * Gets the nutrients of the food item
	 * @return the value of the nutrients of the food item
	 */
	public int getNutrients() {
		return NUTRIENTS;
	}
	
	/**
	 * Tick method for Food when it is in the player's inventory.
	 * 
	 * Allows the Player to eat the food item if the food item is in the Player's inventory.
	 * Creates a new Eat Action.
	 */
	public void tick(Location currentLocation, Actor actor) {
		//Allows the player to eat the food item once held
		if (this.canEat == true) {
			super.allowableActions.add(eat);
			this.canEat = false;
		}
	}
	
	public void tick(Location currentLocation) {
		super.allowableActions.remove(this.eat);
		this.canEat = true;
	}
	
	@Override
	public Item getCraftItem() {
		// TODO Auto-generated method stub
		return null;
	}
}
