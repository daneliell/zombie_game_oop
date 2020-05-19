package game;


import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.WeaponItem;
/**
 * 
 * @author Sravan
 *
 */
public class ZombieMace extends WeaponItem {
	
	public ZombieMace() {
		super("zombie mace", 'd', 25, "batters");
	}

	@Override
	public Item getCraftItem() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isCraftable() {

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


