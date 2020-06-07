package game;


import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Menu;
import edu.monash.fit2099.engine.NumberRange;

public class SniperAction extends Action {
	
	private Actions actions = new Actions();
	private Display display;
	private SniperRifle sniper;
	private Menu menu = new Menu();
	private Action selectedAction;
	
	public SniperAction(Display display, SniperRifle sniper) {
		this.display = display;
		this.sniper = sniper;
	}
	
	@Override
	public String execute(Actor actor, GameMap map) {
		// Checks if previous Action is a Snipe action
		/*if (selectedAction != null) {
			actions.clear();
			if (actor.asPlayer().getAim() < 2) {
				actions.add(selectedAction);
			}
			actions.add(selectedAction.getNextAction());
			Action action = menu.showMenu(actor, actions, display);
			return action.execute(actor, map);
		}*/
		
		// Checks if Player has aimed before this
		if (actor.asPlayer().getAim() > 0) {
			// Remove the aim Action if reached 2 aims against same target
			/*if (actor.asPlayer().getAim() >= 2) {
				actions.remove(selectedAction);
			}*/
			Action nextAction = menu.showMenu(actor, actions, display);
			// If next Action is not an aim or shoot Action on target, clear all aims
			if ((nextAction != selectedAction) & (nextAction != selectedAction.getNextAction())) {
				/*actions.remove(selectedAction.getNextAction());
				actions.add(selectedAction);
				actions.add(selectedAction.getNextAction());*/
				actor.asPlayer().clearAim();
			}
			selectedAction = nextAction;
			return nextAction.execute(actor, map);
		}

		NumberRange xRange = map.getXRange();
		NumberRange yRange = map.getYRange();
		boolean hasActor = false;
		
		for (int x : xRange) {
			for (int y : yRange) {
				if ((map.at(x, y).containsAnActor()) && (map.at(x, y).getActor().hasCapability(ZombieCapability.UNDEAD))) {
					SniperAimAction aimAction = new SniperAimAction(map.at(x, y).getActor(), sniper);
					actions.add(aimAction);
					actions.add(aimAction.getNextAction());
					hasActor = true;
				}
			}
		}
		if (hasActor == false) {
			actions.add(new DoNothingAction());
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
