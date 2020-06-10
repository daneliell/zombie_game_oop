package game;

/**
 * Class representing the ammo item for Shotguns.
 * 
 * @author Daniel Yuen
 *
 */
public class ShotgunAmmo extends Ammo {
	
	/**
	 * Constructor.
	 * 
	 * Creates an instance of ShotgunAmmo with 6 rounds
	 */
	public ShotgunAmmo() {
		super("shotgun ammo", '*', 6);
	}
}
