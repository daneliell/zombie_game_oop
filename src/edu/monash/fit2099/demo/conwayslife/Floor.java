package edu.monash.fit2099.demo.conwayslife;

import edu.monash.fit2099.engine.Ground;
import game.Crop;

public class Floor extends Ground {

	public Floor() {
		super('.');
		addCapability(Status.DEAD);
	}

	@Override
	public Crop asCrop() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean isRipe() {
		// TODO Auto-generated method stub
		return null;
	}
}
