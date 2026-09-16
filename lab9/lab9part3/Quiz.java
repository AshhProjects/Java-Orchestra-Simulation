package lab9part3;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

public class Quiz {
	
	ArrayList<FlashCard> myFlashCards;
	
	Toolbox myToolbox = new Toolbox();
	
	/**
	Constructor of the class.
	*/
	public Quiz(String filename) {
		FlashCardReader myFlashCardReader = new FlashCardReader(filename);

		myFlashCards = myFlashCardReader.getFlashCards();

		play();
	}
	
	
	ArrayList<String> saveFile = new ArrayList<String>();
	
	/**
	'save' subroutine.
	Save's user's results into the 'save.txt' file.
	*/
	public void save(Integer amRight) {
		try {

			PrintStream output = new PrintStream("save.txt");

			Integer amOfQs = 0;
			for (String eachLine : saveFile) {
				output.println(eachLine);
				amOfQs++;
			}
			Double percentage = 0.0;
			percentage = ((100 * (Double.valueOf(amRight) / Double.valueOf(amOfQs))));
			output.println(amRight + "," + amOfQs + "," + percentage);

			output.close();
		} catch (Exception e) {
			e.getMessage();
		}
	}
	
	/**
	'Play' subroutine.
	Asks the user the questions and answers if it's right or wrong depending on answer.
	*/
	public void play() {
		String userAnswer;
		Integer amRight = 0;

		System.out.println("Do you want to save your results? Y/N");
		String userInput;
		userInput = myToolbox.readStringFromCmd();

		for (FlashCard fs : myFlashCards) {
			System.out.println(fs.getQuestion());
			userAnswer = myToolbox.readStringFromCmd();

			if (userAnswer.equals(fs.getAnswer())) {

				System.out.println("Right answer!");
				amRight++;
				saveFile.add(fs.getQuestion() + "," + userAnswer + "," + "right");
			} else {
				System.out.println("Wrong answer, the answer is " + fs.getAnswer());

				saveFile.add(fs.getQuestion() + "," + userAnswer + "," + "wrong");
			}

		}

		if (userInput.equals("Y")) {
			save(amRight);
		}
	}
	
	/**
	Main subroutine. Create's new quiz object.
	*/
	public static void main(String[] args) {
		Quiz myQuiz = new Quiz("Questions.txt");

	}
}
