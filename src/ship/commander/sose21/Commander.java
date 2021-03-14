package ship.commander.sose21;
import ship.commander.sose21.ships.Battleship;
import ship.commander.sose21.ships.Carrier;
import ship.commander.sose21.ships.Cruiser;
import ship.commander.sose21.ships.Destroyer;
import ship.commander.sose21.ships.Ship;


public class Commander {
	
	Ship carrier = new Carrier();
	Ship battleship = new Battleship();
	Ship cruiser = new Cruiser();
	Ship submarine = new Cruiser();
	Ship destroyer = new Destroyer();
	
	Gameboard gameBoard = new Gameboard();
	
	boolean hasLost = false;
	String name;
	
	
	public static boolean shoot(int y, int x, Gameboard gb) {  
		
		if(!gb.checkIfCoordinatesOnBoard(y,x) || gb.getBoard()[y][x].getPartOnField() != null && gb.getBoard()[y][x].getPartOnField().isDestroyed()) {
			
			return false;
			
		}else {
						
			if(gb.getBoard()[y][x].isShipOn()) {
							
				gb.getBoard()[y][x].getPartOnField().setDestroyed(true);
				//gb.checkIfNewShipIsDestroyed();
				
				return true;
				
			}else {
				
				gb.getBoard()[y][x].setBlankWaterHit(true);
				
			}
			
		}
				
		return false;
			
	}
	
	public void hasCommanderLost() {
		
		if(this.gameBoard.allShipsDestroyed()) {
			
			this.hasLost = true;
			
		}
		
	}

}
