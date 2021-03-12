package ship.commander.sose21;

import ship.commander.sose21.ships.Ship.ShipPart;

public class WaterField {
	
	private ShipPart partOnField;
	private boolean blankWaterHit;
	
	public boolean isShipOn(){
		
		return this.getPartOnField() != null;
		
	}

	public boolean getBlankWaterHit() {
		return blankWaterHit;
	}

	public void setBlankWaterHit(boolean blankWaterHit) {
		this.blankWaterHit = blankWaterHit;
	}

	public ShipPart getPartOnField() {
		return partOnField;
	}

	public void setPartOnField(ShipPart partOnField) {
		this.partOnField = partOnField;
	}

}
