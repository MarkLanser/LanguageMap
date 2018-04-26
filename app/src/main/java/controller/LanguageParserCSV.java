package controller;

import android.util.Log;

import java.io.*;
import java.net.URL;
import java.util.HashMap;

public class LanguageParserCSV {

    //used to parse csv files, currently manually called for seperate csv's because of above error
    public HashMap<String, String> parse(String language) {

        String line = "";
        String cvsSplitBy = ",";
        InputStream reader = getClass().getResourceAsStream("/assets/" + language.toUpperCase() + ".csv");
        HashMap<String, String> data = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(reader))) {
            String headerLine = br.readLine();

            while ((line = br.readLine()) != null) {
                // use comma as separator
                String[] country = line.split(cvsSplitBy);
                data.put(country[0], country[3]);
        }
            return data;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

}

