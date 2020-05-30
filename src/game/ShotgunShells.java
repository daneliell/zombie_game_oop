package game;

public class ShotgunShells extends Ammo{
	
	public ShotgunShells() {
		super("shotgun shells", '*');
	}
	
	@Override
	public Ammo getAmmoType() {
		return this;
	}
}
