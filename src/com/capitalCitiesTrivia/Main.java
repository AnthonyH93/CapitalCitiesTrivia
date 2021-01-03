package com.capitalCitiesTrivia;

import com.capitalCitiesTrivia.models.*;
import com.capitalCitiesTrivia.resources.*;

public class Main {

    public static void main(String[] args) {
	    /* Parse the input parameters to decide which mode to launch and the mode settings */
        /* Format for input is: mode maxQuestions */

        int mode = 0;
        int maxQuestions = Integer.MAX_VALUE;

        if (args.length < 1) {
            System.out.println("Error! Build format: mode (1-6) maxQuestions (optional)");
            System.exit(1);
        }
        else {
            mode = Integer.parseInt(args[0]);
            System.out.println("Selected mode: " + mode);

            if (mode < 1 || mode > 6) {
                System.out.println("Error! Mode must be between 1 and 6.");
                System.exit(2);
            }

            if (args.length > 1) {
                maxQuestions = Integer.parseInt(args[1]);
                System.out.println("Selected max questions: " + maxQuestions);
            }

            Boolean guessingCountry = !(mode%2 == 0);

            /* Run the trivia game with the trivia game runner instance */
            TriviaGameRunner game = new TriviaGameRunner(mode, maxQuestions, guessingCountry);
            game.runTriviaGame();
        }

        System.exit(0);
    }
}
