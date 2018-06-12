package com.weDontGiveAShip.interfaces;

/**
 * Allows you to place down {@link Ship Ships} with {@link ShipPlacer#setShips(Ship[])}
 */
public interface ShipPlacer {
	
	/**
	 * Sets your Ships and throws an {@link InvalidShipPlacementException} if the placement was invalid.
	 *
	 * @param ships your {@link Ship Ships}
	 * @throws InvalidShipPlacementException if your {@link Ship} placement was invalid, eg. {@link OverlappingShipsException}, {@link TouchingShipsException} or other
	 */
	void setShips(Ship[] ships) throws InvalidShipPlacementException;
}
