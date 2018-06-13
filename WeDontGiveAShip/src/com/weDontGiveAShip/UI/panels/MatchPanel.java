package com.weDontGiveAShip.UI.panels;



import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import com.weDontGiveAShip.interfaces.Player;

public class MatchPanel extends JPanel{
	
	private JPanel fields;
	private FieldPanel field1, field2;
	private JTextArea logArea;
	
	
	public MatchPanel(Player p1, Player p2) {
		
		field1 = new FieldPanel(10, true);
		field2 = new FieldPanel(10, false);
		fields = new JPanel();
		
		fields.setLayout(new GridLayout(3,1));
		
		fields.add(field2);
		fields.add(field1);
		
		
		add(fields, BorderLayout.CENTER);
		
		
		
		
	}

}
