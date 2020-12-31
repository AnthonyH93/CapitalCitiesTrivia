package com.capitalCitiesTrivia.models;

import com.capitalCitiesTrivia.resources.Constants;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;


public class TriviaGame {
    /* Member variables */
    private int maxQuestions;
    private int correctAnswers;
    private int incorrectAnswers;
    public ArrayList<TriviaQuestion> questions;
    public ArrayList<String> countries;

    /* Constructor */
    public TriviaGame(int ques) {
        this.maxQuestions = ques;
        this.correctAnswers = 0;
        this.incorrectAnswers = 0;
        this.questions = new ArrayList<TriviaQuestion>();
        Constants c = new Constants();
        this.countries = c.getCountries();
    }

    /* Member functions */
    public void addCorrectAnswer() {
        this.correctAnswers += 1;
    }

    public void addIncorrectAnswer() {
        this.incorrectAnswers += 1;
    }

    public void getQuestion(Boolean isMC) {
        /* Multiple choice */
        if (isMC) {

        }
        /* Single question */
        else {
            /* Need to get 1 random country from the list */
            int randomNumber = ThreadLocalRandom.current().nextInt(0,196);
            String randomCountry = this.countries.get(randomNumber);
            System.out.println(randomCountry);

            TriviaQuestion triviaQuestion = new TriviaQuestion(randomCountry, true);
            //System.out.println(triviaQuestion.getCountry());
            try {
                triviaQuestion.getCapitalWithCountry2();
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }

            this.questions.add(triviaQuestion);
        }
    }

}
