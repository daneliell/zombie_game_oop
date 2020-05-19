package game;

import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Item;
/**
 * 
 * @author Sravan
 *
 */
public class Food extends Item {
	protected int nutrients = 10;
	
	public Food() {
		super("food", 'f', true);
		this.addAction(new EatAction(this));
	}
	
	public int getNutrients() {
		return this.nutrients;
	}
	
	private void addAction(Action action) {
		List<Action> actions = super.getAllowableActions();
		this.allowableActions.add(actions);
		this.allowableActions.add(action);
	}

	@Override
	public Item getCraftItem() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isCraftable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public CraftingAction getCraftingAction() {
		if(this.isCraftable()) {
			return new CraftingAction(this);
		}
		return null;
	}



}
