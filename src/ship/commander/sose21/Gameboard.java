package ship.commander.sose21;

import ship.commander.sose21.enums.DIRECTION;
import ship.commander.sose21.exceptions.CoordinatesOutOfBoundException;
import ship.commander.sose21.ships.Ship;




public class Gameboard {
	
	private WaterField [][] board = new WaterField [10][10];
	Ship [] shipsOnBoard = new Ship [5];
	int counterOfDestroyedShips = 0;
	int counterOfShipsOnBoard = 0;
	
	
	public Gameboard() {
		
		for(int y = 0; y < 10; y++) {
			
			for(int x = 0; x < 10; x++) {
				
				getBoard()[y][x] = new WaterField();
				
			}
			
		}
		
	}
	
	/* Coordinates have to be from 0 to 9
	 * Left and Up is the same = -1 || Right and Down is the same +1
	 */
	
	public void placeShip(int y, int x,DIRECTION directionVH, DIRECTION directionLR, Ship ship)throws CoordinatesOutOfBoundException{ //TODO  //VH = Vertically Horizontally LR = Left Right
		
//		if(checkIfCoordinatesOnBoard(y,x) || checkIfPlacingShipGoesOutOfBounds(y,x,directionVH,directionLR,ship)) {
//			return;
//		}
		
		
		int rl = 1;
		int shipLength = ship.getShipParts().length;
		
		
		if(directionLR == DIRECTION.LEFT) { 
			
			rl = -1;
			
		}
		
		
		
		if(directionVH == DIRECTION.HORIZONTALLY) {
			int index = 0;
			
			for(int k = x; shipLength > 0; k += rl ) {   
								
					this.getBoard()[y][k].setPartOnField(ship.getShipParts()[index++]);
				    					
					shipLength--;
									
			}
			
		}else {
			
			int index = 0;
			for(int k = y; shipLength > 0; k += rl ) {  // x = 5   -1     3 				
					
					this.getBoard()[k][x].setPartOnField(ship.getShipParts()[index++]);
				    					
					shipLength--;
						
							
			}
			
		
		
		}
		
		this.shipsOnBoard[this.counterOfShipsOnBoard++] = ship;
		
	}
	
	public int checkIfNewShipIsDestroyed() {
		int counter = 0;
		
		for(int i = 0; i < 5;i++) {
			
			if(this.shipsOnBoard[i] != null && this.shipsOnBoard[i].shipCompletelyDestroyed()) {
				
				this.shipsOnBoard[i].setDestroyed(true);
				counter++;
			}
			
		}
		
		if(counterOfDestroyedShips < counter) {
			
			counterOfDestroyedShips = counter;
			
			System.out.println("SHIP HAS BEEN DESTROYED");
			
			
			
		}
		return counter;
	}
	
	public static boolean checkIfOtherShipsAlreadyBlockPlace(int y, int x,DIRECTION dirVH,DIRECTION dirLR,Ship ship, Gameboard gameBoard) {
		
		int lr = dirLR == DIRECTION.RIGHT ? 1 : -1;
		
		if(dirVH == DIRECTION.HORIZONTALLY) {  //-
			
			for(int i = x; i < ship.getShipParts().length;i += lr) {
				
				if(gameBoard.checkIfCoordinatesOnBoard(y, i) && Gameboard.checkIfFieldsAroundFieldAreBlocked(y, i, gameBoard)) {
									
					return true;
				}
		
			}
			
		}else if(dirVH == DIRECTION.VERTICALLY) {
				
			for(int i = y; i < ship.getShipParts().length;i += lr) {
				
					if(gameBoard.checkIfCoordinatesOnBoard(i, x) && Gameboard.checkIfFieldsAroundFieldAreBlocked(y, i, gameBoard)) {
							
					return true;
				}
					
			}
			 			
		}
					    
		return false;  						 
	}				                             
	
	public static boolean checkIfFieldsAroundFieldAreBlocked(int y,int x,Gameboard gameBoard) {
		
		y -= 1;    //    00 01 02
		x -= 1;   //     10 11 12
				  //
		for(int i = 0; i < 3;i++) { // 0
						
			for(int k = 0;k < 3;k++) { // 0
				
				if(gameBoard.checkIfCoordinatesOnBoard(y,x) && gameBoard.getBoard()[y][x].isShipOn() ) {
									
					return true;
					
				}
						
			x++;
			
		   }
			x -=2;
			y++;
		}
				
		return false;
				
	}
	
	public boolean checkIfCoordinatesOnBoard(int y,int x) {
		
		if(y > 9 || y < 0 || x < 0 || x > 9) {
			
			
			return false;
			
		}
		
		return true;
		
		
	}
	
	public boolean checkIfPlacingShipGoesOutOfBounds(int y, int x,DIRECTION dirVH,DIRECTION dirLR,Ship ship) {//TODO
		
		int shipLength = ship.getShipParts().length;
		
		if(dirVH == DIRECTION.HORIZONTALLY) {
			
			if(dirLR == DIRECTION.LEFT) {
				
				if(x < shipLength) {return true;}
					
			}else {
				if(10 - x < shipLength) {return true;}
			}
		}else {
			
			if(dirLR == DIRECTION.LEFT) {
				
				if(y < shipLength) {return true;}
					
			}else {
				if(10 - y < shipLength) {return true;}
			}
			
		}
			
		return false;
	}
	
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		
		for(WaterField [] waterFields : this.getBoard()) {
			
			for(WaterField waterField : waterFields) {
				
				if(waterField.isShipOn()) {
					
					if(waterField.getPartOnField().isDestroyed()) {
						
						sb.append("X ");
						
					}else {
					
					sb.append("O ");
					
					}
					
				}else {
					
					if(waterField.getBlankWaterHit() == true) {
						
						sb.append("_ ");
						
					}else {
					
					sb.append("~ ");
					
					}
				}
				
			}
			sb.append("\n");
			
		}
		
		
		return sb.toString();	
		}

	public WaterField [][] getBoard() {
		return board;
	}

	public void setBoard(WaterField [][] board) {
		this.board = board;
	}

}
