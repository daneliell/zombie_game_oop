package game;

/**
 * Abstract class to represent ammo items in the world.
 * 
 * @author Daniel Yuen
 *
 */
public abstract class Ammo extends PortableItem{
	
	protected int rounds;
	
	/**
	 * Constructor.
	 * 
	 * @param name Name/Type of ammo
	 * @param displayChar Display character of ammo on the ground
	 * @param rounds Number of rounds in one pack of ammo
	 */
	public Ammo(String name, char displayChar, int rounds) {
		super(name, displayChar);
		this.rounds = rounds;
	}
	
	/**
	 * Public getter method to obtain the number of rounds left in the
	 * pack of ammo.
	 * 
	 * @return number of rounds left in the ammo pack
	 */
	public int getRounds() {
		return rounds;
	}
	
	/**
	 * Public mutator method to reduce the number of rounds left in the
	 * pack of ammo by 1.
	 */
	public void reduceRounds() {
		rounds -= 1;
	}

}
