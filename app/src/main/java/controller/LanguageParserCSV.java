package controller;

import android.renderscript.ScriptGroup;
import android.util.Log;

import java.io.*;
import java.net.URL;
import java.util.HashMap;

public class LanguageParserCSV {

    /** used to parse csv files (languages) */
    public HashMap<String, String> parse(InputStream is, String language) {

        String line = "";
        String cvsSplitBy = ",";

        HashMap<String, String> data = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            String headerLine = br.readLine();

            while ((line = br.readLine()) != null) {
                String[] country = line.split(cvsSplitBy);
                data.put(country[0], country[3]);
            }
            return data;

        } catch (Exception e) {
            e.printStackTrace();
            return data;
        }
    }
}

