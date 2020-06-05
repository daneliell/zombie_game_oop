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
		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();
		for (Item item : this.getInventory()) {
			if (item.asShotgun() != null) {
				Shotgun shotgun = item.asShotgun();
				if (shotgun.isLoaded(this)) {
					actions.add(new ShotgunAction(display));
				}
			}
			else if (item.asSniper() != null) {
				SniperRifle sniper = item.asSniper();
				if (sniper.isLoaded(this)) {
					actions.add(new SniperAction(display));
				}
			}
		}
		return menu.showMenu(this, actions, display);
	}
}
