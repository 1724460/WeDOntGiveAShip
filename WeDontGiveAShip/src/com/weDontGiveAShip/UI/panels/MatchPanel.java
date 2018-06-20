package com.weDontGiveAShip.UI.panels;



import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import com.weDontGiveAShip.main.AI;
import com.weDontGiveAShip.main.Main;
import com.weDontGiveAShip.main.PlayerImpl;
import com.weDontGiveAShip.main.ShipPlacerImpl;

import se1.schiffeVersenken.interfaces.PlayerCreator;
import se1.schiffeVersenken.interfaces.Ship;
import se1.schiffeVersenken.interfaces.ShipPlacer;
import se1.schiffeVersenken.interfaces.Tile;
import se1.schiffeVersenken.interfaces.TurnAction;
import se1.schiffeVersenken.interfaces.GameSettings.ShipBorderConditions;
import se1.schiffeVersenken.interfaces.exception.action.InvalidActionException;
import se1.schiffeVersenken.interfaces.util.Position;

public class MatchPanel extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	private JPanel fields;
	private FieldPanel field1, field2;
	private JTextArea logArea;
	
	PlayerImpl p1;
	AI p2;
	
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
				System.out.println("Du hast auf +"+x+","+y+" geschossen!");
				
				super.setColor(x,y, Color.RED);
				
				p1.turn();
				
//				p1.takeTurn(new TurnAction() {
//					
//					@Override
//					protected Tile shootTile0(Position position) throws InvalidActionException {
//						// TODO Auto-generated method stub
//						return null;
//					}
//				});
				
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
	
}
