package lab5part3;

import java.util.HashMap;
import java.util.HashSet;

public class Main {
	
	/**
	Main subroutine, prints out individual words in wgOne and wgTwo.
	Creates HashSet.
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

		HashSet<String> myHashSet = new HashSet();

		myHashSet = wgOne.getWordSet(wgTwo);


		HashMap<String, Integer> wgOneHm;
		HashMap<String, Integer> wgTwoHm;
		wgOneHm = wgOne.getWordCounts();
		wgTwoHm = wgTwo.getWordCounts();

		for (String myKey : wgOneHm.keySet()) {
			System.out.println(myKey + ": " + wgOneHm.get(myKey));
		}
		System.out.println("");

		for (String myKey : wgTwoHm.keySet()) {
			System.out.println(myKey + ": " + wgTwoHm.get(myKey));
		}


		System.out.println("");

		for (String myString : myHashSet) {
			System.out.println(myString + ": " + wgOneHm.get(myString));
			System.out.println(myString + ": " + wgTwoHm.get(myString));
		}

	}
}
