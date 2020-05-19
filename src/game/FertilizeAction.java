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
	protected Crop target;
	
	public FertilizeAction(Crop target) {
		this.target = target;
	}
	
	public String execute(Actor actor, GameMap map) {
		
		if(this.target.isRipe()) {
			return "Crop is already ripe";
		}
		
		if(this.target.getAge() < 10) {
			this.target.setAge(0);
			return "Crop was fertilized";
		}
		
		else {
			int newAge = this.target.getAge() - 10;
			this.target.setAge(newAge);
			return "Crop was fertilized";
		}
		
	}
	
	public String menuDescription(Actor actor) {
		return actor + " fertilized crop";
	}

}
