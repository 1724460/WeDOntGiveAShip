
package com.weDontGiveAShip.UI;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.weDontGiveAShip.UI.panels.MatchPanel;
import com.weDontGiveAShip.UI.panels.ShipPlacerPanel;
import com.weDontGiveAShip.main.Main;

import se1.schiffeVersenken.interfaces.Ship;

public class Gui extends JFrame{

	private static final long serialVersionUID = 1L;

	private boolean inGame;

	public MatchPanel matchPanel;
	
	public Gui() {		
		
		super("Battleship by We Don't Give A Ship");
		setSize(600, 800);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		drawMainMenu();
		
		setVisible(true);
		inGame = false;
	}

	private void drawMainMenu() {
		MainMenuPanel mainMenu = new MainMenuPanel();
		add(mainMenu);
	}

	public static ShipPlacerPanel shipPlacer;
	
	public void openShipPlacer() {
		clear();
		shipPlacer = new ShipPlacerPanel();
		add(shipPlacer);
		update();
	}
	
	public void openMatchPanel(Ship[] ships) {
		clear();
		matchPanel = new MatchPanel(ships);
		setSize(600, 1000);
		add(matchPanel);
		SwingUtilities.updateComponentTreeUI(Main.gui);
		
		//	KI platziert ihre Schiffe
		Main.gui.matchPanel.p2.placeShips(Main.shipPlacer);
		
		inGame = true;
	}

	public static void clear(){
		Main.gui.getContentPane().removeAll();
		Main.gui.repaint();
	}
	
	public boolean isInGame(){
		return inGame;
	}

	public static void update() {
		SwingUtilities.updateComponentTreeUI(Main.gui);
		Main.gui.repaint();
	}
}

