package edu.monash.fit2099.demo.mars;

import java.util.ArrayList;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Item;

public class MartianItem extends Item{

	public MartianItem(String name, char displayChar, boolean portable) {
		super(name, displayChar, portable);
	}
	
	public void addAction(Action action) {
		this.allowableActions.add(action);
	}

	@Override
	public void setCraftItems(ArrayList<Item> craftItems) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addCraftItems(Item item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Item> getCraftItems() {
		// TODO Auto-generated method stub
		return null;
	}
}
