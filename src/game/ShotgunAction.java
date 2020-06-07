package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Menu;
import java.util.List;
import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Exit;

public class ShotgunAction extends Action{
	
	private Actions actions = new Actions();
	Display display;
	Shotgun shotgun;
	Menu menu = new Menu();
	
	public ShotgunAction(Display display, Shotgun shotgun) {
		this.display = display;
		this.shotgun = shotgun;
	}
	
	@Override
	public String execute(Actor actor, GameMap map) {
		shotgun.reduceAmmo(actor);	
		List<Exit> exits = map.locationOf(actor).getExits();
		for (Exit e : exits) {
			actions.add(new ShotgunShootAction(e));
		}
		Action selectedAction = menu.showMenu(actor, actions, display);
		return selectedAction.execute(actor,map);
	}
	
	@Override
	public String menuDescription(Actor actor) {
		return actor + " aims the shotgun"; 
	}
	
}
