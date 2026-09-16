package lab9part2;

import java.io.IOException;
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
	
	/**
	'Play' subroutine.
	Asks the user the questions and answers if it's right or wrong depending on answer.
	*/
	public void play() {
		
		String userAnswer;

		for (FlashCard fs : myFlashCards) {
			System.out.println(fs.getQuestion());
			userAnswer = myToolbox.readStringFromCmd();

			if (userAnswer.equals(fs.getAnswer())) {

				System.out.println("Right answer!");
			} else {
				System.out.println("Wrong answer, the answer is " + fs.getAnswer());
			}

		}
	}

	/**
	Main subroutine. Create's new quiz object.
	*/
	public static void main(String[] args) throws IOException {
		
		Quiz myQuiz = new Quiz("Questions.txt");

	}
}
