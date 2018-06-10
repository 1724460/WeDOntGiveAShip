package com.weDontGiveAShip.UI.panels;

import javax.swing.JFrame;

public class TestPanelWindow extends JFrame{
	
	public TestPanelWindow() {
		super("TEST");
		
		
	}
	
	public static void main(String[] args) {
		
		TestPanelWindow tpw = new TestPanelWindow();
		tpw.setSize(500,500);
		tpw.setLocationRelativeTo(null);
		
		//PANEL
		tpw.add(new OptionPanel());
		
		//
		
		tpw.setVisible(true);
	}
}
