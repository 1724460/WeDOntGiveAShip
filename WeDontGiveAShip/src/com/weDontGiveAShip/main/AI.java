package com.weDontGiveAShip.main;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;

import com.weDontGiveAShip.UI.panels.FieldPanel;
import com.weDontGiveAShip.interfaces.Direction;
import com.weDontGiveAShip.interfaces.InvalidActionException;
import com.weDontGiveAShip.interfaces.InvalidShipPlacementException;
import com.weDontGiveAShip.interfaces.Player;
import com.weDontGiveAShip.interfaces.Position;
import com.weDontGiveAShip.interfaces.Ship;
import com.weDontGiveAShip.interfaces.ShipPlacer;
import com.weDontGiveAShip.interfaces.Tile;
import com.weDontGiveAShip.interfaces.TurnAction;

public class AI extends PlayerImpl {

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
		
		Position shootAtPosition = getShootAtPosition();
		
		try {
			turnAction.shootTile(shootAtPosition);
		} catch (InvalidActionException e) {
			e.printStackTrace();
		}

	}

	private Position getShootAtPosition() {
		//	hier bestimmen auf welches feld die ai schießen soll
		return null;
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

		ships[0] = generateShip(2);
		ships[1] = generateShip(2);
		ships[2] = generateShip(2);
		ships[3] = generateShip(2);
		ships[4] = generateShip(2);
		ships[5] = generateShip(3);
		ships[6] = generateShip(3);
		ships[7] = generateShip(3);
		ships[8] = generateShip(4);
		ships[9] = generateShip(4);
		ships[10] = generateShip(5);
	
		for (int i = 0; i < ships.length; i++) {
			for(Position position : ships[i].getOccupiedSpaces()) {
				panel.setColor(position.x, position.y, Color.RED);
			}
		}

		return ships;
	}

	private Ship generateShip(int size) {
		Ship ship;
		boolean continueSearching = false;

		Position position;
		Direction direction;
		
		do {
			continueSearching = false;
			
			direction = getRandomDirection();
			
			position = getRandomPosition(size, direction);

			ship = new Ship(position, direction, size);
			
			
			for (Position occupiedPosition : ship.getOccupiedSpaces()) {
				if (alreadyTakenPositions.contains(occupiedPosition)) {
					continueSearching = true;
				}
			}
			
		} while (continueSearching);
		
		return ship;
	}

	private Position getRandomPosition(int size, Direction direction) {
		Random rand = new Random();
		
		int x, y;
		
		if (direction == Direction.HORIZONTAL) {
			x = rand.nextInt(PLAYFIELD_SIZE - 1);
			y = rand.nextInt(PLAYFIELD_SIZE - 1 - size);

		} else {
			x = rand.nextInt(PLAYFIELD_SIZE - 1 - size);
			y = rand.nextInt(PLAYFIELD_SIZE - 1);
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
	
}
