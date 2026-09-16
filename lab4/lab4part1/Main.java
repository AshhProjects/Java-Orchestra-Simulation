package lab4part1;

public class Main {

	/**
	Main subroutine, simple for loop of multiplication table.
	Up to 20 times the number the user specifies.
	Then calculates how many successive integers to add to make a total >500.
	*/
	public static void main(String[] args) {
		Toolbox myToolbox;
		myToolbox = new Toolbox();

		System.out.println("Pick an integer");
		Integer myInt;
		
		myInt = myToolbox.readIntegerFromCmd();
		
		for (int i = 1; i < 21; i++) {
			System.out.print(i * myInt);
			System.out.print(" ");
			
		}
		
		System.out.println();
		int i = 1;
		int total = 0;
		while (total < 500) {
			System.out.println("iteration " + i);
			total = total + i;
			i++;

		}

	}
}
