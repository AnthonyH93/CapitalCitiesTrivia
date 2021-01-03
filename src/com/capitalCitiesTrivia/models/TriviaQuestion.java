package com.capitalCitiesTrivia.models;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class TriviaQuestion {
    /* Member variables */
    private String country;
    private String capital;
    private String[] multipleChoiceOptions;
    private Boolean isGuessingCountry;

    /* Constructor */
    public TriviaQuestion(String input, Boolean mode) {
        this.isGuessingCountry = mode;
        this.multipleChoiceOptions = new String[4];
        if (this.isGuessingCountry == false) {
            this.country = "";
            this.capital = input;
        } else {
            this.country = input;
            this.capital = "";
        }
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

}
