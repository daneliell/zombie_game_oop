package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.WeaponItem;

/**
 * Abstract class to represent different forms of guns in the world.
 *  
 * @author Daniel Yuen
 *
 */
public abstract class GunItem extends WeaponItem {
	
	/**
	 * Constructor.
	 * 
	 * @param name Name/Type of the gun
	 * @param displayChar Display character of gun on the ground
	 * @param damage Amount of damage when used as a melee weapon
	 * @param verb Verb to use for melee attacks
	 */
	public GunItem(String name, char displayChar, int damage, String verb) {
		super(name, displayChar, damage, verb);
	}
	
	/**
	 * Obtain each individual gun's ammo object.
	 *  
	 * @param actor Actor holding the gun
	 * @return null
	 */
	public Ammo getAmmo(Actor actor) {
		return null;
	}
	
	/**
	 * Obtain each individual gun's action when being used.
	 * 
	 * @param display Display that will draw the state of the game
	 * @return null
	 */
	public Action getAction(Display display) {
		return null;
	}
	
	/**
	 * Reduce the ammo in the inventory of the actor holding the gun.
	 * 
	 * @param actor Actor holding the gun
	 */
	public void reduceAmmo(Actor actor) {
		Ammo ammo = this.getAmmo(actor);
		ammo.reduceRounds();
		if (ammo.getRounds() == 0) {
			actor.removeItemFromInventory(ammo);
		}
	}
}
