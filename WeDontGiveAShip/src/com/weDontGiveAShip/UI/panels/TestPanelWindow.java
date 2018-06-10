package com.weDontGiveAShip.UI.panels;

import javax.swing.JFrame;

public class TestPanelWindow extends JFrame{
	
	public TestPanelWindow() {
		super("TEST");
		
		
	}
	
	public static void main(String[] args) {
		
		TestPanelWindow tpw = new TestPanelWindow();
		tpw.setSize(700	,700);
		tpw.setLocationRelativeTo(null);
		
	
		//PANEL
		tpw.add(new FieldPanel(10));
		
		//
		
		tpw.setVisible(true);
	}
}
