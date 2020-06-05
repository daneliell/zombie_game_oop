package game;

import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Menu;

public class SniperAction extends Action {
	
	private Actions actions = new Actions();
	Display display;
	Menu menu = new Menu();
	
	public SniperAction(Display display) {
		this.display = display;
	}
	
	@Override
	public String execute(Actor actor, GameMap map) {
		List<Item> inventory = actor.getInventory();

		for (Item item : inventory) {
			if (item.asSniperAmmo() != null) {
				SniperAmmo ammo = item.asSniperAmmo();
				ammo.reduceRounds();
				if (ammo.getRounds() == false) {
					actor.removeItemFromInventory(item);
				}
				
				
				List<Exit> exits = map.locationOf(actor).getExits();
				for (Exit e : exits) {
					actions.add(new ShotgunShootAction(e));
				}
				Action selectedAction = menu.showMenu(actor, actions, display);
				return selectedAction.execute(actor,map);
			}
		}
		return null;
	}
	
	@Override
	public String menuDescription(Actor actor) {
		return actor + " aims the shotgun"; 
	}

}
