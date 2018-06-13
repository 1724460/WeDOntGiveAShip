package com.weDontGiveAShip.main;

import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

import javax.swing.JFrame;

import com.weDontGiveAShip.UI.panels.FieldPanel;
import com.weDontGiveAShip.interfaces.Direction;
import com.weDontGiveAShip.interfaces.InvalidActionException;
import com.weDontGiveAShip.interfaces.InvalidShipPlacementException;
import com.weDontGiveAShip.interfaces.Position;
import com.weDontGiveAShip.interfaces.Ship;
import com.weDontGiveAShip.interfaces.ShipPlacer;
import com.weDontGiveAShip.interfaces.Tile;
import com.weDontGiveAShip.interfaces.TurnAction;

public class AI extends PlayerImpl {

	public static final int SHIP_COUNT_1 = 0;
	public static final int SHIP_COUNT_2 = 5;
	public static final int SHIP_COUNT_3 = 4;
	public static final int SHIP_COUNT_4 = 2;
	public static final int SHIP_COUNT_5 = 1;
	// total number of ships
	public static final int SHIP_COUNT = SHIP_COUNT_1 + SHIP_COUNT_2 + SHIP_COUNT_3 + SHIP_COUNT_4 + SHIP_COUNT_5;

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

	// A list, that contains every Position the AI initially hit and is currently
	// targetting
	List<TargettedShip> targetList = new ArrayList<TargettedShip>();
	Queue<TargettedShip> targetQueue = new LinkedList<TargettedShip>();
	Queue<AIPrediction> lastPredictions = new LinkedList<AIPrediction>();

	TargettedShip currentTarget = null;

	@Override
	public void takeTurn(TurnAction turnAction) {
		Position shootAtPosition = getShootAtPosition();

		Tile hitField;

		try {
			hitField = turnAction.shootTile(shootAtPosition);
		} catch (InvalidActionException e) {
			e.printStackTrace();
			hitField = null;
		}

		// Hit a ship
		if (hitField == Tile.SHIP) {

			// Hit a already known ship
			if (hitAlreadyKnownShip(shootAtPosition)) {

				// Hit a previously unknown ship
			} else {
				targetQueue.add(new TargettedShip(shootAtPosition));
			}

			// Successfully destroyed a ship
		} else if (hitField == Tile.SHIP_KILL) {

			// Hit water
		} else if (hitField == Tile.WATER) {
			// do nothing i guess
		}

	}

	private Position getShootAtPosition() {
		Position shootAt = null;

		int predictionLocation;
		
		AIPrediction prevPrediction = lastPredictions.peek();
		
		if (currentTarget == null) {
			// random shot auf das feld
			
			
		} else {
			
			if (prevPrediction.success) {
				
				if (prevPrediction.location == AIPrediction.UP) {
					// predictionLocation =

				} else if (prevPrediction.location == AIPrediction.RIGHT) {

				} else if (prevPrediction.location == AIPrediction.DOWN) {

				} else if (prevPrediction.location == AIPrediction.LEFT) {

				}
				
				
			//	did not hit the ship last time
			}else {
				
				//	hier shot in der nähe machen
				
				
			}

		}

		return shootAt;
	}

	private boolean hitAlreadyKnownShip(Position shootAtPosition) {
		for (TargettedShip ship : targetList) {
			for (Position pos : ship.getHitPositions()) {

				if (pos.equals(shootAtPosition)) {
					return true;
				}

			}
		}

		return false;
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
		List<Ship> shipList = new ArrayList<Ship>();

		// add ships of size 1
		for (int i = 0; i < SHIP_COUNT_1; i++) {
			shipList.add(generateShip(1));
		}

		// add ships of size 2
		for (int i = 0; i < SHIP_COUNT_2; i++) {
			shipList.add(generateShip(2));
		}

		// add ships of size 3
		for (int i = 0; i < SHIP_COUNT_3; i++) {
			shipList.add(generateShip(3));
		}

		// add ships of size 4
		for (int i = 0; i < SHIP_COUNT_4; i++) {
			shipList.add(generateShip(4));
		}

		// add ships of size 5
		for (int i = 0; i < SHIP_COUNT_5; i++) {
			shipList.add(generateShip(5));
		}

		// zeichnet auf den test frame hier in der klasse (kann später gelöscht werden)
		for (int i = 0; i < shipList.size(); i++) {
			for (Position position : shipList.get(i).getOccupiedSpaces()) {
				panel.setColor(position.x, position.y, Color.RED);
			}
		}

		/*
		 * Hier könnte man noch einfügen, dass je nach Spielregeln, re-generated wird.
		 * (oder wir lassen es einfach bei "Berührungen erlaubt")
		 */

		return shipList.toArray(new Ship[SHIP_COUNT]);
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
