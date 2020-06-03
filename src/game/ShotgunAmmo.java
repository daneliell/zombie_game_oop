package game;

public class ShotgunAmmo extends PortableItem {
	
	private int rounds = 6;
	
	public ShotgunAmmo() {
		super("shotgun ammo", '*');
	}
	
	public boolean getRounds() {
		if (rounds == 0) {
			return false;
		}
		return true;
	}
	public void reduceRounds() {
		rounds -= 1;
	}
}
