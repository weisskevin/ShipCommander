package ship.commander.sose21;

import java.util.Scanner;

import ship.commander.sose21.enums.DIRECTION;
import ship.commander.sose21.helping.MonitoringThroughConsole;
import ship.commander.sose21.helping.Order;
import ship.commander.sose21.ships.Ship;

public class RunningGameOnConsole {

	static boolean shotHasHit = false;
	static boolean shotHasSunken = false;


	public static void main(String[] args) {
		
		introduction();
		
		Commander PLAYER1 = new Commander();
		Commander PLAYER2 = new Commander();
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("SPIELER 1 geben sie ihren Namen an");
		PLAYER1.name = scanner.nextLine();
		
		System.out.println("SPIELER 2 geben sie ihren Namen an");
		PLAYER2.name = scanner.nextLine();
		
		clear();
		
		PLAYER1 = placeShipsRoutine(PLAYER1,scanner);
		
		clear();
			
		PLAYER2 = placeShipsRoutine(PLAYER2,scanner);
		
		clear();
		
//------------------------------------------------------------------------------------------GAME_LOOP------------------------------------------------------------------		
		
		while(!PLAYER1.hasLost && !PLAYER2.hasLost) {
			
			PLAYER2 = inGameRound(PLAYER1,PLAYER2,scanner);
			
			PLAYER1 = inGameRound(PLAYER2,PLAYER1,scanner);
			
			PLAYER1.hasCommanderLost();
			PLAYER2.hasCommanderLost();
			
		}//.---------------------------------------------------------------------------------------------------------------------WINNER AND LOSER IF-ELSE
		
		clear();
		
		scanner.close();
		
		if(PLAYER1.hasLost) {
			
			System.out.println("SPIELER 2 HAT GEWONNEN");
			
		}else {
			
			System.out.println("SPIELER 1 HAT GEWONNEN");
			
		}
		
	}



 public static void drucken() {
	 
	 System.out.println();
	 System.out.println();
	 
 }
 
 public static void clear() {
	 
	 for(int i = 0;i < 20;i++) {
		 System.out.println();
	 }
	 
 }
 
 public static Order clearOrder(String orderString) {
	 
	 Order order = new Order();
	 	 
	 String [] s = orderString.split(" ");
	 
	 order.setY(mapCoordinates(s[0]));
	 order.setX(mapSndCoordinates(s[1]));
	 order.setVh(mapDirections(s[2]));
	 order.setLR(mapDirections(s[3]));
	 
	 return order;
 }

 public static int mapCoordinates(String a){

	 return switch (a) {
		 case "A", "a" -> 0;
		 case "B", "b" -> 1;
		 case "C", "c" -> 2;
		 case "D", "d" -> 3;
		 case "E", "e" -> 4;
		 case "F", "f" -> 5;
		 case "G", "g" -> 6;
		 case "H", "h" -> 7;
		 case "I", "i" -> 8;
		 case "J", "j" -> 9;
		 default -> 0;
	 };

 }

 public static int mapSndCoordinates(String a){

	 return (Integer.parseInt(a))-1;

 }

 public static DIRECTION mapDirections(String a){

		return switch (a){
			case "VERTICALLY","V","v" -> DIRECTION.VERTICALLY;
			case "HORIZONTALLY","H","h" -> DIRECTION.HORIZONTALLY;
			case "RIGHT","R","r" -> DIRECTION.RIGHT;
			case "LEFT","L","l" -> DIRECTION.LEFT;
			default -> DIRECTION.UNKOWN;
		};


 }
 
 public static Commander placeShipsRoutine(Commander player,Scanner scan) {
	 	System.out.println("-------------------------------------");
		System.out.println(player.name+" AN DER REIHE MIT PLATZIEREN");
		System.out.println("-------------------------------------");
	 	 

			player = actuallyPlaceShip(player, scan, "Carrier", player.carrier);
			player = actuallyPlaceShip(player, scan, "Battleship", player.battleship);
			player = actuallyPlaceShip(player, scan, "Cruiser", player.cruiser);
			player = actuallyPlaceShip(player, scan, "Submarine", player.submarine);
			player = actuallyPlaceShip(player, scan, "Destroyer", player.destroyer);


     return player;
	 
 }
 
 public static Commander actuallyPlaceShip(Commander player,Scanner scanner,String name,Ship ship) {

	 		 System.out.println(name);

	 		 String order = scanner.nextLine();

	 		 Order ord = clearOrder(order);
				
	 		 try {
                 while (!player.gameBoard.placeShip(ord.getY(), ord.getX(), ord.getVh(), ord.getLR(), ship)) {

                     System.out.println(name);

                     order = scanner.nextLine();

                     ord = clearOrder(order);

                 }
             }catch(Exception e){
				    e.printStackTrace();
				    clear();
	 		        actuallyPlaceShip(player, scanner, name, ship);

                 }
				return player; 
	 
		}
 
 public static void introduction() {
	 
	 System.out.println("__---Willkommen zu SHIP COMMANDER---__");
		
		drucken();
		
		System.out.println("Bitte platzieren sie ihre jetzt ihre Schiffe unter folgendem Format:\ny-Koordinate *space* x-Koordinate *space* HORIZONTALLY oder VERTICALLY *space* LEFT oder RIGHT");
		System.out.println("Beim Schießen die Koordinaten im folgenden Format angeben: \nY-Koordinate *space* X-Koordinate");
		drucken();
		
		
	 
 }
 
 public static Commander inGameRound(Commander own, Commander enemy,Scanner scanner) {
	 
	 System.out.println(own.name+" AM ZUG \ngo eingeben um zu starten");	
		String enter = "";
		
		while(!enter.equals("go")) {
			
			enter = scanner.nextLine();
			
		}
		
		enemy = shootLoop(own,enemy,scanner);
			
		
		
		System.out.println(MonitoringThroughConsole.monitorGame(own.gameBoard, enemy.gameBoard));
		
		System.out.println("ok eingeben um Zug zu beenden");
		while(!enter.equals("ok")) {
			
			enter = scanner.nextLine();
			
			
			
		}
		
		clear();
		return enemy;
	 
 }
 
 public static Commander shootLoop(Commander own,Commander enemy,Scanner scanner) {
	 
	 	System.out.println(MonitoringThroughConsole.monitorGame(own.gameBoard, enemy.gameBoard));
	 	if(shotHasHit){
			System.out.println(" TREFFER! \n");
			shotHasHit = false;
		}
	 	if(shotHasSunken){

	 		System.out.println(" VERSENKT! \n");
	 		shotHasSunken = false;

		}
		System.out.println("Koordinaten fuer Schuss angeben");



		 String [] s = scanner.nextLine().split(" ");

		 try {

             int yP1 = mapCoordinates(s[0]);
             int xP1 = mapSndCoordinates(s[1]);

             if (Commander.shoot(yP1, xP1, enemy.gameBoard)) {
                 shotHasHit = true;

                 if (enemy.gameBoard.checkIfNewShipIsDestroyed()) {



                     shotHasSunken = true;

                 }

                 enemy = shootLoop(own, enemy, scanner);
             }



         }catch(Exception e){

            clear();
             System.out.println("KOORDINATEN UNGÜLTIG");
		     shootLoop(own,enemy,scanner);

         }
	 
	 return enemy;
 }
 
}