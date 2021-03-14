package ship.commander.sose21;

import ship.commander.sose21.enums.DIRECTION;
import ship.commander.sose21.exceptions.CoordinatesOutOfBoundException;
import ship.commander.sose21.ships.Battleship;
import ship.commander.sose21.ships.Carrier;
import ship.commander.sose21.ships.Destroyer;

import java.util.Scanner;

public class test {
	
	
	public static void main(String[] args) throws CoordinatesOutOfBoundException {
		

		Gameboard gameboard = new Gameboard();
		gameboard.placeShip(0,0, DIRECTION.VERTICALLY,DIRECTION.RIGHT,new Carrier());

		System.out.println(gameboard.toString());


		System.out.println(gameboard.checkIfOtherShipsAlreadyBlockPlace(4,1,DIRECTION.VERTICALLY,DIRECTION.RIGHT,new Battleship()));

	}



	
	
	
}