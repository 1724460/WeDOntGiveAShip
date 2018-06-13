package com.weDontGiveAShip.UI.panels;



import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import com.weDontGiveAShip.interfaces.Position;
import com.weDontGiveAShip.interfaces.Ship;
import com.weDontGiveAShip.main.Main;
import com.weDontGiveAShip.main.PlayerCreatorImpl;
import com.weDontGiveAShip.main.PlayerImpl;

public class MatchPanel extends JPanel{
	
	private JPanel fields;
	private FieldPanel field1, field2;
	private JTextArea logArea;
	
	
	
	public MatchPanel() {
		PlayerCreatorImpl pc = new PlayerCreatorImpl();
		
		
		
		field1 = new FieldPanel(10, true);
		
		field2 = new FieldPanel(10, false);
//		for(Ship s : p1.getShips()) {
//			for(Position p : s.getOccupiedSpaces()) {
//				field2.setColor(p.x, p.y, Main.SHIP_COLOR);
//			}
//		}
			
		
		fields = new JPanel();
		
		fields.setLayout(new GridLayout(3,1));
		
		fields.add(field1);
		fields.add(field2);
		
		
		add(fields, BorderLayout.CENTER);
		
		
		
		
	}

}
