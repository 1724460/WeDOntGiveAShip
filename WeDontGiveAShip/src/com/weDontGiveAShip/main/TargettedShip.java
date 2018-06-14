package com.weDontGiveAShip.main;

import java.util.ArrayList;
import java.util.List;

import se1.schiffeVersenken.interfaces.util.Position;

public class TargettedShip {

	private List<Position> markedPositions = new ArrayList<Position>();
	private List<Position> hitPositions = new ArrayList<Position>();
	
	public TargettedShip(Position initialPosition) {
		mark(initialPosition, true);
	}
	
	public void mark(Position position, boolean hitShip) {
		if(hitShip) {
			hitPositions.add(position);
		}
		
		markedPositions.add(position);
	}
	
	public Position[] getMarkedPositions() {
		return markedPositions.toArray(new Position[markedPositions.size()]);
	}
	
	public Position[] getHitPositions() {
		return hitPositions.toArray(new Position[hitPositions.size()]);
	}
	
}
