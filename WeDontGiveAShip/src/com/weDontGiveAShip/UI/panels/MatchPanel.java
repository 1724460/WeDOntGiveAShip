package com.weDontGiveAShip.UI.panels;



import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import com.weDontGiveAShip.UI.Gui;
import com.weDontGiveAShip.main.AI;
import com.weDontGiveAShip.main.Main;
import com.weDontGiveAShip.main.PlayerCreatorImpl;
import com.weDontGiveAShip.main.PlayerImpl;
import com.weDontGiveAShip.main.ShipPlacerImpl;

import se1.schiffeVersenken.interfaces.PlayerCreator;
import se1.schiffeVersenken.interfaces.Ship;
import se1.schiffeVersenken.interfaces.ShipPlacer;
import se1.schiffeVersenken.interfaces.util.Position;

public class MatchPanel extends JPanel{
	
	private JPanel fields;
	private FieldPanel field1, field2;
	private JTextArea logArea;
	
	public MatchPanel() {
		PlayerCreatorImpl pc = new PlayerCreatorImpl();
		ShipPlacer shipPlacer = new ShipPlacerImpl();
		
		PlayerImpl p1 = (PlayerImpl) pc.createPlayer(Main.getSettings(), PlayerCreator.class);
		AI p2 = (AI) pc.createPlayer(Main.getSettings(), PlayerCreator.class);
		
		//	set ships of player
		p1.setShips(Gui.shipPlacer.getShips());
		p1.placeShips(shipPlacer);
		
		//	Feld auf dem man klick, wohin man schießen möchte
		field1 = new FieldPanel(10, true);
		
		field2 = new FieldPanel(10, false);
		
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
