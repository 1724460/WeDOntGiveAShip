package com.weDontGiveAShip.main;

import java.awt.Color;

import com.weDontGiveAShip.UI.panels.MatchPanel;

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

	public static Ship[] ships;
	
	@Override
	public void placeShips(ShipPlacer placer) {
		
		try {
			placer.setShips(ships);
		} catch (InvalidShipPlacementException e) {
			e.printStackTrace();
		}
		
	}
	
	public void turn(int x, int y) {
		System.out.println("Du hast auf "+x+","+y+" geschossen!");
		
		Tile hitTile = MatchPanel.whatDidIHit(getClass(), x, y);
		
		if(hitTile == Tile.WATER) {
			System.out.println("Leider kein Treffer...");
			
			Main.gui.matchPanel.field1.setColor(x, y, Color.GRAY);
			
		}else if(hitTile == Tile.SHIP) {
			System.out.println("Treffer!");
			
			Main.gui.matchPanel.field1.setColor(x, y, Color.RED);

		}else if(hitTile == Tile.SHIP_KILL) {
			System.out.println("Treffer, versenkt!");
			Ship hitShip = getHitShip(x, y);
		
			for(Position pos : hitShip.getOccupiedSpaces()) {
				Main.gui.matchPanel.field1.setColor(pos.x, pos.y, Color.BLACK);
			}
			
			
		}
		
		
	}
	
	private Ship getHitShip(int x, int y) {
		for(int i = 0; i < ships.length; i++) {
			for(Position pos : ships[i].getOccupiedSpaces()) {
				if(pos.equals(new Position(x, y))) {
					return ships[i];
				}
			}
		}
		return null;
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
	
}
