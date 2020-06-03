package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.Weapon;

import java.util.ArrayList;
import java.util.List;

import edu.monash.fit2099.engine.Action;
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
	
	private Exit direction;
	private ArrayList<Exit> area = new ArrayList<Exit>();
	
	public ShotgunAction(Exit direction) {
		this.direction = direction;
	}
	
	@Override
	public String execute(Actor actor, GameMap map) {
		String result = "";
		String name = direction.getName();
		List<Exit> exits = map.locationOf(actor).getExits();
		
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < exits.size(); j++) {
				if (exits.get(j).getName() == name) {
					int left = j - 1;
					if (left < 0) {
						left = exits.size() - 1;
					}
					int right = (j + 1) % (exits.size() - 1);
					area.add(exits.get(left));
					area.add(exits.get(j));
					area.add(exits.get(right));
					exits = exits.get(j).getDestination().getExits();
				}
			}
		}
		for (int k = 0; k < area.size(); k++) {
			Location location = area.get(k).getDestination();
			if (location.containsAnActor()) {
				Actor target = location.getActor();
				AttackAction attackAction = new AttackAction(target);
				result += attackAction.execute(actor, map);
			}
			if (location.getGround().blocksThrownObjects()) {
				location.setGround(new Path());
			}
		}
		return result;
	}
	
	@Override
	public String menuDescription(Actor actor) {
		return actor + " shoots " + direction.getName(); 
	}
	
}
