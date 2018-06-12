package com.weDontGiveAShip.interfaces;

public class ActionPositionOutOfBoundsException extends InvalidActionException {
	
	private final Position pos;
	
	public ActionPositionOutOfBoundsException(Position pos) {
		super("Invalid Position: " + pos + "!");
		this.pos = pos;
	}
	
	public Position getPos() {
		return pos;
	}
}
