package com.capitalCitiesTrivia.models;

import com.capitalCitiesTrivia.resources.Constants;

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

    public int getCorrectAnswers() {
        return this.correctAnswers;
    }

    public int getIncorrectAnswers() {
        return this.incorrectAnswers;
    }

    public int getMaxQuestions() {
        return this.maxQuestions;
    }

    public void createQuestion(Boolean isMC) {
        /* Multiple choice */
        if (isMC) {

        }
        /* Single question */
        else {
            /* Need to get 1 random country from the list */
            int randomNumber = ThreadLocalRandom.current().nextInt(0,195);
            String randomCountry = this.countries.get(randomNumber);

            TriviaQuestion triviaQuestion = new TriviaQuestion(randomCountry, true);
            try {
                triviaQuestion.getAndSetCapitalWithCountry();
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }

            this.questions.add(triviaQuestion);
        }
    }

    public TriviaQuestion getQuestion() {
        TriviaQuestion triviaQuestion = this.questions.get(0);
        this.questions.remove(triviaQuestion);
        return triviaQuestion;
    }

}
