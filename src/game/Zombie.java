package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.IntrinsicWeapon;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
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
	private ZombieAttackBehaviour attackBehaviour = new ZombieAttackBehaviour(ZombieCapability.ALIVE);
	private Behaviour[] moveBehaviours = {
			new HuntBehaviour(Human.class, 10),
			new WanderBehaviour()
	};
	
	private String zombieDialogue = "Braaaaaaaains...";
	private double dialogueChance = 0.1;
	
	private int[] dropLocation = {-1,1};
	private int armsNumber;
	private int legsNumber;
	private double limbLostChance = 0.25;
	private Boolean isSecondTurn;

	public Zombie(String name) {
		super(name, 'Z', 100, ZombieCapability.UNDEAD);
		armsNumber = 2;
		legsNumber = 2;
		attackBehaviour.setArms(armsNumber);
		isSecondTurn = false;
	}
	
	@Override
	public IntrinsicWeapon getIntrinsicWeapon() {
		return new IntrinsicWeapon(10, "punches");
	}

	/**
	 * If a Zombie can attack, it will.  If not, it will chase any human within 10 spaces.  
	 * If no humans are close enough it will wander randomly.
	 * 
	 * @param actions list of possible Actions
	 * @param lastAction previous Action, if it was a multiturn action
	 * @param map the map where the current Zombie is
	 * @param display the Display where the Zombie's utterances will be displayed
	 */
	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		double rand = Math.random(); 
		if (rand <= dialogueChance) {
			display.println(name + ": " + zombieDialogue);
		}
		
		Action attackAction = attackBehaviour.getAction(this,map);
		if (attackAction != null) {
			return attackAction;
		}
		
		if (legsNumber == 0) {
			return new DoNothingAction();
		}
		else if (legsNumber == 2 | isSecondTurn == true) {
			isSecondTurn = false;
			for (Behaviour moveBehaviour : moveBehaviours) {
				Action moveAction = moveBehaviour.getAction(this, map);
				if (moveAction != null)
					return moveAction;
			}
		}
		else if (legsNumber == 1 & isSecondTurn == false) {
			isSecondTurn = true;
		}
		
		return new DoNothingAction();	
	}
	
	public String loseLimb(GameMap map) {
		if (Math.random() <= limbLostChance) {
			Random randLocation = new Random();
	        int randomX = randLocation.nextInt(dropLocation.length);
	        int randomY = randLocation.nextInt(dropLocation.length);
			int x = map.locationOf(this).x()+randomX;
			int y = map.locationOf(this).y()+randomY;
			
			Random randLimbs = new Random();
			if ((armsNumber | legsNumber) != 0) {
				if (randLimbs.nextBoolean() & armsNumber != 0) {
					armsNumber--;
					attackBehaviour.setArms(armsNumber);
					if (armsNumber == 1) {
						if (Math.random() < 50) {
							dropWeapon();
						}
					}
					if (armsNumber == 0) {
						dropWeapon();
					}
					map.at(x, y).addItem(new ZombieArm());
					return System.lineSeparator() + name + "'s arm was knocked off!";
				}
				if (legsNumber != 0) {
					legsNumber--;
					map.at(x, y).addItem(new ZombieLeg());
					return System.lineSeparator() + name + "'s leg was knocked off!";
				}
			}
		}
		return "";
	}
	
	private void dropWeapon() {
		if (this.getWeapon() instanceof WeaponItem) {
			this.removeItemFromInventory((Item) this.getWeapon());
		}
	}
}
