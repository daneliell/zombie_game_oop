package game;

public abstract class Ammo extends PortableItem{

	protected int rounds;
	
	public Ammo(String name, char displayChar, int rounds) {
		super(name, displayChar);
		this.rounds = rounds;
	}
	
	public int getRounds() {
		return rounds;
	}
	
	public void reduceRounds() {
		rounds -= 1;
	}

}
