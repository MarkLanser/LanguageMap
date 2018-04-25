package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class LanguageParserCSV {
    //TODO fix issue of not being able to draw in mainactivity
    //used to parse csv files, currently manually called for seperate csv's because of above error
    public String csvParser() {
        String array = "[['Country', 'Language']";
        String line = "";
        String cvsSplitBy = ",";

        String path = "./app/libs/csv/English.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String headerLine = br.readLine();

            while ((line = br.readLine()) != null) {
                // use comma as separator
                String[] country = line.split(cvsSplitBy);
                array = array + ", ['" + country[0] + "', " + country[3] + "]";
        }
            array = array + ",]";

            return array;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return array;
    }

}

