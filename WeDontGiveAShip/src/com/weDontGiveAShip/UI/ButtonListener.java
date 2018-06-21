package com.weDontGiveAShip.UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.weDontGiveAShip.main.Main;

public class ButtonListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == MainMenuPanel.playButton){

			Main.gui.openShipPlacer();
			
		}else if(e.getSource() == MainMenuPanel.exitButton){
			System.exit(0);
		}
	}

}
