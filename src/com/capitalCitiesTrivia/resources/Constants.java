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
    public void printIntro(String modeSelected, Boolean guessingCountry) {
        System.out.println("Welcome to Capital Cities Trivia!");
        System.out.println();
        System.out.println("You selected " + modeSelected +" mode!");
        if (guessingCountry) {
            System.out.println("You will be shown a capital city and will have to type the country.");
        }
        else {
            System.out.println("You will be shown a country and will have to type the capital city.");
        }

        System.out.println();
        System.out.println("Good luck! The game will begin now ...");
    }

    public void printSingleQuestion(TriviaQuestion triviaQuestion, Boolean guessingCountry) {
        System.out.println();
        if (guessingCountry) {
            System.out.println("Which country has " + triviaQuestion.getCapital() + " as its capital city?");
        }
        else {
            System.out.println("What is the capital city of " + triviaQuestion.getCountry() + "?");
        }
    }

    public void printSingleAnswer(TriviaQuestion triviaQuestion, Boolean guessingCountry, String answerGiven, Boolean correct) {
        System.out.println();
        System.out.println("You answered: " + answerGiven);
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
}
