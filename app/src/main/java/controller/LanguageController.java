package controller;

import android.util.Log;

import java.io.InputStream;
import java.util.*;

public class LanguageController {
    private LanguageParserCSV parserCSV = new LanguageParserCSV();
    public HashMap<String, String> globalData = new HashMap<>();

    /** manages the global dataset. Combines multiple languages (multiple hashmaps) if chosen by the user,
     *  Is called each time a user selects or deselects languages
     * @param data
     */
    public void getDataList(Map<String, String> data) {
        //TODO should only call the parser when starting the app for the first time, but time will tell how performance is
        Set dataset = data.entrySet();
        Iterator i = dataset.iterator();

        if (globalData.isEmpty()) {
            globalData.putAll(data);
        } else {
            while (i.hasNext()) {
                Map.Entry me = (Map.Entry) i.next();
                int intS = Integer.parseInt(data.get(me.getKey().toString()));

                //if the global dataset contains the key and the new value is higher than the old, overwrite it
                //if the global dataset does not yet contain the key, add it
                if (globalData.containsKey(me.getKey().toString())
                        && intS > Integer.parseInt(globalData.get(me.getKey().toString()))
                        || !globalData.containsKey(me.getKey().toString())) {
                    Log.d("TEST CONTROLLER MAP", globalData.containsKey(me.getKey().toString()) + " EN " + me.getKey().toString());
                    globalData.put(me.getKey().toString(), me.getValue().toString());
                }
            }
        }
    }

    /** used to convert the data from the global hashmap to the right format for the
     * presentation (geochart)
     * @param set
     * */
    public String getFormatString(Map<String, String> set) {
        String dataformatForPresentation = "[['Country', 'Language'], ";
        Set dataset = set.entrySet();
        Iterator i = dataset.iterator();

        while (i.hasNext()) {
            Map.Entry me = (Map.Entry) i.next();
            dataformatForPresentation = dataformatForPresentation + "['" + me.getKey().toString() + "', " + me.getValue().toString() + "], ";
        }
        dataformatForPresentation = dataformatForPresentation + "]";
        return dataformatForPresentation;
    }

    /** used to parse a language (csv file)
     * @param is
     * */
    public void parseCSV(InputStream is) {
        HashMap<String, String> data = parserCSV.parse(is);
        getDataList(data);
    }

    public void clearGlobalDataset() {
        globalData.clear();
    }
}
