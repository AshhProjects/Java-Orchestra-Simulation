package lab5part1;


public class WordGroup {

	String words = new String();

	public WordGroup(String myString) {
		words = myString.toLowerCase();

	}

	/**
	Splits word string into separate words to store into an array.
	*/ 
	public String[] getWordArray() {

		String[] stringArray;
		stringArray = words.split(" ");
		return stringArray;

	}
}
