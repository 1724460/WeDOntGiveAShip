package com.weDontGiveAShip.UI;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class MainMenuPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	public static JButton playButton;
	public static JButton exitButton;
	private static JLayeredPane backgroundPanel;
	
	public MainMenuPanel() {
		setLayout(null);

		playButton = new JButton("Spielen");
		playButton.setForeground(Color.WHITE);
		playButton.setFont(new Font("Calibri", Font.BOLD, 28));
//		playButton.setBounds(200, 100, 200, 50);
		playButton.setBounds(200, 200, 200, 50);
		playButton.setFocusPainted(false);
		playButton.setOpaque(false);
		playButton.setContentAreaFilled(false);
		playButton.addActionListener(new ButtonListener());
		
		add(playButton);

		exitButton = new JButton("Beenden");
		exitButton.setForeground(Color.WHITE);
		exitButton.setFont(new Font("Calibri", Font.BOLD, 28));
		exitButton.setBounds(200, 600, 200, 50);
		exitButton.setFocusPainted(false);
		exitButton.setOpaque(false);
		exitButton.setContentAreaFilled(false);
		exitButton.addActionListener(new ButtonListener());
		
		add(exitButton);
		
		//	we dont give a ship logo
		JLabel logo = new JLabel(null, new ImageIcon(getClass().getResource("logo.png")), SwingConstants.CENTER);
		
//		logo.setBounds(50, 200, 500, 250);
		logo.setBounds(50, -15, 500, 250);
		add(logo);
		
		drawBackground();
	}
	
	public void drawBackground(){
		//	background gif
		backgroundPanel = new JLayeredPane();
		
		JLabel backgroundImageLabel = new JLabel(null, new ImageIcon(getClass().getResource("waves_menu_background.gif")), SwingConstants.CENTER);
		backgroundImageLabel.setSize(600, 800);
		
		backgroundPanel.add(backgroundImageLabel);
		backgroundPanel.setBounds(0, 0, 600, 800);
		add(backgroundPanel);
	}

}
