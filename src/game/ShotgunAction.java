package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.Menu;
import edu.monash.fit2099.engine.Weapon;

import java.util.ArrayList;
import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Exit;

public class ShotgunAction extends Action{
	
	/*private Exit left;
	private Exit middle;
	private Exit right;
	private ArrayList<Exit> directions = new ArrayList<Exit>();
	private ArrayList<Exit> area = new ArrayList<Exit>();
	
	public ShootAction(Exit left, Exit middle, Exit right) {
		this.left = left;
		this.middle = middle;
		this.right = right;
		directions.add(left);
		directions.add(middle);
		directions.add(right);
		area.add(left);
		area.add(middle);
		area.add(right);
	}

	@Override
	public String execute(Actor actor, GameMap map) {
		directions.add(left);
		directions.add(middle);
		directions.add(right);
		//Weapon weapon = actor.getWeapon();
		//int damage = weapon.damage();
		String result = "";
		for (Exit direction : directions) {
			String name = direction.getName();
			List<Exit> exits = direction.getDestination().getExits();
			for (int i = 0; i < 1; i++) {
				for (Exit e : exits) {
					if (e.getName() == name) {
						area.add(e);
						exits = e.getDestination().getExits();
					}
				}
			}
		}
		String name = middle.getName();
		List<Exit> exits = middle.getDestination().getExits();
		for (int i = 0; i < 1; i++) {
			for (Exit e : exits) {
				if (e.getName() == name) {
					area.add(e);
					
				}
			}
		}
		for (int i = 0; i < area.size(); i++) {
			System.out.println(area.get(i).getDestination().x());
			System.out.println(area.get(i).getDestination().y());
		}
		for (int i = 0; i < area.size(); i++) {
			Location location = area.get(i).getDestination();
			if (location.containsAnActor()) {
				Actor target = location.getActor();
				AttackAction attackAction = new AttackAction(target);
				result += attackAction.execute(actor, map);
				//result += actor + " " + weapon.verb() + " " + target + " for 50 damage.";
			}
		}
		// area contains left, middle, right, left1, left2, middle1, middle2, right1, right2
		return result;
	}*/
	
	private Actions actions = new Actions();
	Display display;
	Menu menu = new Menu();
	
	public ShotgunAction(Display display) {
		this.display = display;
	}
	
	@Override
	public String execute(Actor actor, GameMap map) {
		List<Item> inventory = actor.getInventory();

		for (Item item : inventory) {
			if (item.asShotgunAmmo() != null) {
				actor.removeItemFromInventory(item);
				List<Exit> exits = map.locationOf(actor).getExits();
				for (Exit e : exits) {
					actions.add(new ShootAction(e));
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
