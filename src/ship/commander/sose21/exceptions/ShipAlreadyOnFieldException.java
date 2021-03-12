package ship.commander.sose21.exceptions;

public class ShipAlreadyOnFieldException extends Exception {

	
	private static final long serialVersionUID = 1L;

	
	public ShipAlreadyOnFieldException() {
		
		super("Auf dem Feld oder an einem anliegenden Feld befindet sich bereits ein Schiff \nbitte geben sie die Koordinaten nochmal ein");
		
	}
}
