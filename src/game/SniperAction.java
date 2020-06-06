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
import edu.monash.fit2099.engine.NumberRange;

public class SniperAction extends Action {
	
	private Actions actions = new Actions();
	Display display;
	Menu menu = new Menu();
	Action selectedAction;
	
	public SniperAction(Display display) {
		this.display = display;
	}
	
	@Override
	public String execute(Actor actor, GameMap map) {
		if (selectedAction != null) {
			actions.clear();
			if (actor.getAim() < 3) {
				actions.add(selectedAction);
			}
			actions.add(selectedAction.getNextAction());
			Action action = menu.showMenu(actor, actions, display);
			return action.execute(actor, map);
		}

		NumberRange xRange = map.getXRange();
		NumberRange yRange = map.getYRange();

		for (int x : xRange) {
			for (int y : yRange) {
				if ((map.at(x, y).containsAnActor()) && (map.at(x, y).getActor().hasCapability(ZombieCapability.UNDEAD))) {
					actions.add(new SniperAimAction(map.at(x, y).getActor()));
					actions.add(new SniperShootAction(map.at(x, y).getActor()));
				}
			}
		}
		selectedAction = menu.showMenu(actor, actions, display);
		return selectedAction.execute(actor, map);
	}
	
	@Override
	public String menuDescription(Actor actor) {
		return actor + " aims the sniper"; 
	}
	
	@Override
	public Action getNextAction() {
		return this;
	}

}
