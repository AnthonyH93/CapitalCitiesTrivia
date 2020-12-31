package com.capitalCitiesTrivia.models;

import java.io.*;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static java.util.stream.Collectors.toList;

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
    public String getCapitalWithCountry() throws IOException {
        String capitalFromCountry = "test";

        String countryRequest = this.getCountry();
        if (countryRequest.equals("")) {
            return null;
        }

        /* Create the HTTP request */
        String urlString = "https://restcountries.eu/rest/v2/name/" + countryRequest;
        System.out.println(urlString);
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        //connection.setRequestProperty("Content-Type", "application/json");
        connection.setConnectTimeout(5000);
        connection.setReadTimeout(5000);


        /* Do the request */
        int status = connection.getResponseCode();

        System.out.println(status);

        Reader reader = null;

        /* Failure */
        if (status > 299) {
            reader = new InputStreamReader(connection.getErrorStream());
        }
        /* Success */
        else {
            reader = new InputStreamReader(connection.getInputStream());
        }


        /* Read the response */
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String input = in.readLine();
        while (input != null) {
            System.out.println(in);
            input = in.readLine();
        }
        in.close();


        connection.disconnect();

        return capitalFromCountry;
    }

    public String getCapitalWithCountry2() throws IOException, InterruptedException {
        String countryRequest = this.getCountry();
        if (countryRequest.equals("")) {
            return null;
        }

        /* Create the HTTP request */
        String urlString = "https://restcountries.eu/rest/v2/name/" + countryRequest;
        System.out.println(urlString);

        var client = HttpClient.newHttpClient();

        var request = HttpRequest.newBuilder(URI.create(urlString)).build();

        var response = client.send(request, HttpResponse.BodyHandlers.ofString());

        int index = response.body().indexOf("c");

        System.out.println(index);
        System.out.println(response.body());

        return null;
    }

}
