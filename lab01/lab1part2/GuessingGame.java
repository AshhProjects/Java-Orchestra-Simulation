public class GuessingGame {
	
	
	/**
	*Picks a random number up to 10, user has to try and guess it.
	*if user gets it lower, prints out "too low".
	*if user gets it higher, prints out "too high".
	*if user gets it right, prints out "right".
	*/
	public static void main(String[] args) {
		
		Toolbox myToolbox = new Toolbox();
		
		
		System.out.println("Welcome to Guessing Game");
		
		Integer numberToGuess = myToolbox.getRandomInteger(10);
		Integer guessedNumber;
		guessedNumber = myToolbox.readIntegerFromCmd();
		
		if (numberToGuess < guessedNumber) {
			System.out.println("too high");
		}	else if (numberToGuess > guessedNumber) {
			System.out.println("too low");
		}	else {
			System.out.println("right");
		}
	}	
	
}