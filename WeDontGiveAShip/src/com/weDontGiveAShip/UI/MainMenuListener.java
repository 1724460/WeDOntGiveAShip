package com.weDontGiveAShip.UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class MainMenuListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() instanceof JButton){
			JButton pressed = (JButton) e.getSource();
			
			if(pressed.getText().equals("Spielen")){
				System.out.println("Jetzt Shipplacer öffnen");
				
			}else if(pressed.getText().equals("Beenden")){
				System.exit(0);
			}
			
		}
	}

}
