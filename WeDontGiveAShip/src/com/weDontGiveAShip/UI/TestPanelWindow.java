package com.weDontGiveAShip.UI;

import javax.swing.JFrame;

public class TestPanelWindow extends JFrame{
	
	public TestPanelWindow() {
		super("TEST");
	}
	
	public static void main(String[] args) {
		JFrame mainMenuTest = new JFrame("Main menu test");
		
		//	Größe soll eig so bleiben
		mainMenuTest.setSize(600, 800);
		mainMenuTest.setResizable(false);
		mainMenuTest.add(new MainMenuPanel());
		mainMenuTest.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainMenuTest.setVisible(true);
		
		
		TestPanelWindow tpw = new TestPanelWindow();
		tpw.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		tpw.setSize(700, 700);
		tpw.setLocationRelativeTo(null);
		
	
		//	PANEL
		tpw.add(new FieldPanel(10));
		
		tpw.setVisible(true);
	}
}
