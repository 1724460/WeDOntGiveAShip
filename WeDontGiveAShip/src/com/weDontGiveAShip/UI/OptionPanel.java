package com.weDontGiveAShip.UI;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class OptionPanel extends JPanel {
	
	private static final int MAX_SHIP_SIZE = 10;
	private static final int MIN_SHIP_SIZE = 2;

	public JTextField[] shipAmountFields;

	public OptionPanel() {
		GridLayout layout = new GridLayout(4, 1);
		setLayout(layout);
		
		add(new JLabel("OPTIONS"));

		JPanel gridSizePanel = new JPanel();

		// ship amount
		GridLayout gridSizelayout = new GridLayout(2, MAX_SHIP_SIZE);
		gridSizePanel.setLayout(gridSizelayout);
		
		int amountOfShipVariations = MAX_SHIP_SIZE - MIN_SHIP_SIZE;
		shipAmountFields = new JTextField[amountOfShipVariations];
		
		for (int i = 0; i < amountOfShipVariations; i++) {
			gridSizePanel.add(new JLabel("" + (i+ MIN_SHIP_SIZE)));
		}
		for (int i = 0; i < amountOfShipVariations; i++) {
			shipAmountFields[i] = new JTextField();
			gridSizePanel.add(shipAmountFields[i]);
		}

		add(gridSizePanel);
	}

	private void addGridsizeOption() {

	}
}
