package com.weDontGiveAShip.UI.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.weDontGiveAShip.interfaces.Direction;
import com.weDontGiveAShip.interfaces.Position;
import com.weDontGiveAShip.interfaces.Ship;
import com.weDontGiveAShip.main.Main;

public class ShipPlacerPanel extends JPanel {

	public final static int[] DEFAULT_SHIP_AMOUNT = { 0, 0, 5, 3, 2, 1 };

	private int[] shipAmount;
	private ArrayList<Ship> ships;
	
	private int currentShipLength;
	private int currentShipAmount;
	private Direction currentShipDirection = Direction.HORIZONTAL;
	
	private boolean[][] occupied;

	
	public ShipPlacerPanel() {
		setLayout(new BorderLayout());
		
	
		// currentShipPanel
		JPanel currentShipPanel = new JPanel();
		currentShipPanel.setLayout(new FlowLayout());
		
		
		JLabel shipLengthLabel = new JLabel("XXX");
		JLabel shipAmountLabel = new JLabel("XXX");
		
		JButton switchDirectionButton = new JButton(currentShipDirection.toString());
		switchDirectionButton.setSize(30, 30);
		switchDirectionButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (currentShipDirection == Direction.HORIZONTAL)
					currentShipDirection = Direction.VERTICAL;

				else
					currentShipDirection = Direction.HORIZONTAL;
				
				switchDirectionButton.setText(currentShipDirection.toString());
			}
		

		});
		currentShipPanel.add(switchDirectionButton);
		currentShipPanel.add(shipLengthLabel);
		currentShipPanel.add(shipAmountLabel);
		add(currentShipPanel, BorderLayout.NORTH);
		
		
		shipAmount = DEFAULT_SHIP_AMOUNT;
		currentShipLength = 0;
		currentShipAmount = shipAmount[0];
		nextShip();
		shipLengthLabel.setText("LENGTH: " +currentShipLength);
		shipAmountLabel.setText("AMOUNT: " +currentShipAmount);
			
		
		
		
		// create ships
		ships = new ArrayList<>();
		add(new FieldPanel(10, true) {
			

			@Override
			public void onClick(int x, int y) {
				ships.add(new Ship(new Position(x, y), currentShipDirection, currentShipLength));
				for(int i = 0; i < currentShipLength; i++) {
					Position clicked = new Position(x, y);
					Position p = clicked.add(currentShipDirection.positive.multiply(i));
					super.setColor(p.x, p.y, Color.GRAY);
					
				
				}
				
				ships.add(new Ship(new Position(x, y), currentShipDirection, currentShipLength));
				
				if(!nextShip()) {
					Main.gui.openMatchPanel();
				}
				shipLengthLabel.setText("LENGTH: " +currentShipLength);
				shipAmountLabel.setText("AMOUNT: " +currentShipAmount);
				
			}

		}, BorderLayout.CENTER);
	}
	
	private boolean nextShip() {
		currentShipAmount--;
		if(currentShipAmount <= 0) {
			currentShipLength++;
			if(currentShipLength < shipAmount.length) {
				currentShipAmount = shipAmount[currentShipLength];
				if(currentShipAmount == 0) {
					nextShip();
				}
				return true;
			}
			else
				return false;
		}
		return true;
	}
}
