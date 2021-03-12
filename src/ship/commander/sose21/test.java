package ship.commander.sose21;

import java.util.Scanner;

public class test {
	
	
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		testMethod(scanner);
	
		
	}



	public static void testMethod(Scanner scanner) {
	
		while(true) {
			
			boolean i = scanner.nextBoolean();
			
			
			if(i) {
				
				System.out.println("AGAIN");
				
			}else {
				
				break;
			}
			
		}
		
		System.out.println("YAY");
		
	}
	
	
	
}