package com.weDontGiveAShip.interfaces;

public class AlreadyShotPositionException extends InvalidActionException {
	
	private final Position pos;
	
	public AlreadyShotPositionException(Position pos) {
		super("Shot already taken on position " + pos);
		this.pos = pos;
	}
	
	public Position getPos() {
		return pos;
	}
}
