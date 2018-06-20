package com.weDontGiveAShip.main;

import se1.schiffeVersenken.interfaces.GameSettings;
import se1.schiffeVersenken.interfaces.Player;
import se1.schiffeVersenken.interfaces.PlayerCreator;
import se1.schiffeVersenken.interfaces.Ship;
import se1.schiffeVersenken.interfaces.ShipPlacer;
import se1.schiffeVersenken.interfaces.Tile;
import se1.schiffeVersenken.interfaces.TurnAction;
import se1.schiffeVersenken.interfaces.exception.shipPlacement.InvalidShipPlacementException;
import se1.schiffeVersenken.interfaces.util.Position;

public class PlayerImpl implements Player, PlayerCreator{

	private Ship[] ships;
	
	@Override
	public void placeShips(ShipPlacer placer) {
		
		try {
			placer.setShips(ships);
		} catch (InvalidShipPlacementException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void takeTurn(TurnAction turnAction) {
//		turnAction.shootTile();
	}

	@Override
	public void onEnemyShot(Position position, Tile tile, Ship ship) {
		//	Position 
		
	}

	@Override
	public void gameOver(boolean youHaveWon) {
		
	}

	public void setShips(Ship[] ships) {
		this.ships = ships;
	}
	
	public Ship[] getShips() {
		return ships;
	}

	@Override
	public Player createPlayer(GameSettings settings, Class<? extends PlayerCreator> otherPlayer) {
		return new PlayerImpl();
	}

	public void turn() {
				
	}
	
}
