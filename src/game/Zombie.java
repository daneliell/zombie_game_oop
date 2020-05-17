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
	private Behaviour[] behaviours = {
			new ZombieAttackBehaviour(ZombieCapability.ALIVE),
			new HuntBehaviour(Human.class, 10),
			new WanderBehaviour()
	};
	
	private String zombieDialogue = "Braaaaaaaains...";
	private double dialogueChance = 0.1;
	
	private List<WeaponItem> limbs = new ArrayList<WeaponItem>();
	private int armsNumber;
	private int legsNumber;
	private double limbLostChance = 0.25;
	private Boolean isSecondTurn = false;

	public Zombie(String name) {
		super(name, 'Z', 100, ZombieCapability.UNDEAD);
		armsNumber = 2;
		legsNumber = 2;
		for (int i = 0; i < 2; i++) {
			limbs.add(new ZombieArm());
			limbs.add(new ZombieLeg());
		}
		Collections.shuffle(limbs);
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
		
		for (Behaviour behaviour : behaviours) {
			Action action = behaviour.getAction(this, map);
			if (action != null)
				return action;
		}
		return new DoNothingAction();	
	}
	
	public String loseLimb(Location dropLocation) {
		//double rand = Math.random(); 
		double rand = 0.1;
		//Location dropLocation = new Location(map, map.locationOf(this).x() + 1, map.locationOf(this).y());
		//System.out.println(dropLocation.x());
		
		if (rand <= limbLostChance) {
			Random randLimbs = new Random();
			if ((armsNumber & legsNumber) != 0) {
				if (randLimbs.nextBoolean() & armsNumber != 0) {
					armsNumber--;
					dropLocation.addItem(new ZombieArm());
					return name + "'s arm was knocked off!";
				}
				else if (legsNumber != 0){
					legsNumber--;
					dropLocation.addItem(new ZombieLeg());
					return name + "'s leg was knocked off!";
				}
			}
		}
		return null;
	}
}
