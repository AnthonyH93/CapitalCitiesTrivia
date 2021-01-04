package com.capitalCitiesTrivia.models;

import com.capitalCitiesTrivia.resources.Constants;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class TriviaQuestion {
    /* Member variables */
    private String country;
    private String capital;
    private String[] multipleChoiceOptions;
    int multipleChoiceCorrectIndex;
    private Boolean isMC;

    /* Constructor */
    public TriviaQuestion(String input, Boolean mode) {
        this.isMC = mode;
        this.multipleChoiceOptions = new String[4];
        this.country = input;
        this.capital = "";
        this.multipleChoiceCorrectIndex = 0;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public void setMultipleChoiceOptions(String[] multipleChoiceOptions) {
        this.multipleChoiceOptions = multipleChoiceOptions;
    }

    public String[] getMultipleChoiceOptions() {
        return this.multipleChoiceOptions;
    }

    public void setMultipleChoiceCorrectIndex(int correctIndex) {
        this.multipleChoiceCorrectIndex = correctIndex;
    }

    public int getMultipleChoiceCorrectIndex() {
        return this.multipleChoiceCorrectIndex;
    }

    /* Use the REST Countries API to get the capital of a given country */
    public void getAndSetCapitalWithCountry() throws IOException, InterruptedException {
        String countryRequest = this.getCountry();
        if (countryRequest.equals("")) {
            return;
        }

        /* Create the HTTP request */
        String urlString = "https://restcountries.eu/rest/v2/name/" + countryRequest;
        String newUrlString = urlString.replaceAll(" ", "%20");

        var client = HttpClient.newHttpClient();

        var request = HttpRequest.newBuilder(URI.create(newUrlString)).build();

        var response = client.send(request, HttpResponse.BodyHandlers.ofString());

        int index = response.body().indexOf("capital");

        int startingIndex = index + 10;

        String capitalStart = response.body().substring(startingIndex);

        int i = 0;
        String capital = "";
        char nextChar = capitalStart.charAt(0);

        while (nextChar != '\"') {
            capital = capital + nextChar;
            i += 1;
            nextChar = capitalStart.charAt(i);
        }

        this.setCapital(capital);
    }

    /* Setup a multiple choice questions when the country is being guessed (one API call) */
    public void setupMultipleChoiceGuessingCountry(int index, ArrayList<String> countries) throws IOException, InterruptedException{
        String countryCorrect = this.getCountry();

        /* Setup the correct answer */
        getAndSetCapitalWithCountry();

        /* Pick other countries to be optional answers */
        int [] rands = new int[3];
        int counter = 0;
        Boolean isUnique = true;

        while (counter < 3) {
            int random = ThreadLocalRandom.current().nextInt(0,195);
            /* ensure it is a new number */
            isUnique = true;
            for (int i=0; i<3; i++) {
                if ((random == rands[i]) || (random == index)) {
                    isUnique = false;
                    break;
                }
            }
            if (isUnique) {
                rands[counter] = random;
                counter += 1;
            }
        }

        /* Find the countries corresponding to the indexes chosen */
        String [] countryOptions = new String[3];
        for (int j=0; j<3; j++) {
            countryOptions[j] = countries.get(rands[j]);
        }

        /* Put the correct country is a known index then fill up the other spaces */
        int randIndex = ThreadLocalRandom.current().nextInt(0,4);
        this.setMultipleChoiceCorrectIndex(randIndex);

        String [] finalCountryPlaces = new String[4];
        finalCountryPlaces[randIndex] = this.getCountry();

        int spaceCounter = 0;

        /* Surround the correct answer with incorrect options */
        for (int k=0; k<4; k++) {
            if (k != randIndex) {
                finalCountryPlaces[k] = countryOptions[spaceCounter];
                spaceCounter += 1;
            }
        }

        this.setMultipleChoiceOptions(finalCountryPlaces);
    }

}
