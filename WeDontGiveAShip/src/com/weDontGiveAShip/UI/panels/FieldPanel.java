package com.weDontGiveAShip.UI.panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class FieldPanel extends JPanel {

	// ActionListener
	class Listener implements ActionListener {
		private FieldPanel fieldPanel;
		int x, y;

		private Listener(FieldPanel fp, int x, int y) {
			fieldPanel = fp;
			this.x = x;
			this.y = y;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			fieldPanel.onClick(x, y);
		}

	}

	private JButton[][] buttons;

	public FieldPanel(int gridSize, boolean enabled) {
		super();
		setLayout(new GridLayout(gridSize + 1, gridSize + 1));

		buttons = new JButton[gridSize][gridSize];
		// NumberRow
		add(new JLabel(""));
		for (int x = 0; x < gridSize; x++) {
			char letter = (char) ('A' + x);
			JLabel letterLabel = new JLabel("" + letter);
			letterLabel.setFont(new Font("Serif", Font.PLAIN, 28));
			
			letterLabel.setHorizontalAlignment(SwingConstants.CENTER);;
			add(letterLabel);
		}

		for (int x = 0; x < gridSize; x++) {
			JLabel numberLabel = new JLabel("" +  (x+1));
			numberLabel.setFont(new Font("Serif", Font.PLAIN, 28));
			
			numberLabel.setHorizontalAlignment(SwingConstants.CENTER);;
			add(numberLabel);
			for (int y = 0; y < gridSize; y++) {

				JButton currentButton = new JButton("");
				
				currentButton.setEnabled(enabled);
				buttons[x][y] = currentButton;

				currentButton.addActionListener(new Listener(this, x, y));
				currentButton.setBackground(Color.WHITE);
				add(currentButton);

			}
		}
	}

	public void onClick(int x, int y) {
		System.out.println("Clicked(" + x + ", " + y + ")");
		buttons[x][y].setBackground(Color.GRAY);
	}
	
	public void setColor(int x, int y, Color color) {
		buttons[x][y].setBackground(color);
	}
	
	public void Update() {
		
	}

}
