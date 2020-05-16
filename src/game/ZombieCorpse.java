package game;

import edu.monash.fit2099.engine.Item;

public class ZombieCorpse extends Item {
	
	public ZombieCorpse(String name, char displayChar, boolean portable) {
		super("dead" + name, '%', false);
	}

}
