public class ATM {
	
	Integer bankbalance;
	
	/**
	* First class 'main' that gets called.
	* Initalizes a new myATM object as ATM.
	* Runs the go subroutne in myATM.
	*/
	public static void main(String[] args) {
		
		ATM myATM = new ATM();
		myATM.go();
		
	}
	
	/**
	* Subroutine go.
	* which asks user for bank balance and prints it out to you.
	* it stores the user's input in the variable bankbalance of the ATM class.
	***/
	public void go() {
		
		Toolbox myToolbox = new Toolbox();
		
		System.out.println("Welcome to online ATM banking");
		
		Boolean valid = false;
		while (!valid) {
			System.out.println("How much do you want in your account?");
			bankbalance = myToolbox.readIntegerFromCmd();			
			
			if (bankbalance < 0) {
				System.out.println("Balance can't be below 0!");
			}	else {
				System.out.println("Your balance is " + bankbalance);
				valid = true;
			}
		}
		
		
		while (true) {
			System.out.println("What do you want to do?");
			System.out.println("1 : Withdraw");
			System.out.println("2 : Deposit");
			System.out.println("3 : Inquire");
			System.out.println("4 : Quit");
			Integer choice;
			choice = myToolbox.readIntegerFromCmd();
			if (choice == 1) {
				withdraw();
			}	else if (choice == 2) {
				deposit();
			}	else if (choice == 3) {
				inquire();
			}	else if (choice == 4) {
				quit();
			}
		}

	}
	
	/**
	* Withdraw subroutine, takes away user input from bank balance variable.
	***/
	public void withdraw() {
		
		System.out.println("*****************************************");
		System.out.println("              Withdrawal                 ");
		System.out.println("*****************************************");
		
		Boolean valid = false;
		
		while (!valid) {
			System.out.println("How much would you like to withdraw?");
			Integer withdrawal;
			Toolbox myToolbox = new Toolbox();
			withdrawal = myToolbox.readIntegerFromCmd();
			if (withdrawal > bankbalance) {
				System.out.println("Invalid withdrawal amount! too much");
			}	else if (withdrawal >= 0) {
				bankbalance = bankbalance - withdrawal;
				if (bankbalance < 0) {
					bankbalance = 0;
				}
				System.out.println("*****************************************");
				System.out.println("         Your new balance is " + bankbalance);
				System.out.println("*****************************************");
				valid = true;
			}	else {
				System.out.println("Invalid amount to withdraw!!");
			}
		}
		
	}
	
	/**
	* Deposit subroutine, gives user input amount to the bank balance variable.
	***/
	public void deposit() {
		System.out.println("*****************************************");
		System.out.println("              Deposit                 ");
		System.out.println("*****************************************");
		
		Boolean valid = false;
	
		while (!valid) {
				
			System.out.println("How much would you like to deposit?");
			Integer depositing;
			Toolbox myToolbox = new Toolbox();
			depositing = myToolbox.readIntegerFromCmd();
			
			if (depositing >= 0) {
				bankbalance = bankbalance + depositing;
				if (bankbalance < 0) {
					bankbalance = 0;
				}
				System.out.println("*****************************************");
				System.out.println("         Your new balance is " + bankbalance);
				System.out.println("*****************************************");
				valid = true;
			}	else {
				System.out.println("invalid amount to deposit!");
			}
			
		}
	}
	
	/**
	* prints out the bankbalance value.
	***/
	public void inquire() {
		
		System.out.println("*****************************************");
		System.out.println("          Your balance is " + bankbalance);
		System.out.println("*****************************************");
	}
	
	/**
	* Quits the program.
	***/
	public void quit() {
		System.out.println("*****************************************");
		System.out.println("         GoodBye!");
		System.out.println("*****************************************");
		System.exit(0);
	}
	
}