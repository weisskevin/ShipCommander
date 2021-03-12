package ship.commander.sose21.helping;

import ship.commander.sose21.Gameboard;
import ship.commander.sose21.WaterField;

public class MonitoringThroughConsole {
	
	
	public static String monitorGame(Gameboard own,Gameboard enemy) {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("DEIN SPIELFELD                 GEGNER SPIELFELD \n__________________________________________________\n");
		sb.append(" 1 2 3 4 5 6 7 8 9 10           1 2 3 4 5 6 7 8 9 10\n");
		char c = 'A';
		for(int i = 0;i < 10;i++) {
			
			WaterField[] ownWF = own.getBoard()[i];
			WaterField[] enemyWF = enemy.getBoard()[i];		
			sb.append(c);
			sb.append(" ");
			
			for(int k = 0;k < 10 ; k++) {
							
				if(ownWF[k].isShipOn()) {
					
					if(ownWF[k].getPartOnField().isDestroyed()) {
						
						sb.append("X ");
						
					}else {
					
					sb.append("O ");
					
					}
					
				}else {
					
					if(ownWF[k].getBlankWaterHit() == true) {
						
						sb.append("_ ");
						
					}else {
					
					sb.append("~ ");
					
					}
				}
				
			}
			
			sb.append("         ");
			sb.append(c);
			sb.append(" ");
			c += 1;
			for(int k = 0;k < 10 ; k++) {
				
				if(enemyWF[k].isShipOn()) {
					
					if(enemyWF[k].getPartOnField().isDestroyed()) {
						
						sb.append("X ");
						
					}else {
					
					sb.append("~ ");
					
					}
					
				}else {
					
					if(enemyWF[k].getBlankWaterHit() == true) {
						
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
		
	}


