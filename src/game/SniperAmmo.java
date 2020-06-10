package game;

/**
 * Class representing the ammo item for SniperRifles.
 * 
 * @author Daniel Yuen
 *
 */
public class SniperAmmo extends Ammo{
	
	/**
	 * Constructor.
	 * 
	 * Creates an instance of SniperAmmo with 2 rounds
	 */
	public SniperAmmo() {
		super("sniper rifle ammo", '*', 2);
	}
}
