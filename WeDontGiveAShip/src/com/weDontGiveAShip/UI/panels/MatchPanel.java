package com.weDontGiveAShip.UI.panels;



import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.weDontGiveAShip.UI.Gui;
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
//	private JTextArea logArea;
	
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
			private static final long serialVersionUID = 1L;

			@Override
			public void setColor(int x, int y, Color color) {
				super.setColor(x, y, color);
			}

			@Override
			public void onClick(int x, int y) {
				if(PlayerImpl.alreadyShotOnPositions.contains(new Position(x, y))) {
					JOptionPane.showMessageDialog(null, "Du hast bereits auf dieses Feld geschossen!");
					return;
				}
				
				Tile tileThatPlayer1Hit = p1.turn(x, y);
				checkGameOver();
				p2.onEnemyShot(new Position(x, y), tileThatPlayer1Hit, getHitShip(PlayerImpl.class, x, y));
				
				
				System.out.println("-----------------------------------");
				//	AI schießt jetzt
				p2.turn();
				
				checkGameOver();
				
			}

			
			
			
			private void checkGameOver() {
				Player winningPlayer = isGameOver();
				
				if(winningPlayer != null) {
					
					//	AI won
					if(winningPlayer instanceof AI) {
						JOptionPane.showMessageDialog(null, "Du hast leider verloren!");

					//	Player won
					}else {
						JOptionPane.showMessageDialog(null, "Du hast gewonnen!");
					}
					
					System.exit(0);
				}
				
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

	public static Player isGameOver() {
		Player winningPlayer = Main.gui.matchPanel.p1;
		
		for(Ship ship : AI.ships) {
			if(!ship.isSunk()) {
				winningPlayer = null;
			}
		}
		
		if(winningPlayer != null) {
			return winningPlayer;
		}
		
		winningPlayer = Main.gui.matchPanel.p2;
		
		for(Ship ship : PlayerImpl.ships) {
			if(!ship.isSunk()) {
				winningPlayer = null;
			}
		}
		
		
		return winningPlayer;
	}
	
	/** Pass class of the player that shot **/
	public static Tile whatDidIHit(Class<? extends Player> class1, int x, int y) {
		Position pos = new Position(x, y);
		
		if(class1.equals(AI.class)) {
			Ship[] ships = PlayerImpl.ships;
			
			for (int i = 0; i < ships.length; i++) {
				for (Position position : ships[i].getOccupiedSpaces()) {
					if(position.equals(pos)) {
						ships[i].takeHit();
						
						if(ships[i].isSunk()) {
							System.out.println("*** Es wurde eins deiner Schiffe an Position '"+((char)(x+1+'0'))+""+y+"' getroffen und versenkt! ***");
							return Tile.SHIP_KILL;
						}else {
							System.out.println("*** Es wurde eins deiner Schiffe an Position '"+((char)(x+1+'A'))+""+y+"' getroffen! ***");
							return Tile.SHIP;
						}
						
					}
				}
			}
			
			System.out.println("*** Es wurde keins von deinen Schiffen getroffen! ***");
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
	
	
	/** Pass class of the player of which ships you refer to **/
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
	
//	/**		Class of the player that just shot (AI -> redraw lower field, Player -> redraw upper)	**/
	public void redraw() {

		for(int y = 0; y < PLAYFIELD_SIZE; y++) {
			for(int x = 0; x < PLAYFIELD_SIZE; x++) {
				Position position = new Position(x, y);
				
				if(PlayerImpl.alreadyShotOnPositions.contains(position)) {
					field1.setColor(x, y, Color.GRAY);
				}
				
				if(AI.alreadyShotOnPositions.contains(position)) {
					field2.setColor(x, y, Color.GRAY);
				}
				
			}
		}
		
		//	Update AI ships
		for(Ship ship : AI.ships) {
			Color color;
			
			if(ship.isSunk()) {
				color = Color.BLACK;
				
			}else {
				color = Color.RED;
			}	
			
			for(Position pos : ship.getOccupiedSpaces()) {
				if(PlayerImpl.alreadyShotOnPositions.contains(pos)) {
					field1.setColor(pos.x, pos.y, color);
				}
			}
		}
		
		//	update player ships
		for(Ship ship : PlayerImpl.ships) {
			Color color;
			
			if(ship.isSunk()) {
				color = Color.BLACK;
				
			}else {
				color = Color.RED;
			}	
			
			for(Position pos : ship.getOccupiedSpaces()) {
				if(AI.alreadyShotOnPositions.contains(pos)) {
					field2.setColor(pos.x, pos.y, color);
				}
			}
		}
		
		
		Gui.update();
	}
	
}
