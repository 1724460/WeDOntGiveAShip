package com.weDontGiveAShip.UI.panels;



import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.Arrays;
import java.util.Collections;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import com.weDontGiveAShip.main.AI;
import com.weDontGiveAShip.main.Main;
import com.weDontGiveAShip.main.PlayerImpl;
import com.weDontGiveAShip.main.ShipPlacerImpl;
import com.weDontGiveAShip.main.TurnActionImpl;

import se1.schiffeVersenken.interfaces.Player;
import se1.schiffeVersenken.interfaces.Ship;
import se1.schiffeVersenken.interfaces.ShipPlacer;
import se1.schiffeVersenken.interfaces.Tile;
import se1.schiffeVersenken.interfaces.util.Position;

public class MatchPanel extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	private JPanel fields;
	public FieldPanel field1, field2;
	private JTextArea logArea;
	
	public PlayerImpl p1;
	public AI p2;
	
	TurnActionImpl p1TurnAction;
	TurnActionImpl p2TurnAction = new TurnActionImpl();
	
	public MatchPanel(Ship[] playerShips) {
		ShipPlacer shipPlacer = new ShipPlacerImpl();
		
		p1 = (PlayerImpl) new PlayerImpl().createPlayer(Main.getSettings(), PlayerImpl.class);
		p2 = (AI) new AI().createPlayer(Main.getSettings(), AI.class);
		
		//	set ships of player
		p1.setShips(playerShips);
		p1.placeShips(shipPlacer);
		
		//	Feld auf dem man klick, wohin man schießen möchte, überschreibt onClick zum Schuss verarbeiten
		field1 = new FieldPanel(10, true) {

			@Override
			public void setColor(int x, int y, Color color) {
				super.setColor(x, y, color);
			}

			@Override
			public void onClick(int x, int y) {
				if(PlayerImpl.alreadyShotOnPositions.contains(new Position(x, y))) {
					System.out.println("Du kannst nicht nochmal auf dieses Feld schießen!");
					return;
				}
				
				p1.turn(x, y);
				
				//	AI schießt jetzt
//				p2.turn();
				
			}

		};
		
		
		
		
		
		field2 = new FieldPanel(10, false);
		
		//	zeichnet schiffe aufs gui
		for(Ship s : p1.getShips()) {
			for(Position p : s.getOccupiedSpaces()) {
				field2.setColor(p.x, p.y, Main.SHIP_COLOR);
			}
		}
		
		
		fields = new JPanel();
		
		fields.setLayout(new GridLayout(3,1));
		
		
		fields.add(field1);
		fields.add(field2);
		
		add(fields, BorderLayout.CENTER);
	}

	public static Tile whatDidIHit(Class<? extends Player> class1, int x, int y) {
		Position pos = new Position(x, y);
		
		//TODO: DEBUG MSG
		System.out.println(class1);
		
		if(class1.equals(AI.class)) {
			Ship[] ships = PlayerImpl.ships;
			
			for (int i = 0; i < ships.length; i++) {
				for (Position position : ships[i].getOccupiedSpaces()) {
					if(position.equals(pos)) {
						ships[i].takeHit();
						
						if(ships[i].isSunk()) {
							return Tile.SHIP_KILL;
						}else {
							return Tile.SHIP;
						}
						
					}
				}
			}
			
			return Tile.WATER;
			
		}else {
			Ship[] ships = AI.ships;
			
			for (int i = 0; i < ships.length; i++) {
				for (Position position : ships[i].getOccupiedSpaces()) {
					if(position.equals(pos)) {
						ships[i].takeHit();
						
						if(ships[i].isSunk()) {
							return Tile.SHIP_KILL;
						}else {
							return Tile.SHIP;
						}
						
					}
				}
			}
			
			return Tile.WATER;
		}
		
	}
	
	public static Ship getHitShip(Class<? extends Player> class1, int x, int y) {
		Ship[] ships;
		
		if(class1.equals(AI.class)) {
			ships = AI.ships;
		}else {
			ships = PlayerImpl.ships;
		}
			
		for(int i = 0; i < ships.length; i++) {
			for(Position pos : ships[i].getOccupiedSpaces()) {
				if(pos.equals(new Position(x, y))) {
					return ships[i];
				}
			}
		}
		
		return null;
	}
	
	public static final int PLAYFIELD_SIZE = 10;
	
	public void redraw() {
		Ship[] ships = AI.ships;

		for(int y = 0; y < PLAYFIELD_SIZE; y++) {
			for(int x = 0; x < PLAYFIELD_SIZE; x++) {
				if(PlayerImpl.alreadyShotOnPositions.contains(new Position(x, y))) {
					Main.gui.matchPanel.field1.setColor(x, y, Color.GRAY);
				}
			}
		}
		
		for(Ship ship : ships) {
			Color color;
			
			if(ship.isSunk()) {
				color = Color.BLACK;
				
			}else {
				color = Color.RED;
			}	
			
			for(Position pos : ship.getOccupiedSpaces()) {
				if(PlayerImpl.alreadyShotOnPositions.contains(pos)) {
					Main.gui.matchPanel.field1.setColor(pos.x, pos.y, color);
				}
			}
		}
		
		SwingUtilities.updateComponentTreeUI(Main.gui.matchPanel);
		Main.gui.matchPanel.repaint();
		SwingUtilities.updateComponentTreeUI(Main.gui);
		Main.gui.repaint();
		
		
		
		System.out.println("---");
		Arrays.asList(AI.ships).forEach(ship -> System.out.println(ship));
		System.out.println("---");
		System.out.println(PlayerImpl.alreadyShotOnPositions);
	}
	
}
