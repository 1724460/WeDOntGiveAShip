package com.weDontGiveAShip.UI.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.weDontGiveAShip.interfaces.Direction;
import com.weDontGiveAShip.interfaces.Position;
import com.weDontGiveAShip.interfaces.Ship;

public class ShipPlacerPanel extends JPanel {

	public final static int[] DEFAULT_SHIP_AMOUNT = { 0, 5, 3, 2, 1 };

	private ArrayList<Ship> ships;
	private int currentShipLength;
	private Direction currentShipDirection;
	private int[] shipAmount;

	public ShipPlacerPanel() {
		// currentShipPanel
		JPanel currentShipPanel = new JPanel();
		JButton switchDirectionButton = new JButton();
		switchDirectionButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (currentShipDirection == Direction.HORIZONTAL)
					currentShipDirection = Direction.VERTICAL;

				else
					currentShipDirection = Direction.VERTICAL;
			}

		});

		// create ships
		ships = new ArrayList<>();
		shipAmount = DEFAULT_SHIP_AMOUNT;
		for (int i = 0; i < shipAmount.length; i++) {
			for (int s = 0; s < shipAmount[i]; s++) {

			}
		}
		currentShipDirection = Direction.VERTICAL;
		currentShipLength = 4;

		setLayout(new BorderLayout());
		add(new FieldPanel(10, true) {

			@Override
			public void onClick(int x, int y) {
				ships.add(new Ship(new Position(x, y), currentShipDirection, currentShipLength));
				for(int i = 0; i < currentShipLength; i++) {
					Position clicked = new Position(x, y);
					Position p = clicked.add(currentShipDirection.positive.multiply(i));
					super.setColor(p.x, p.y, Color.GRAY);
				}
				
			}

		}, BorderLayout.CENTER);
	}
}
