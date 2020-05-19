package edu.monash.fit2099.demo.mars;

import java.util.ArrayList;

import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.WeaponItem;

public class Stick extends WeaponItem {

	public Stick() {
		super("stick", '/', 10, "pokes");
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
	public ArrayList<Item> getCraftItem() {
		// TODO Auto-generated method stub
		return null;
	}

}
