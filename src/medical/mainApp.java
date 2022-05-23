package medical;


import java.util.Scanner;

import org.apache.log4j.Logger;

import functions.Dbfunctions;
import interfaces.Operations;


class Menu {
	
	static Logger log = Logger.getLogger(Menu.class.getName());
	
	Operations op = new Dbfunctions();
	
	
	
	public void dispMenu(String user) throws Exception {
		log.info("Inside display menu()");
		
		Scanner scan = new Scanner(System.in);
			
		int userC = 1; // used as flag to exit from while loop, initial value is set to 1
		
		while(true) {
				
			if(userC == 1) {
				System.out.println("Welcome to the Pharmacy");
				System.out.println("Enter your Choice");
						
				System.out.println("1. List the Medicines");
				System.out.println("2. Buy Medicine");
				System.out.println("3. Search Medicine");
				System.out.println("4. Exit");
							
				int userChoice = scan.nextInt();
								
				switch(userChoice) {
							
					case 1:
						log.debug("getting user input - "+ user +" slected option 1");
						op.listMed();
						break;
						
					case 2:
						log.debug("getting user input - "+ user +" slected option 2");
						//System.out.println("Enter the Medicine you are looking for");
						
						//String medName = scan.next();
						
						//op.searchMed(medName);
						op.buyMed();
						break;
								
					case 3:
						log.debug("getting user input - "+ user +" slected option 3");
						System.out.println("Enter the Medicine you are looking for");
						
						String medName = scan.next();
						
						op.searchMed(medName);
						break;
									
					case 4:
						log.debug("getting user input - "+ user +" slected option 4");
						log.info("exit success - " + user);
									
						System.out.println("Thank you");
						System.exit(0);
						break;
								
					default:
						System.out.println("wrong input");
				} // end switch
							
				//System.out.println("\nContinue(1 --> Yes/0 --> No) \n");
				//userC = scan.nextInt(); // flag value changes depending on the user input
						
			} // end if
					
			else {
				System.out.println("Thank you");
				System.exit(0);
			} // end else
		} // end while
	} // end dispMenu()
} // end AppMenu


public class mainApp {
	
	static Logger log = Logger.getLogger(mainApp.class.getName());
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		Menu m = new Menu();
		m.dispMenu("");
	} // end main()

} // end AppMenu



