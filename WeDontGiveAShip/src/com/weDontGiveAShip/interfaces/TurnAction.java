package com.weDontGiveAShip.interfaces;

/**
 * A {@link TurnAction} allows you to {@link TurnAction#shootTile(Position) shoot a Tile} and returns a {@link Tile} or throws a {@link InvalidActionException}
 */
public abstract class TurnAction {
	
	protected boolean taken = false;
	
	public boolean isTaken() {
		return taken;
	}
	
	/**
	 * takes an Action and shoots a {@link Tile}
	 *
	 * @param position the {@link Position} to shoot
	 * @return the {@link Tile} which was hit, one of {@link Tile#WATER}, {@link Tile#SHIP} or {@link Tile#SHIP_KILL}.
	 * @throws InvalidActionException if the action is invalid, eg. {@link AlreadyUsedActionException}, {@link ActionPositionOutOfBoundsException} or others
	 */
	public synchronized final Tile shootTile(Position position) throws InvalidActionException {
		if (taken)
			throw new AlreadyUsedActionException();
		
		Tile tile = shootTile0(position);
		taken = true;
		return tile;
	}
	
	protected abstract Tile shootTile0(Position position) throws InvalidActionException;
}
