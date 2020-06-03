package game;

import java.util.ArrayList;
import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

public class ShotgunShootAction extends Action {
	
	private Exit direction;
	private ArrayList<Exit> area = new ArrayList<Exit>();
	
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
				ShotgunAttackAction attackAction = new ShotgunAttackAction(target);
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
