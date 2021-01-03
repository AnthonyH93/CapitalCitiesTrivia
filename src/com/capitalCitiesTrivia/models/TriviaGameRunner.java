package com.capitalCitiesTrivia.models;

import com.capitalCitiesTrivia.models.TriviaGame;
import com.capitalCitiesTrivia.resources.Constants;

import java.util.Scanner;

/* Class to run trivia games */
public class TriviaGameRunner {
    /* Member variables */
    private TriviaGame triviaGame;
    private int mode;
    private Boolean guessingCountry;

    /* Constructor for TriviaGameRunner */
    public TriviaGameRunner(int mode, int questions, Boolean guessingCountry) {
        this.triviaGame = new TriviaGame(questions);
        this.mode = mode;
        this.guessingCountry = guessingCountry;
    }

    public int getMode() {
        return mode;
    }

    /* Function to select and run the correct trivia game based on mode */
    public void runTriviaGame() {
        int modeSelected = this.getMode();

        if (modeSelected == 1 || modeSelected == 2) {
            this.runInfiniteTriviaGame();
        }
        else if (modeSelected == 3 || modeSelected == 4) {

        }
        else if (modeSelected == 5 || modeSelected == 6) {

        }
        /* Error case: should not occur */
        else {
            System.out.println("Error, incorrect mode selected");
            return;
        }
    }

    /* Infinite trivia game is mode 1 or 2 */
    public void runInfiniteTriviaGame() {
        Constants c = new Constants();
        c.printIntro("infinite", this.guessingCountry);

        TriviaQuestion nextQuestion = null;
        String givenAnswer = null;

        /* Infinite loop for asking trivia questions and accepting answers */
        while (true) {
            /* Get a new question */
            triviaGame.createQuestion(false);
            nextQuestion = triviaGame.getQuestion();

            c.printSingleQuestion(nextQuestion, this.guessingCountry);

            Scanner scanner = new Scanner(System.in);
            String answer = scanner.nextLine();

            Boolean correctAnswer = false;

            if (this.guessingCountry) {
                correctAnswer = nextQuestion.getCountry().toLowerCase().equals(answer.toLowerCase());
            }
            else {
                correctAnswer = nextQuestion.getCapital().toLowerCase().equals(answer.toLowerCase());
            }

            if (correctAnswer) {
                triviaGame.addCorrectAnswer();
            }
            else {
                triviaGame.addIncorrectAnswer();
            }

            c.printSingleAnswer(nextQuestion, this.guessingCountry, answer, correctAnswer);
            c.printCurrentRecord(this.triviaGame);
        }
    }

    /* Challenge is mode 3 or 4 */

    /* Multiple choice is mode 5 or 6 */

}
