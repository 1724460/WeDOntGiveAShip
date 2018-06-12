package com.weDontGiveAShip.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.weDontGiveAShip.interfaces.Direction;
import com.weDontGiveAShip.interfaces.InvalidShipPlacementException;
import com.weDontGiveAShip.interfaces.Player;
import com.weDontGiveAShip.interfaces.Position;
import com.weDontGiveAShip.interfaces.Ship;
import com.weDontGiveAShip.interfaces.ShipPlacer;
import com.weDontGiveAShip.interfaces.Tile;
import com.weDontGiveAShip.interfaces.TurnAction;

public class AI implements Player {

	public static final int SHIP_COUNT = 12;
	public static final int SHIP_COUNT_1 = 0;
	public static final int SHIP_COUNT_2 = 5;
	public static final int SHIP_COUNT_3 = 4;
	public static final int SHIP_COUNT_4 = 2;
	public static final int SHIP_COUNT_5 = 1;
	
	public static final int PLAYFIELD_SIZE = 10;

	List<Position> alreadyTakenPositions = new ArrayList<Position>();
	
	public static void main(String args[]) {
		new AI().placeShips(new ShipPlacerImpl());
	}
	
	@Override
	public void placeShips(ShipPlacer placer) {
		Ship[] ships = generateShips();
		
		try {
			placer.setShips(ships);
		} catch (InvalidShipPlacementException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void takeTurn(TurnAction turnAction) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onEnemyShot(Position position, Tile tile, Ship ship) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void gameOver(boolean youHaveWon) {
		// TODO Auto-generated method stub
	}
	
	public Ship[] generateShips() {
		//	implementation no touch
		Ship ships[] = new Ship[SHIP_COUNT];
		
		
		ships[0] = generateShip(2);
		ships[1] = generateShip(2);
		ships[2] = generateShip(2);
		ships[3] = generateShip(2);
		ships[4] = generateShip(2);
		ships[5] = generateShip(3);
		ships[6] = generateShip(3);
		ships[7] = generateShip(3);
		ships[8] = generateShip(3);
		ships[9] = generateShip(4);
		ships[10] = generateShip(4);
		ships[11] = generateShip(5);
		
		
		for(int i = 0; i<ships.length; i++) {
			System.out.println(ships[i].toString());
			System.out.println("-----------------");
		}
		
		return ships;
	}

	private Ship generateShip(int size) {
		Ship ship;
		Random rand = new Random();
		
		Direction direction;
		
		//	Random boolean für Ausrichtung
		if(rand.nextBoolean()) {
			direction = Direction.HORIZONTAL;
			
		}else {
			direction = Direction.VERTICAL;
		}
		
		//	is true if direction == horizontal
		boolean isHorizontal = (direction == Direction.HORIZONTAL);
		
		int x;
		int y;
		
		if(isHorizontal) {
			x = rand.nextInt(PLAYFIELD_SIZE-size);
			y = rand.nextInt(PLAYFIELD_SIZE);
			
		}else {
			x = rand.nextInt(PLAYFIELD_SIZE);
			y = rand.nextInt(PLAYFIELD_SIZE-size);
		}
		
		
		Position position = new Position(x, y);
		
		while(alreadyTakenPositions.contains(position)) {
			
			if(isHorizontal) {
				x = rand.nextInt(PLAYFIELD_SIZE-size);
				y = rand.nextInt(PLAYFIELD_SIZE);
				
			}else {
				x = rand.nextInt(PLAYFIELD_SIZE);
				y = rand.nextInt(PLAYFIELD_SIZE-size);
			}
			
			position = new Position(x, y);
		}
		
		
		return new Ship(position, direction, size);
	}
	
}
