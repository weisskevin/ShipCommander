package ship.commander.sose21;

import ship.commander.sose21.enums.DIRECTION;
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
	
	public boolean placeShip(int y, int x,DIRECTION directionVH, DIRECTION directionLR, Ship ship){

		if(this.checkIfPlacingShipGoesOutOfBounds(y,x,directionVH,directionLR,ship) || this.checkIfOtherShipsAlreadyBlockPlace(y,x,directionVH,directionLR,ship) ){

			return false;

		}


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
			
		}else if(directionVH == DIRECTION.VERTICALLY) {
			
			int index = 0;
			for(int k = y; shipLength > 0; k += rl ) {  // x = 5   -1     3 				
					
					this.getBoard()[k][x].setPartOnField(ship.getShipParts()[index++]);
				    					
					shipLength--;
						
							
			}
			
		
		
		}
		
		this.shipsOnBoard[this.counterOfShipsOnBoard++] = ship;

		return true;
	}
	
	public boolean checkIfNewShipIsDestroyed() {
		int counter = 0;
		
		for(int i = 0; i < 5;i++) {
			
			if(this.shipsOnBoard[i] != null && this.shipsOnBoard[i].shipCompletelyDestroyed()) {
				
				this.shipsOnBoard[i].setDestroyed(true);
				counter++;
			}
			
		}
		
		if(this.counterOfDestroyedShips < counter) {
			
			this.counterOfDestroyedShips = counter;
			


			return true;
		}

		return false;
	}

	public boolean allShipsDestroyed(){

		for(Ship ship : shipsOnBoard){

			if(!ship.isDestroyed){


				return false;
			}


		}

		return true;
	}
	
	public boolean checkIfOtherShipsAlreadyBlockPlace(int y, int x,DIRECTION dirVH,DIRECTION dirLR,Ship ship) {
		
		int lr = dirLR == DIRECTION.RIGHT ? 1 : -1;
		
		if(dirVH == DIRECTION.HORIZONTALLY) {  //-

			for(int i = 0; i < ship.getShipParts().length;i++) {
				
				if(this.checkIfCoordinatesOnBoard(y, x) && this.checkIfFieldsAroundFieldAreBlocked(y, x)) {
									
					return true;
				}
				x += lr;
			}
			
		}else if(dirVH == DIRECTION.VERTICALLY) {

			for(int i = 0; i < ship.getShipParts().length;i ++) {

					if(this.checkIfCoordinatesOnBoard(y, x) && this.checkIfFieldsAroundFieldAreBlocked(y, x)) {
							
					return true;
				}

					y+=lr;
					
			}
			 			
		}


					    
		return false;  						 
	}				                             
	
	public boolean checkIfFieldsAroundFieldAreBlocked(int y,int x) {
		
		y -= 1;    //    00 01
		x -= 1;   //     10 11
				  //     20 21
		for(int i = 0; i < 3;i++) { // 0
						
			for(int k = 0;k < 3;k++) { // 0

				if(this.checkIfCoordinatesOnBoard(y,x) && this.board[y][x].isShipOn() ) {
									
					return true;

					
				}

			x++;

		   }
			x -=3;
			y++;
		}
				
		return false;
				
	}
	
	public boolean checkIfCoordinatesOnBoard(int y,int x) {

		return y <= 9 && y >= 0 && x >= 0 && x <= 9;


	}
	
	public boolean checkIfPlacingShipGoesOutOfBounds(int y, int x,DIRECTION dirVH,DIRECTION dirLR,Ship ship) {//TODO
		
		int shipLength = ship.getShipParts().length;
		
		if(dirVH == DIRECTION.HORIZONTALLY) {
			
			if(dirLR == DIRECTION.LEFT) {

				return x < shipLength;
					
			}else {

				return 10 - x < shipLength;

			}
		}else {
			
			if(dirLR == DIRECTION.LEFT) {

				return y < shipLength;
					
			}else {

				return 10 - y < shipLength;
			}
			
		}
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
					
					if(waterField.getBlankWaterHit()) {
						
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



}
