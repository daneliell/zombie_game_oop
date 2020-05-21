package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.IntrinsicWeapon;
import edu.monash.fit2099.engine.Item;
import java.lang.Math;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A Zombie. Zombies have a chance to say something Zombie-like each turn. Zombies
 * can attack Humans with either a normal attack or a bite attack. If a Zombie is
 * standing on a WeaponItem, it picks it up and uses it instead. 
 * 
 * On hit, a Zombie has a chance to lose any of its 4 limbs. Reduced limbs cause
 * the Zombie to behave differently such as dropping its weapon when it loses
 * its arms and losing the ability to move after losing both its legs. Dropped Zombie 
 * limbs can be wielded as weapons by the player.
 * 
 * @author Daniel Yuen
 *
 */
public class Zombie extends ZombieActor {
	// attributes for Behaviour
	private ZombieAttackBehaviour attackBehaviour = new ZombieAttackBehaviour(ZombieCapability.ALIVE, 2);
	private Behaviour[] moveBehaviours = {
			new HuntBehaviour(Human.class, 10),
			new WanderBehaviour()
	};
	
	// attributes for producing Zombie dialogue
	private static final String ZOMBIE_DIALOGUE = "Braaaaaaaains...";
	private static final double DIALOGUE_CHANCE = 0.1;
	
	// attributes for dropping limbs on hit
	private int armsNumber;
	private int legsNumber;
	private static final double LIMB_LOSS_CHANCE = 0.25;
	private Boolean isSecondTurn = false;
	
	/**
	 * Constructor.
	 * 
	 * Sets the name, display character, hit points and capability
	 * Sets the armsNumber and legsNumber to 2 during creation of a Zombie.
	 * 
	 * @param name Zombie name
	 */
	public Zombie(String name) {
		super(name, 'Z', 100, ZombieCapability.UNDEAD);
		armsNumber = 2;
		legsNumber = 2;
	}
	
	@Override
	public IntrinsicWeapon getIntrinsicWeapon() {
		return new IntrinsicWeapon(10, "punches");
	}

	/**
	 * Before beginning a turn, Zombies have a chance for to say something Zombie-like.
	 * If a Zombie can attack, it will. If the Zombie has only 1 leg, it only performs move actions
	 * once every two turns. If the Zombie has no legs, it will not be able to move at all. If it
	 * has both legs, it will perform its move actions, which is to chase any human within 
	 * 10 spaces. If no humans are close enough it will wander randomly. 
	 * 
	 * @param actions list of possible Actions
	 * @param lastAction previous Action, if it was a multiturn action
	 * @param map the map where the current Zombie is
	 * @param display the Display where the Zombie's utterances will be displayed
	 * @return the Action chosen to be performed
	 */
	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		assert (0 <= armsNumber & legsNumber <= 2) : "Arms and legs cannot be less than 0 or greater than 2";
		
		// has a chance to say something Zombie-like
		if (Math.random() < DIALOGUE_CHANCE) {
			display.println(name + ": " + ZOMBIE_DIALOGUE);
		}
		else {
			// performs an attack if it can
			Action attackAction = attackBehaviour.getAction(this,map);
			if (attackAction != null) {
				return attackAction;
			}

			if (legsNumber == 0) {
				return new DoNothingAction();
			}
			// this block of code will run every time if the Zombie has both of its legs, and 
			// every second turn if the Zombie has only 1 leg
			else if (legsNumber == 2 | isSecondTurn == true) {
				isSecondTurn = false;
				for (Behaviour moveBehaviour : moveBehaviours) {
					Action moveAction = moveBehaviour.getAction(this, map);
					if (moveAction != null)
						return moveAction;
				}
			}
			// skips turn if the Zombie only has 1 leg
			else if (legsNumber == 1 & isSecondTurn == false) {
				isSecondTurn = true;
			}
		}
		return new DoNothingAction();	
	}
	
	/**
	 * Has a chance to randomly remove a limb from the Zombie. Drops the weapon
	 * it was holding if it loses its arms. Drops the knocked off limb on the ground
	 * randomly on any of the 8 spaces around the Zombie.
	 * 
	 * @param map the map where the current Zombie is
	 * @return String informing the result of knocking off the limb
	 */
	public String loseLimb(GameMap map) {
		if (Math.random() <= LIMB_LOSS_CHANCE) {
			// Random locations around the Zombie to drop limb
			List<Exit> exits = new ArrayList<Exit>(map.locationOf(this).getExits());
			Collections.shuffle(exits);
			
			if ((armsNumber | legsNumber) != 0) {
				// 50/50 chance to either drop an arm or a leg
				if (Math.random() < 50 & armsNumber > 0) {
					String result = "";
					armsNumber--;
					attackBehaviour.removeArm();
					result += System.lineSeparator() + name + "'s arm was knocked off";
					
					if (armsNumber == 1) {
						if (Math.random() < 50) {
							result += dropWeapon(map);
						}
					}
					if (armsNumber == 0) {
						result += dropWeapon(map);
					}
					exits.get(0).getDestination().addItem(new ZombieArm());
					return result;
				}
				if (legsNumber > 0) {
					legsNumber--;
					exits.get(0).getDestination().addItem(new ZombieLeg());
					return System.lineSeparator() + name + "'s leg was knocked off!";
				}
			}
		}
		return "";
	}
	
	/**
	 * Drops the weapon the Zombie is currently holding.
	 * 
	 * @param map The GameMap containing the Zombie
	 * @return String containing the result 
	 */
	private String dropWeapon(GameMap map) {
		for (Item item : inventory) {
			if (item.asWeapon() != null)
				item.getDropAction().execute(this, map);
				return " and drops the " + item + " that it was holding.";
		}
		return "";
	}

}
