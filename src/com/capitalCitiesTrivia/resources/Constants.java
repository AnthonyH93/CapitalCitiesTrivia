package com.capitalCitiesTrivia.resources;

import com.capitalCitiesTrivia.models.TriviaGame;
import com.capitalCitiesTrivia.models.TriviaQuestion;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/* Class for constants used throughout the project */
public class Constants {

    public ArrayList<String> getCountries() {
        ArrayList<String> countries = new ArrayList<String>();

        /* Project is run from the src directory */
        Path currentPath = FileSystems.getDefault().getPath("");
        String absolutePath = currentPath.toAbsolutePath().toString();
        String filePath = absolutePath + "/com/capitalCitiesTrivia/resources/countries.txt";

        /* Open countries text file and read each county line-by-line */
        try {
            BufferedReader countryReader = new BufferedReader(new FileReader(filePath));
            String country = countryReader.readLine();
            while (country != null) {
                countries.add(country);
                country = countryReader.readLine();
            }
            countryReader.close();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        return countries;
    }

    /* Common print messages */
    public void printIntro(String modeSelected, Boolean guessingCountry, Boolean isChallengeMode, int maxQuestions) {
        int maxWrong = maxQuestions/3;
        int correct = maxQuestions - maxWrong;
        System.out.println("Welcome to Capital Cities Trivia!");
        System.out.println("\nYou selected " + modeSelected +" mode!");
        if (guessingCountry) {
            System.out.println("You will be shown a capital city and will have to type the country.");
            if (isChallengeMode) {
                System.out.println("You must answer " + correct + " correctly and answer less than " + maxWrong + " incorrectly to win.");
            }
        }
        else {
            System.out.println("You will be shown a country and will have to type the capital city.");
            if (isChallengeMode) {
                System.out.println("You must answer " + correct + " correctly and less than " + maxWrong + " incorrectly to win.");
            }
        }

        System.out.println("\nGood luck! The game will begin now ...");
    }

    public void printSingleQuestion(TriviaQuestion triviaQuestion, Boolean guessingCountry) {
        if (guessingCountry) {
            System.out.println("\nWhich country has " + triviaQuestion.getCapital() + " as its capital city?");
        }
        else {
            System.out.println("\nWhat is the capital city of " + triviaQuestion.getCountry() + "?");
        }
    }

    public void printSingleAnswer(TriviaQuestion triviaQuestion, Boolean guessingCountry, String answerGiven, Boolean correct) {
        System.out.println("\nYou answered: " + answerGiven);
        if (guessingCountry) {
            if (correct) {
                System.out.println("Great job! You are correct!");
            }
            else {
                System.out.println("Incorrect! The correct answer is: " + triviaQuestion.getCountry());
            }
        }
        else {
            if (correct) {
                System.out.println("Great job! You are correct!");
            }
            else {
                System.out.println("Incorrect! The correct answer is: " + triviaQuestion.getCapital());
            }
        }
    }

    public void printCurrentRecord(TriviaGame triviaGame) {
        double correctAnswers = triviaGame.getCorrectAnswers();
        double incorrectAnswers = triviaGame.getIncorrectAnswers();
        double totalQuestions = correctAnswers + incorrectAnswers;

        System.out.println("\nCurrent record: " + triviaGame.getCorrectAnswers() + " correct and " + triviaGame.getIncorrectAnswers() + " incorrect.");

        double percentage = (correctAnswers/totalQuestions) * 100.0;
        int percent = (int) Math.round(percentage);
        System.out.println("Percentage: " + percent + "%");
    }

    public void printExtraMCInstructions() {
        System.out.println("\nEnter the letter (A, B, C or D) corresponding to your choice, case does not matter.");
    }

    public void printMCQuestion(TriviaQuestion triviaQuestion, Boolean guessingCountry) {
        String[] MCOptions = triviaQuestion.getMultipleChoiceOptions();
        if (guessingCountry) {
            System.out.println("\nWhich country has " + triviaQuestion.getCapital() + " as its capital city?");
            System.out.println("\nA. " + MCOptions[0]);
            System.out.println("B. " + MCOptions[1]);
            System.out.println("C. " + MCOptions[2]);
            System.out.println("D. " + MCOptions[3]);
        }
        else {
            System.out.println("\nWhat is the capital city of " + triviaQuestion.getCountry() + "?");
            System.out.println("\nA. " + MCOptions[0]);
            System.out.println("B. " + MCOptions[1]);
            System.out.println("C. " + MCOptions[2]);
            System.out.println("D. " + MCOptions[3]);
        }
    }
}
