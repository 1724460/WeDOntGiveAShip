<<<<<<< HEAD
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
=======
package com.weDontGiveAShip.UI;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.weDontGiveAShip.UI.panels.FieldPanel;
import com.weDontGiveAShip.UI.panels.ShipPlacerPanel;
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
		ShipPlacerPanel shipPlacer = new ShipPlacerPanel();
		add(shipPlacer);
		SwingUtilities.updateComponentTreeUI(Main.gui);
	}

	public static void clear(){
		Main.gui.getContentPane().removeAll();
		Main.gui.repaint();
	}
	
}
>>>>>>> refs/heads/ShipPlacer
