package game;

import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
/**
 * 
 * @author Sravan
 *
 */
public class Food extends Item {
	protected int nutrients = 10;
	private boolean canEat = true;
	
	public Food() {
		super("food", 'f', true);
	}
	
	public int getNutrients() {
		return this.nutrients;
	}
	
	public void tick(Location currentLocation, Actor actor) {
		if (this.canEat == true) {
			super.allowableActions.add(new EatAction(this));
			this.canEat = false;
		}
		}

	@Override
	public Item getCraftItem() {
		// TODO Auto-generated method stub
		return null;
	}
}
