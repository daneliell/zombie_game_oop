package game;


import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.WeaponItem;
/**
 * 
 * @author Sravan
 *
 */
public class ZombieClub extends WeaponItem {
	
	public ZombieClub() {
		super("zombie club", 'P', 20, "clubs");
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
