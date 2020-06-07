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
		// Handle multi-turn Actions
		// If Player takes damage or chooses a non-aim Action, clear aims 
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
						if (lastAction.getNextAction() != null) {
							actions.add(lastAction.getNextAction());
							return menu.showMenu(this, actions, display);
						}
						else {
							this.clearAim();
						}
					}
					actions.add(item.asGunItem().getAction(display));
				}
			}
		}
		System.out.println(this.maxHitPoints);
		System.out.println(this.hitPoints);
		
		return menu.showMenu(this, actions, display);
	}
	
	public int getAim() {
		return aims;
	}
	
	public void addAim() {
		aims++;
	}
	
	public void clearAim() {
		aims = 0;
	}
	
	public void incMaxHealth(int points) {
		maxHitPoints += points;
		hitPoints += points;
	}
	
	public void decMaxHealth(int points) {
		maxHitPoints -= points;
		hitPoints -= points;
		if (hitPoints <= 0) {
			hitPoints = 1;
		}
	}
}
