package ship.commander.sose21.exceptions;

public class CoordinatesOutOfBoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	
	
	public CoordinatesOutOfBoundException() {
				
		super("Die Koordinaten befinden sich au�erhalb des Spielfeldes \nbitte Koordinaten nochmal eingeben");		
		
	}
	
	

}
