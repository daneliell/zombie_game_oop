package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.IntrinsicWeapon;
import edu.monash.fit2099.engine.WeaponItem;
import java.lang.Math;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * A Zombie.
 * 
 * This Zombie is pretty boring.  It needs to be made more interesting.
 * 
 * @author ram
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
	private String zombieDialogue = "Braaaaaaaains...";
	private double dialogueChance = 0.1;
	
	// attributes for dropping limbs on hit
	//private int[][] dropLocation = {{-1,-1},{-1,0},{-1,1},{0,-1},{0,1},{1,-1},{1,0},{1,-1}};
	private int armsNumber;
	private int legsNumber;
	private double limbLostChance = 0.25;
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
	 * Before beginning a turn, it has a chance for the Zombie to say something Zombie-like.
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
		// has a chance to return a SpeakAction
		if (Math.random() < 0.1) {
			return new SpeakAction(zombieDialogue, dialogueChance);
		}
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
		
		return new DoNothingAction();	
	}
	
	/**
	 * Has a chance to randomly remove a limb from the Zombie. Drops the weapon
	 * it was holding if it loses its arms. Drops the knocked off limb on the ground
	 * randomly on any of the 8 blocks around the Zombie.
	 * 
	 * @param map the map where the current Zombie is
	 * @return String informing the result of knocking off the limb
	 */
	public String loseLimb(GameMap map) {
		if (Math.random() <= limbLostChance) {
			List<Exit> exits = new ArrayList<Exit>(map.locationOf(this).getExits());
			Collections.shuffle(exits);

			/*double index = Math.floor(Math.random()*dropLocation.length);
	        int randomX = dropLocation[(int)index][0];
	        int randomY = dropLocation[(int)index][1];
			int x = map.locationOf(this).x()+randomX;
			int y = map.locationOf(this).y()+randomY;*/
			
			if ((armsNumber | legsNumber) != 0) {
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
	
	private String dropWeapon(GameMap map) {
		String result = "";
		if (this.getWeapon() instanceof IntrinsicWeapon == false) {
			WeaponItem weapon = (WeaponItem) this.getWeapon();
			weapon.getDropAction().execute(this, map);
			result = " and drops the " + weapon + " that it was holding.";
		}
		return result;
	}
}
