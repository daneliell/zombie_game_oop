package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
/**
 * 
 * @author Sravan
 *
 */
public class FertilizeAction extends Action{
	private Crop target;
	
	public FertilizeAction(Crop target) {
		this.target = target;
	}
	
	public String execute(Actor actor, GameMap map) {
		
		int Age = this.target.getAge();

		if (Age < 10) {
			this.target.setAge(0);
			return actor + " fertilizes a crop";
		}	
		else {
			int newAge = Age - 10;
			this.target.setAge(newAge);
			return actor + " fertilizes a crop";
		}
		
	}
	
	public String menuDescription(Actor actor) {
		return actor + " fertilizes crop";
	}

}
