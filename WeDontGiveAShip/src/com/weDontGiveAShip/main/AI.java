package com.weDontGiveAShip.main;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;

import com.weDontGiveAShip.UI.panels.FieldPanel;
import com.weDontGiveAShip.interfaces.Direction;
import com.weDontGiveAShip.interfaces.InvalidShipPlacementException;
import com.weDontGiveAShip.interfaces.Player;
import com.weDontGiveAShip.interfaces.Position;
import com.weDontGiveAShip.interfaces.Ship;
import com.weDontGiveAShip.interfaces.ShipPlacer;
import com.weDontGiveAShip.interfaces.Tile;
import com.weDontGiveAShip.interfaces.TurnAction;

public class AI implements Player {

	public static final int SHIP_COUNT = 11;
	public static final int SHIP_COUNT_1 = 0;
	public static final int SHIP_COUNT_2 = 5;
	public static final int SHIP_COUNT_3 = 4;
	public static final int SHIP_COUNT_4 = 2;
	public static final int SHIP_COUNT_5 = 1;

	public static final int PLAYFIELD_SIZE = 10;

	List<Position> alreadyTakenPositions = new ArrayList<Position>();
	static FieldPanel panel;

	public static void main(String args[]) {
		JFrame f = new JFrame();

		f.setSize(600, 800);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panel = new FieldPanel(PLAYFIELD_SIZE, false);
		f.add(panel);
		f.setVisible(true);

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
		// implementation no touch
		Ship ships[] = new Ship[SHIP_COUNT];

//		ships[0] = generateShip(2);
//		ships[1] = generateShip(2);
//		ships[2] = generateShip(2);
//		ships[3] = generateShip(2);
//		ships[4] = generateShip(2);
//		ships[5] = generateShip(3);
//		ships[6] = generateShip(3);
//		ships[7] = generateShip(3);
//		ships[8] = generateShip(3);
//		ships[9] = generateShip(4);
//		ships[10] = generateShip(4);
//		ships[11] = generateShip(5);

		ships = getPreset();
		
		for (int i = 0; i < ships.length; i++) {
			for(Position position : ships[i].getOccupiedSpaces()) {
				panel.setColor(position.y, position.x, Color.RED);
			}
		}

		return ships;
	}

	private Ship generateShip(int size) {
		Random rand = new Random();


		Ship ship;
		boolean continueSearching = false;

		Position position;
		Direction direction;
		
		do {
		
			direction = getRandomDirection();
			
			position = getRandomPosition(size, direction);

			ship = new Ship(position, direction, size);
			
			
			for (Position occupiedPosition : ship.getOccupiedSpaces()) {
				
				System.out.println(occupiedPosition.x+", "+occupiedPosition.y + "test");
				
				if (alreadyTakenPositions.contains(occupiedPosition)) {
					continueSearching = true;
					System.out.println("ye");
//					System.exit(0);
				}
			}
			System.out.println("-----------------");
			
		} while (continueSearching);

		
		for (Position occupiedPosition : ship.getOccupiedSpaces()) {
			alreadyTakenPositions.add(occupiedPosition);
		}
		
		alreadyTakenPositions.forEach(p -> System.out.println(p.toString() + "asd"));
		
		return ship;
	}

	private Position getRandomPosition(int size, Direction direction) {
		Random rand = new Random();
		
		int x, y;
		
		if (direction == Direction.HORIZONTAL) {
			x = rand.nextInt(PLAYFIELD_SIZE-1 - size);
			y = rand.nextInt(PLAYFIELD_SIZE-1);

		} else {
			x = rand.nextInt(PLAYFIELD_SIZE-1);
			y = rand.nextInt(PLAYFIELD_SIZE-1 - size);
		}
		
		return new Position(x, y);
	}

	private Direction getRandomDirection() {
		Direction direction;

		// Random boolean für Ausrichtung
		if (new Random().nextBoolean()) {
			direction = Direction.HORIZONTAL;

		} else {
			direction = Direction.VERTICAL;
		}
		
		return direction;
	}
	
	private Ship[] getPreset() {
		Ship[] ships = new Ship[SHIP_COUNT];
		
		ships[0] = new Ship(new Position(1, 1), Direction.VERTICAL, 2);
		ships[1] = new Ship(new Position(4, 3), Direction.VERTICAL, 2);
		ships[2] = new Ship(new Position(8, 6), Direction.HORIZONTAL, 2);
		ships[3] = new Ship(new Position(4, 6), Direction.HORIZONTAL, 2);
		ships[4] = new Ship(new Position(5, 6), Direction.HORIZONTAL, 2);
		ships[5] = new Ship(new Position(7, 3), Direction.HORIZONTAL, 3);
		ships[6] = new Ship(new Position(1, 5), Direction.HORIZONTAL, 3);
		ships[7] = new Ship(new Position(6, 0), Direction.VERTICAL, 3);
		ships[8] = new Ship(new Position(2, 9), Direction.VERTICAL, 4);
		ships[9] = new Ship(new Position(4, 2), Direction.VERTICAL, 4);
		ships[10] = new Ship(new Position(9, 3), Direction.HORIZONTAL, 5);
		
		return ships;
	}
	
}
