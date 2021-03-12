package ship.commander.sose21.ships;

public abstract class Ship {//TODO: InfoToGameBoard


	public ShipPart [] shipParts;
	public boolean isDestroyed;
	
	//Set Coordinates when placing Ships?
	

	
    public Ship(int numberOfParts) {
    	
    	this.shipParts = new ShipPart [numberOfParts];
    	
    	for(int i = 0;i < numberOfParts;i++) {
    		
    		this.shipParts[i] = new ShipPart();
    		
    	}
    	
    	this.isDestroyed = false;
    	
    }
    
    public boolean shipCompletelyDestroyed() {
    	
    	for(ShipPart part : this.shipParts) {
    		
    		if(part.isDestroyed() == true) {
    			
    		}else {
    			
    			return false;
    
    		}
    		
    	}
    	
    	return true;
    	
    }
    
    
	public ShipPart [] getShipParts() {
		return shipParts;
	}





	public void setShipParts(ShipPart [] shipParts) {
		this.shipParts = shipParts;
		
	}





	public boolean getDestroyed() {
		return isDestroyed;
	}





	public void setDestroyed(boolean isDestroyed) {
		this.isDestroyed = isDestroyed;
	}
	
	
	

public class ShipPart{ //done
	
	private boolean destroyed;
	
	public ShipPart() {
		this.destroyed = false;
	}

	public boolean isDestroyed() {
		return destroyed;
	}

	public void setDestroyed(boolean destroyed) {
		this.destroyed = destroyed;
	}
	
	
	
}






}