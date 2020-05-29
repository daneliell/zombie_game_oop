package game;


import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

public class ChantAction extends Action {
	
	public String execute(Actor actor, GameMap map) {
		int i = 0;
		while(i<5) {
			int x = (int) ((Math.random()*((80-0)+1))+0);
			int y = (int) ((Math.random()*((25-0)+1))+0);
			if(!map.isAnActorAt(map.at(x, y))) {
				map.addActor(new Zombie("Mambo Zombie"), map.at(x, y));
				i++;
			}
			else {
				continue;
			}
		}
		return menuDescription(actor);
	}
	
	public String menuDescription(Actor actor) {
		return actor + "chants a spell";
	}
}
