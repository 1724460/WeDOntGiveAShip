package com.weDontGiveAShip.main;

import com.weDontGiveAShip.UI.Gui;
import com.weDontGiveAShip.interfaces.GameSettings;
import com.weDontGiveAShip.interfaces.GameSettings.ShipBorderConditions;
import com.weDontGiveAShip.interfaces.GameSettingsBuilder;

public class Main {

	public final static int[] DEFAULT_SHIP_AMOUNT = { 0, 0, 5, 3, 2, 1 };
	public final static ShipBorderConditions DEFAULT_SHIP_BORDER_CONDITIONS = ShipBorderConditions.TOUCHING_ALLOWED;

	public static Gui gui;
	private static GameSettings settings;

	public static void main(String[] args) {
		new Main();
	}

	public Main() {
		// Default Settings

		GameSettingsBuilder builder = new GameSettingsBuilder();
		for (int length = 1; length < DEFAULT_SHIP_AMOUNT.length; length++) {
			if (DEFAULT_SHIP_AMOUNT[length] > 0)
				builder.setNumOfShips(length, DEFAULT_SHIP_AMOUNT[length]);
		}
		builder.setShipBorderConditions(DEFAULT_SHIP_BORDER_CONDITIONS);
		settings = builder.createGameSettings();

		// User Interface
		gui = new Gui();
	}

	public static GameSettings getSettings() {
		return settings;
	}

}
