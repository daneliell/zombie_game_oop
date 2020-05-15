package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

public class FertilizeAction extends Action{
	protected Crop target;
	
	public FertilizeAction(Crop Target) {
		this.target = Target;
	}
	
	public String execute(Actor actor, GameMap map) {
		
		if(this.target.isRipe()) {
			return "The crop is already ripe";
		}
		
		if(this.target.getAge() < 10) {
			this.target.setAge(0);
			return "The crop was fertilized";
		}
		
		else {
			int newAge = this.target.getAge() - 10;
			this.target.setAge(newAge);
			return "The crop was fertilized";
		}
		
	}
	
	public String menuDescription(Actor actor) {
		return actor + " fertilized the crop";
	}

}