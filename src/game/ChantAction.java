package game;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.NumberRange;

public class ChantAction extends Action {
	private String[] zombies = {"Zombie Erica", "Zombie Monica", "Zombie Tina", "Zombie Ahamad", "Zombie Santhi", "Zombie Jr", 
			"Zombie Monash", "Zombie Ali", "Zombie Jian-Yang", "Zombie Syabu", "Zombie Pewds", "Zombie Ketupat", "Zombie Papadam",
			"Zombie Rita", "Zombie Ah Beng", "Zombie Bill", "Zombie Tammy", "Zombie Billy Bob", "Zombie Mambo no.5"};
	
	public String execute(Actor actor, GameMap map) {
		List<String> names = Arrays.asList(zombies);
		Collections.shuffle(names);
		int i = 0;
		NumberRange xRange = map.getXRange();
		NumberRange yRange = map.getYRange();
		int minX = xRange.min();
		int minY = yRange.min();
		int maxX = xRange.max();
		int maxY = yRange.max();
		while(i<5) {
			int x = (int) ((Math.random()*((maxX-minX)+1))+minX);
			int y = (int) ((Math.random()*((maxY-minY)+1))+minY);
			if(!map.isAnActorAt(map.at(x, y))) {
				map.addActor(new Zombie(names.get(i)), map.at(x, y));
				i++;
			}
			else {
				continue;
			}
		}
		return menuDescription(actor);
	}
	
	public String menuDescription(Actor actor) {
		return actor + " chants a spell";
	}
}
