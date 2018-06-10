package com.weDontGiveAShip.UI.panels;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class OptionPanel extends JPanel{
	
	public OptionPanel() {
		GridLayout layout = new GridLayout(4,1);
		setLayout(layout);
		add(new JLabel("OPTIONS"));
		
		JPanel gridSizePanel = new JPanel();
		GridLayout gridSizelayout = new GridLayout(1,2);
		gridSizePanel.setLayout(gridSizelayout);
		gridSizePanel.add(new JLabel("Gridsize:"));
		gridSizePanel.add(new JTextField());
	}
	
	private void addGridsizeOption() {
		
	}
}
