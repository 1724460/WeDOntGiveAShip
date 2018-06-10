package com.weDontGiveAShip.UI.panels;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class FieldPanel extends JPanel {
	
//ActionListener
	class Listener implements ActionListener{
		private FieldPanel fieldPanel;
		int x, y;
		private Listener(FieldPanel fp, int x , int y) {
			fieldPanel = fp;
			this.x = x;
			this.y = y;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			fieldPanel.OnClick(x,y);
		}
		
	}
	
	
	private JButton[][] buttons;
	public FieldPanel(int gridSize) {
		super();
		setLayout(new GridLayout(gridSize, gridSize));
		
		buttons = new JButton[gridSize][gridSize];
		
		
		for(int x = 0; x < gridSize; x++) {
			for(int y = 0 ; y < gridSize; y++ ) {
				char letter = (char) ('A' +x);
				JButton currentButton = new JButton("" +letter + (y+1));
				buttons[x][y] = currentButton;
				
				currentButton.addActionListener(new Listener(this, x, y));
				
				add(currentButton);
				
			}
		}
	}
	
	private void OnClick(int x, int y) {
		System.out.println("CLicked(" +x + ", " + y + ")");
	}
	
	
}
