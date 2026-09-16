package lab5part2;


import java.util.HashSet;

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

	/**
	returns a hashset.
	*/ 
	public HashSet<String> getWordSet(WordGroup myWg) {
		HashSet<String> myHs = new HashSet();
		for (String eachWord : this.getWordArray()) {
			myHs.add(eachWord);
		}

		for (String eachWord : myWg.getWordArray()) {
			myHs.add(eachWord);
		}

		return myHs;
	}
}
