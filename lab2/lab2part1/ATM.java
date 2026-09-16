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
		System.out.println("How much do you want in your account?");
		bankbalance = myToolbox.readIntegerFromCmd();
		System.out.println(bankbalance);
		
	}
	
}