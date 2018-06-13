package com.weDontGiveAShip.main;

import com.weDontGiveAShip.interfaces.Position;

public class AIPrediction {

	public static final int UP = 0;
	public static final int RIGHT = 1;
	public static final int LEFT = 2;
	public static final int DOWN = 3;
	
	public  int location;
	public Position position;
	public boolean success;
	
	public AIPrediction(Position position, int relativeLocation, boolean success) {
		this.position = position;
		this.location = relativeLocation;
		this.success = success;
	}
	
}
