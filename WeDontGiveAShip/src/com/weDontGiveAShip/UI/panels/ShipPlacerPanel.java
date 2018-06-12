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

	private ArrayList<Integer> previousShipLength;
	private ArrayList<Integer> previousShipAmount;
	
	private Direction currentShipDirection = Direction.HORIZONTAL;

	
	private FieldPanel fieldPanel;
	private boolean everyShipIsPlaced;
	
	JButton commitButton;

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
		
		commitButton = new JButton("Commit");
		commitButton.setSize(30, 30);
		commitButton.setEnabled(false);;
		commitButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Ship[] s = new Ship[ships.size()];
				ships.toArray(s);
				Main.gui.openMatchPanel(s);
			}

		});

		JButton undoButton = new JButton("Undo");
		undoButton.setSize(30, 30);
		undoButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (ships.size() > 0) {
					undo();
					shipLengthLabel.setText("LENGTH: " + currentShipLength);
					shipAmountLabel.setText("AMOUNT: " + currentShipAmount);
				}
			}

		});

		currentShipPanel.add(undoButton);
		currentShipPanel.add(switchDirectionButton);
		currentShipPanel.add(shipLengthLabel);
		currentShipPanel.add(shipAmountLabel);
		currentShipPanel.add(commitButton);
		add(currentShipPanel, BorderLayout.NORTH);

		shipAmount = DEFAULT_SHIP_AMOUNT;
		currentShipLength = 0;
		currentShipAmount = shipAmount[0];
		nextShip();
		shipLengthLabel.setText("LENGTH: " + currentShipLength);
		shipAmountLabel.setText("AMOUNT: " + currentShipAmount);

		// create ships
		ships = new ArrayList<>();
		previousShipAmount = new ArrayList<>();
		previousShipLength = new ArrayList<>();

		add(fieldPanel = new FieldPanel(10, true) {

			@Override
			public void setColor(int x, int y, Color color) {
				super.setColor(x, y, color);
			}

			@Override
			public void onClick(int x, int y) {
				if(!everyShipIsPlaced) {
				for (int i = 0; i < currentShipLength; i++) {
					Position clicked = new Position(x, y);
					Position p = clicked.add(currentShipDirection.positive.multiply(i));
					setColor(p.x, p.y, Color.GRAY);

				}
				super.onClick(x, y);
				
				ships.add(new Ship(new Position(x, y), currentShipDirection, currentShipLength));
				previousShipAmount.add(currentShipAmount);
				previousShipLength.add(currentShipLength);

				if (!nextShip()) {
					commitButton.setEnabled(true);
					everyShipIsPlaced = true;
				}
				shipLengthLabel.setText("LENGTH: " + currentShipLength);
				shipAmountLabel.setText("AMOUNT: " + currentShipAmount);

			}
			}

		}, BorderLayout.CENTER);
		
	}

	private boolean nextShip() {
		currentShipAmount--;
		if (currentShipAmount <= 0) {
			currentShipLength++;
			if (currentShipLength < shipAmount.length) {
				currentShipAmount = shipAmount[currentShipLength];
				if (currentShipAmount == 0) {
					nextShip();
				}
				return true;
			} else
				return false;
		}
		return true;
	}

	private void undo() {
		currentShipAmount = previousShipAmount.remove(previousShipAmount.size()-1);
		currentShipLength = previousShipLength.remove(previousShipLength.size()-1);
		Ship s = ships.remove(ships.size() - 1);
		for (Position p : s.getOccupiedSpaces()) {
			fieldPanel.setColor(p.x, p.y, Color.WHITE);
		}
		everyShipIsPlaced = false;
		commitButton.setEnabled(false);

	}

}
