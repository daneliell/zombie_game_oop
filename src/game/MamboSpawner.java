package game;

import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;

public class MamboSpawner extends Item {
	private boolean spawned = false;
	private MamboMarie marie = new MamboMarie();
	
	public MamboSpawner() {
		super("Mambo Spawner", '.', false);
	}
	
	public void tick(Location currentLocation) {
		if (marie.isConscious()) {
			if (spawned) {
				if (marie.getSpawnCounter() % 30 == 0) {
					GameMap currentMap = marie.getCurrentMap();
					currentMap.removeActor(marie);
					spawned = false;
				}
			} else {
				if (Math.random() < 0.05) {
					currentLocation.addActor(marie);
					spawned = true;
				}
			} 
		}
	}

	@Override
	public Item getCraftItem() {
		// TODO Auto-generated method stub
		return null;
	}
}
