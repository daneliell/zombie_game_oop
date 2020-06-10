package game;

import java.util.ArrayList;
import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;

public class ShotgunShootAction extends Action {
	
	private Exit direction;
	ArrayList<Exit> area = new ArrayList<Exit>();
	
	public ShotgunShootAction(Exit direction) {
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
					Exit middleExit = exits.get(j);
					Exit leftExit = exits.get(left);
					Exit rightExit = exits.get(right);
					String leftDirection = getAdjacentDirection(middleExit, leftExit);
					String rightDirection = getAdjacentDirection(middleExit, rightExit);
					for (int k = 0; k < i; k++) {
						leftExit = addAdjacentArea(leftExit, leftDirection);
						rightExit = addAdjacentArea(rightExit, rightDirection);
					}
					
					area.add(leftExit);
					area.add(rightExit);
					area.add(middleExit);
					/*result += hitArea(exits.get(left), actor, map);
					result += hitArea(exits.get(j), actor, map);
					result += hitArea(exits.get(right), actor, map)*/
					exits = exits.get(j).getDestination().getExits();
				}
			}
		}
		for (Exit area : area) {
			if (area.getDestination().getGround().blocksThrownObjects()) {
				area.getDestination().setGround(new Path());
			}
			if (area.getDestination().containsAnActor()) {
				Actor target = area.getDestination().getActor();
				ShotgunAttackAction attackAction = new ShotgunAttackAction(target);
				result += System.lineSeparator() + attackAction.execute(actor, map);
			}
		}
		return result;
		/*for (Exit exit : area) {
			System.out.println("(" + exit.getDestination().x() + ", " + exit.getDestination().y() + ")");
		}*/
	}
	
	private String getAdjacentDirection(Exit middle, Exit adjacent) {
		for (Exit exit : middle.getDestination().getExits()) {
			if (exit.getDestination() == adjacent.getDestination()) {
				return exit.getName();
			}
		}
		return "";
	}
	
	private Exit addAdjacentArea(Exit e, String direction) {
		for (Exit exit : e.getDestination().getExits()) {
			if (exit.getName() == direction) {
				area.add(e);
				return exit;
			}
		}
		return null;
	}
	
	/*private String hitArea(Exit area, Actor actor, GameMap map) {
		String result = "";
		if (area.getDestination().getGround().blocksThrownObjects()) {
			area.getDestination().setGround(new Path());
		}
		if (area.getDestination().containsAnActor()) {
			Actor target = area.getDestination().getActor();
			ShotgunAttackAction attackAction = new ShotgunAttackAction(target);
			result += System.lineSeparator() + attackAction.execute(actor, map);
		}
		return result;
	}*/
	
	@Override
	public String menuDescription(Actor actor) {
		return actor + " shoots " + direction.getName(); 
	}
}
