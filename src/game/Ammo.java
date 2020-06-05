package game;

public abstract class Ammo extends PortableItem{

	protected int rounds;
	
	public Ammo(String name, char displayChar, int rounds) {
		super(name, displayChar);
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
