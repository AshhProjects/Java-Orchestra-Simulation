package lab5part1;

public class Main {
	
	/**
	Main subroutine, prints out individual words in wgOne and wgTwo.
	*/

	public static void main(String[] args) {

		WordGroup wgOne;
		wgOne = new WordGroup("You can discover more about a person in an hour of play than in a year of conversation");
		WordGroup wgTwo;
		wgTwo = new WordGroup("When you play play hard when you work dont play at all");

		String[] stringArrayOne;
		String[] stringArrayTwo;

		stringArrayOne = wgOne.getWordArray();
		stringArrayTwo = wgTwo.getWordArray();

		for (String myStringOne : stringArrayOne) {
			System.out.println(myStringOne);
		}

		System.out.println("");
		for (String myStringTwo : stringArrayTwo) {
			System.out.println(myStringTwo);
		}

	}
}
