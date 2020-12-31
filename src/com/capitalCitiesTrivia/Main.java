package com.capitalCitiesTrivia;

//import com.capitalCitiesTrivia.modes.*;
import com.capitalCitiesTrivia.resources.*;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
	    System.out.println("Welcome to Capital Cities Trivia!");

	    /* Parse the input parameters to decide which mode to launch and the mode settings */
        /* Format for input is: mode maxQuestions */

        int mode = 0;
        int maxQuestions = Integer.MAX_VALUE;

        if (args.length < 1) {
            System.out.println("Error! Build format: mode (1,2 or 3) maxQuestions (optional)");
            System.exit(1);
        }
        else {
            mode = Integer.parseInt(args[0]);
            System.out.println("Selected mode: " + mode);

            if (mode < 1 || mode > 3) {
                System.out.println("Error! Mode must be 1, 2 or 3.");
                System.exit(2);
            }

            if (args.length > 1) {
                maxQuestions = Integer.parseInt(args[1]);
                System.out.println("Selected max questions: " + maxQuestions);
            }

            Constants constants = new Constants();
            ArrayList<String> c = constants.getCountries();

        }

        System.exit(0);
    }
}
