package game;

import java.util.ArrayList;
import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

/**
 * Class representing an ordinary human.
 * 
 * 
 * @author ram
 *
 */
public class Human extends ZombieActor {
	private Behaviour behaviour = new WanderBehaviour();

	/**
	 * The default constructor creates default Humans
	 * 
	 * @param name the human's display name
	 */
	public Human(String name) {
		super(name, 'H', 50, ZombieCapability.ALIVE);
	}
	
	/**
	 * The protected constructor can be used to create subtypes
	 * of Human, such as the Player
	 * 
	 * @param name the human's display name
	 * @param displayChar character that will represent the Human in the map 
	 * @param hitPoints amount of damage that the Human can take before it dies
	 */
	protected Human(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints, ZombieCapability.ALIVE);
	}

	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		// FIXME humans are pretty dumb, maybe they should at least run away from zombies?
		// Is there food at my location for me to eat?
		List<Item> items = new ArrayList<Item>(map.locationOf(this).getItems());
		for (Item item : items) {
			if (item.asFood() != null) {
				return item.getPickUpAction();
			}
		}
		// Am I injured?
		if (this.hitPoints < this.maxHitPoints) {
			for (Item food : this.getInventory()) {
				if(food.asFood() != null) {
					return new EatAction(food.asFood());
				}
			}
		}
		return behaviour.getAction(this, map);
	}

	@Override
	public String loseLimb(GameMap map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addAim() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clearAim() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getAim() {
		// TODO Auto-generated method stub
		return 0;
	}
}
