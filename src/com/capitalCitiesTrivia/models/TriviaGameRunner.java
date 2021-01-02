package com.capitalCitiesTrivia.models;

import com.capitalCitiesTrivia.models.TriviaGame;
import com.capitalCitiesTrivia.resources.Constants;

/* Class to run trivia games */
public class TriviaGameRunner {
    /* Member variables */
    private TriviaGame triviaGame;
    private int mode;

    /* Constructor for TriviaGameRunner */
    public TriviaGameRunner(int mode, int questions) {
        this.triviaGame = new TriviaGame(questions);
        this.mode = mode;
    }

    public int getMode() {
        return mode;
    }

    /* Function to select and run the correct trivia game based on mode */
    public void runTriviaGame() {
        int modeSelected = this.getMode();

        if (modeSelected == 1) {
            this.runInfiniteTriviaGame();
        }
        else if (modeSelected == 2) {

        }
        else if (modeSelected == 3) {

        }
        /* Error case: should not occur */
        else {
            System.out.println("Error, incorrect mode selected");
            return;
        }
    }

    /* Infinite trivia game is mode 1 */
    public void runInfiniteTriviaGame() {
        Constants c = new Constants();
        c.printIntro("infinite", true);
    }

}
