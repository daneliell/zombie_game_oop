package game;


import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

public class ChantAction extends Action {
	private String[] zombies = {"Erica", "Monica", "Tina", "Jessica", "Mambo no.5"};
	
	public String execute(Actor actor, GameMap map) {
		int i = 0;
		while(i<5) {
			int x = (int) ((Math.random()*((map.getXRange().max()-map.getXRange().min())+1))+map.getXRange().min());
			int y = (int) ((Math.random()*((map.getYRange().max()-map.getYRange().min())+1))+map.getYRange().min());
			if(!map.isAnActorAt(map.at(x, y))) {
				map.addActor(new Zombie(zombies[i]), map.at(x, y));
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
