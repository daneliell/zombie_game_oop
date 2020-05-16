package game;

import edu.monash.fit2099.engine.WeaponItem;

/**
 * Weapon dropped from knocking off the leg of a Zombie. Can be crafted into a Zombie Mace.
 * 
 * @author Daniel Yuen
 *
 */
public class ZombieLeg extends WeaponItem {
	
	public ZombieLeg() {
		super("zombie leg", '}', 20, "whacks");
	}

}
