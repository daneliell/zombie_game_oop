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
		// Handle multi-turn Actions
		if (prevHitPoints > this.hitPoints) {
			this.clearAim();
		}
		prevHitPoints = this.hitPoints;
		
		if (aims > 0) {
			if (lastAction.getNextAction() != null) {
				actions.add(lastAction.getNextAction());
				return menu.showMenu(this, actions, display);
			}
			else {
				this.clearAim();
			}
		}
		/*for (Item item : this.getInventory()) {
			if (item.asShotgun() != null) {
				Shotgun shotgun = item.asShotgun();
				if (shotgun.isLoaded(this)) {
					actions.add(new ShotgunAction(display));
				}
			}
			if (item.asSniper() != null) {
				SniperRifle sniper = item.asSniper();
				if (sniper.getAmmo(this) != null) {
					actions.add(new SniperAction(display));
				}
			}
		}*/
		for (Item item : this.getInventory()) {
			if (item.asGunItem() != null) {
				actions.add(item.asGunItem().getAction(display));
			}
		}
		return menu.showMenu(this, actions, display);
	}
	
	/*public GunItem getGunItem() {
		for (Item item : this.getInventory()) {
			if (item.asGunItem() != null) {
				return item.asGunItem();
			}
		}
		return null;
	}*/
	
	public Ammo getAmmo() {
		return getGunItem().isLoaded(this);
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
}
