package adamProtocol;

import java.util.Scanner;
import adamProtocol.exceptions.IndivisibleDoseException;

public class DisplayMessage {
	
	static Scanner s = new Scanner(System.in);
	public static boolean suppressed = false;
	
	public static void displayMessage(String message){
		if(!suppressed)
			System.out.println(message);
	}

	public static Dose confirmDose(String message) throws IndivisibleDoseException {
		displayMessage(message);
		displayMessage("Enter dose you want to prescribe as \"6MP MTX\": ");
		
		double smp = s.nextDouble();
		double mtx = s.nextDouble();
		
		return new Dose(mtx,smp);
	}
}
