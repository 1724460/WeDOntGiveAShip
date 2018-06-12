package com.weDontGiveAShip.UI;

import javax.swing.JFrame;

import com.weDontGiveAShip.main.Main;

public class Gui extends JFrame{

	private static final long serialVersionUID = 1L;
	
	public Gui() {
		super("Battleship by We Don't Give A Ship");
		setSize(600, 800);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		drawMainMenu();
		
		setVisible(true);
	}

	private void drawMainMenu() {
		MainMenuPanel mainMenu = new MainMenuPanel();
		add(mainMenu);
	}

	public void openShipPlacer() {
		clear();
		
		
	}

	public static void clear(){
		Main.gui.getContentPane().removeAll();
		Main.gui.repaint();
	}
	
}
