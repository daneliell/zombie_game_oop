package game;

import java.util.ArrayList;
import java.util.List;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.GroundFactory;
import edu.monash.fit2099.engine.Item;

public class CompoundMap extends GameMap {
	private boolean MamboStatus;
	private MamboMarie marie = new MamboMarie();
	private boolean spawned;
	
	public CompoundMap(GroundFactory groundFactory, char groundChar, int width, int height) {
		super(groundFactory, groundChar, width, height);
		this.MamboStatus = true;
	}
	
	public CompoundMap(GroundFactory groundFactory, List<String> lines) {
		super(groundFactory, lines);
		this.MamboStatus = true;
	}

	public boolean getMamboStatus() {
		return this.MamboStatus;
	}
	
	public void tick() {
		// Tick over all the items in inventories.
		for (Actor actor : actorLocations) {
			if (this.contains(actor)) {
				for (Item item : new ArrayList<Item>(actor.getInventory())) { // Copy the list in case the item wants to leave
					item.tick(actorLocations.locationOf(actor), actor);
				}
			}
		}

		for (int y : heights) {
			for (int x : widths) {
				this.at(x, y).tick();
			}
		}
		
		if (marie.isConscious()) {
			if (this.spawned) {
				this.MamboStatus = true;
				if (marie.getSpawnCounter() % 30 == 0) {
					this.removeActor(marie);
					spawned = false;
				}
			} 
			else {
				if (Math.random() < 0.05) {
					this.at(this.getXRange().min(), this.getXRange().min()).addActor(marie);
					spawned = true;
				}
			} 
		}
		if(spawned && !this.contains(marie)) {
			this.MamboStatus = false;
		}
	}
}
