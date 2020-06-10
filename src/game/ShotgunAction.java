package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Menu;
import java.util.List;
import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Exit;

/**
 * Action class that is used as a menu option to use the Shotgun.
 * Brings up a submenu to choose which direction to use the Shotgun.
 * 
 * @author Daniel Yuen
 *
 */
public class ShotgunAction extends Action{
	
	private Actions actions = new Actions();
	private Display display;
	private Shotgun shotgun;
	private Menu menu = new Menu();
	
	/**
	 * Constructor.
	 * 
	 * Creates an instance of ShotgunAction with Display to show the submenu.
	 * 
	 * @param display Display that will draw the state of the game
	 * @param shotgun Shotgun item instance in the Player's inventory
	 */
	public ShotgunAction(Display display, Shotgun shotgun) {
		this.display = display;
		this.shotgun = shotgun;
	}
	
	/**
	 * Reduces the rounds in ShotgunAmmo in the Player's inventory.
	 * Shows the submenu to allow Players to choose the direction they
	 * wish to shoot the Shotgun. Creates a new ShotgunShootAction
	 * for every exit direction that the Player can shoot in.
	 * 
	 * @return the result of shooting the shotgun in the selected direction
	 */
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
