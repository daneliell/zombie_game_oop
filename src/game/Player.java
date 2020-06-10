package game;


import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Menu;

/**
 * Class representing the Player.
 */
public class Player extends Human {

	private Menu menu = new Menu();
	private int aims = 0;
	private int prevHitPoints = this.hitPoints;

	/**
	 * Constructor.
	 *
	 * @param name        Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param hitPoints   Player's starting number of hitpoints
	 */
	public Player(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints);
	}

	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		actions.add(new QuitGameAction());
		// If Player takes damage, clear aims 
		if (prevHitPoints > this.hitPoints) {
			this.clearAim();
		}
		prevHitPoints = this.hitPoints;
		
		// Checks if Player is holding a GunItem which is loaded, then add
		// the respective Action
		for (Item item : this.getInventory()) {
			if (item.asGunItem() != null) {
				if (item.asGunItem().getAmmo(this) != null) {
					if (aims > 0) {
						// Handle multi-turn Actions
						// Adds the previous SniperAction that was used
						if (lastAction.getNextAction() != null) {
							actions.add(lastAction.getNextAction());
							return menu.showMenu(this, actions, display);
						}
						// If Player does not use any SniperAction, clear aims
						else {
							this.clearAim();
						}
					}
					actions.add(item.asGunItem().getAction(display));
				}
			}
		}
		
		return menu.showMenu(this, actions, display);
	}
	
	/**
	 * Public getter method to get number of aims.
	 * 
	 * @return number of turns spent aiming by the Player
	 */
	public int getAim() {
		return aims;
	}
	
	/**
	 * Public mutator method to add number of aims.
	 */
	public void addAim() {
		aims++;
	}
	
	/**
	 * Public mutator method to clear number of aims.
	 */
	public void clearAim() {
		aims = 0;
	}
	
	/**
	 * Public mutator method to increase maximum hitpoints.
	 * 
	 * @param points Number of hitpoints to increase max life.
	 */
	public void incMaxHealth(int points) {
		convertPercentage(hitPoints, maxHitPoints, (maxHitPoints+points));
		maxHitPoints += points;
	}
	
	/**
	 * Public mutator method to decrease maximum hitpoints.
	 * 
	 * @param points Number of hitpoints to decrease max life.
	 */
	public void decMaxHealth(int points) {
		convertPercentage(hitPoints, maxHitPoints, (maxHitPoints-points));
		maxHitPoints -= points;
	}
	
	/**
	 * Internal method to convert hitpoints percentage and change the hitpoints
	 * accordingly.
	 * 
	 * @param hitPoints Current hitpoints of the Player
	 * @param maxHitPoints Maximum hitpoints of the Player before change
	 * @param newMaxHitPoints Maximum hitpoints of the Player after change
	 */
	private void convertPercentage(int hitPoints, int maxHitPoints, int newMaxHitPoints) {
		int healthPercentage = (hitPoints*100)/maxHitPoints;
		this.hitPoints = (newMaxHitPoints*healthPercentage/100);
	}
}
