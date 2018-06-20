package com.weDontGiveAShip.main;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFrame;

import com.weDontGiveAShip.UI.panels.FieldPanel;

import se1.schiffeVersenken.interfaces.Ship;
import se1.schiffeVersenken.interfaces.ShipPlacer;
import se1.schiffeVersenken.interfaces.exception.shipPlacement.InvalidShipPlacementException;
import se1.schiffeVersenken.interfaces.util.Position;

public class ShipPlacerImpl implements ShipPlacer {

	
	
	@Override
	public void setShips(Ship[] ships) throws InvalidShipPlacementException {
		
		JFrame debug = new JFrame();
		
		debug.setTitle("DEBUG WINDOW FOR SHIP PLACEMENTS");
		debug.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		debug.setSize(600, 800);
		debug.setLocationRelativeTo(null);
		
		FieldPanel fp = new FieldPanel(10, false);
		
		debug.add(fp);
		
		
		// zeichnet auf den test frame hier in der klasse (kann später gelöscht werden)
		List<Ship> shipList = Arrays.asList(ships);
		
		for (int i = 0; i < shipList.size(); i++) {
			for (Position position : shipList.get(i).getOccupiedSpaces()) {
				System.out.println(position.x+";"+position.y);
			
				fp.setColor(position.x, position.y, Color.RED);
			}
		}
		
		
		
		debug.setVisible(true);
	}

}
