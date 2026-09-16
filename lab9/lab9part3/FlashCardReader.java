package lab9part3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

public class FlashCardReader {
	
	BufferedReader reader;
	
	int noLines = 0;
	
	/**
	Creates new BufferedReader which reads from file with given filename.
	*/
	public FlashCardReader(String filename) {
		
		try {
			reader = new BufferedReader(new FileReader(filename));
		} catch (Exception e) {
			System.out.println("Error with BufferedReader: " + e.getMessage());
		}

		setLineAmount(filename);
	}
	
	/**
	Sets 'noLines' to the amount of lines that are in the file.
	*/
	public void setLineAmount(String filename) {

		try (Stream<String> fileStream = Files.lines(Paths.get(filename))) {
			noLines = (int) fileStream.count();
		} catch (Exception e) {
			e.getMessage();
		}

	}
	
	/**
	Gets the next line in the file.
	*/
	public String getLine() {

		try {
			return reader.readLine();
		} catch (Exception e) {
			System.out.println("Error reading file: " + e.getMessage());
			return null;
		}
	}
	
	/**
	Checks if the file is ready.
	*/
	public boolean fileIsReady() {
		try {
			return reader.ready();
		} catch (Exception e) {
			System.out.println("Error checking file readiness: " + e.getMessage());
			return false;
		}
	}
	
	/**
	Returns an ArrayList of flashcards taken from the file.
	*/
	public ArrayList<FlashCard> getFlashCards() {
		ArrayList flashCardsList = new ArrayList<FlashCard>();

		String myLine;
		for (int i = 1; i <= noLines; i++) {
			myLine = getLine();
			String[] parts = myLine.split(":");

			flashCardsList.add(new FlashCard(parts[0], parts[1]));
		}

		return flashCardsList;
	}



}
