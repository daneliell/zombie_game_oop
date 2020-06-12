package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Menu;
import edu.monash.fit2099.engine.NumberRange;

/**
 * Action class that is used as a menu option to use the Sniper Rifle.
 * Brings up a submenu to choose which Actor to use the Sniper Rifle on.
 * 
 * @author Daniel Yuen
 *
 */
public class SniperAction extends Action {
	
	private Actions actions = new Actions();
	private Display display;
	private SniperRifle sniper;
	private Menu menu = new Menu();
	private Action selectedAction;
	
	/**
	 * Constructor.
	 * 
	 * @param display Display that will draw the state of the game
	 * @param sniper SniperRifle item instance in the Player's inventory
	 */
	public SniperAction(Display display, SniperRifle sniper) {
		this.display = display;
		this.sniper = sniper;
	}
	
	/**
	 * Scans the map the Actor is on to obtain all Actors that have UNDEAD
	 * ZombieCapability. Shows the submenu to allow Players to choose the
	 * Actor they wish to aim or shoot at. Creates a new SniperAimAction
	 * and respective SniperShootAction for every Actor detected. If no
	 * valid Actors are present on the map, a DoNothingAction is returned.
	 * 
	 * @return the result of aiming or shooting at the selected target
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		// Checks if Player has aimed before this
		if (actor.asPlayer().getAim() > 0) {
			Action nextAction = menu.showMenu(actor, actions, display);
			// If next Action is not an aim or shoot Action on the same target, clear all aims
			if ((nextAction != selectedAction) & (nextAction != selectedAction.getNextAction())) {
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
					// Adds the aim and respective shoot actions on the target
					actions.add(aimAction);
					actions.add(aimAction.getNextAction());
					hasActor = true;
				}
			}
		}
		// If no valid Actor present, add a DoNothingAction
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
	
	/**
	 * Allows the same action to be returned in the following turn.
	 */
	@Override
	public Action getNextAction() {
		return this;
	}

}
