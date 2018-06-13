package com.weDontGiveAShip.main;

import com.weDontGiveAShip.interfaces.InvalidShipPlacementException;
import com.weDontGiveAShip.interfaces.Player;
import com.weDontGiveAShip.interfaces.Position;
import com.weDontGiveAShip.interfaces.Ship;
import com.weDontGiveAShip.interfaces.ShipPlacer;
import com.weDontGiveAShip.interfaces.Tile;
import com.weDontGiveAShip.interfaces.TurnAction;

public class PlayerImpl implements Player{

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
	
}
