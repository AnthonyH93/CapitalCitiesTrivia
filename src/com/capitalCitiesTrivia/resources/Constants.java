package com.capitalCitiesTrivia.resources;

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
}
