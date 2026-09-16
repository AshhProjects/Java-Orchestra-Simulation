package lab9part1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FlashCardReader {
	BufferedReader reader;

	/**
	Creates new BufferedReader which reads from file with given filename.
	*/
	public FlashCardReader(String filename) {
		try {
			reader = new BufferedReader(new FileReader(filename));
		} catch (Exception e) {
			System.out.println("Error with BufferedReader: " + e.getMessage());
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


}
