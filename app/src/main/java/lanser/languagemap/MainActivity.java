package lanser.languagemap;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Region;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.*;
import controller.LanguageController;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

public class MainActivity extends AppCompatActivity  {

    private WebView webView;
    private LanguageController controller = new LanguageController();
    private Set<String> set = new HashSet<>();

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set the toolbar
        Toolbar myToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        //show the languages the user clicked
        LinearLayout langlayout = findViewById(R.id.langImagelayout);

        final SharedPreferences pref = getApplicationContext().getSharedPreferences("ClickedLanguages", Context.MODE_PRIVATE);
        final Set<String> setLanguages = pref.getStringSet("setLanguages", set);

        for (String s : setLanguages) {
            //TODO replace textview by pngs or vectors
            //temporary text until png or vector images are made
            TextView text = new TextView(this);
            text.setText(s + "   ");
            text.setHeight(50);
            langlayout.addView(text);
        }

        //create the webview
        webView = (WebView) findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);

        //set the listview if the device is in portraitmode
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            setListView();
        }

        //draw the map
        getData();
        drawMap(webView, "world");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_languages:
                Intent intentLang = new Intent(MainActivity.this, LanguageActivity.class);
                startActivity(intentLang);
                return true;

            case R.id.menu_statistics:
                Intent intentStats = new Intent(MainActivity.this, StatisticsActivity.class);
                startActivity(intentStats);
                return true;

            default:
                // When the action was not recognized.
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        getData();
        drawMap(webView, null);
    }

    /** gets the correct data for the languages selected */
    public void getData() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("ClickedLanguages", Context.MODE_PRIVATE);
        Set<String> setLanguages = pref.getStringSet("setLanguages", set);

        try {
            controller.clearGlobalDataset();
            for (String l : setLanguages) {
                String path = l.toUpperCase() + ".csv";
                controller.parseCSV(getApplicationContext().getAssets().open(path));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** sets the regions listview in the main activity */
    public void setListView() {
        //gets all drawable regions from strings.xml
        final ArrayList<String> regions = new ArrayList<>();
        String[] regionList = getResources().getStringArray(R.array.regionListArray);

        for(int i=0; i<regionList.length; i=i+2) {
            regions.add(regionList[i]);
        }

        ListView regionLayout = findViewById(R.id.regionList);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, regions);
        regionLayout.setAdapter(adapter);
        regionLayout.setFocusable(false);

        regionLayout.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("TEST CLICK HANDLER", String.valueOf(regions.get(position)));
                drawMap(webView, String.valueOf(regions.get(position)));
            }
        });
    }

    //TODO make tooltip more user friendly (no country codes, percentage instead of int)
    /** draws the worldmap, uses data from the controller */
    public void drawMap(WebView webView, String regionName) {
        String data = controller.getFormatString(controller.globalData);
        String region;
        String[] regionList = getResources().getStringArray(R.array.regionListArray);

        HashMap<String, String> regions = new HashMap<>();
        for(int i=0; i<regionList.length; i=i+2) {
            regions.put(regionList[i], regionList[i+1]);
        }

        if (regionName != null && !regionName.equals("World")) {
            region = "region: '" + regions.get(regionName) + "', resolution: 'countries', enableRegionInteractivity: true, ";
        } else region = "";

        String html = "<html><head>" +
                "  </head>"+
                "  <body>" +
                "       <div id='" + "regions_div" + "' style='" + "width:100%; height: 100%;" + "'></div>" +
                "" +
                "<script type='" + "text/javascript" + "' src='" + "https://www.google.com/jsapi" + "'></script>" +
                "<script type='" + "text/javascript" + "'>" +
                "google.load('" + "visualization" + "', '" + "1" + "', {packages:['" + "geochart" + "']});" +
                "google.setOnLoadCallback(drawRegionsMap);" +
                ""+
                "      function drawRegionsMap() {"+
                "        var data = google.visualization.arrayToDataTable(" + data + ");"+
                ""+
                "        var options = {colors: ['#293F50', '#8daba5'], " +
                                        region +

                "                       datalessRegionColor: '#293F50', " +
                "                       defaultColor: '#293F50' };" +
                ""+
                "        var chart = new google.visualization.GeoChart(document.getElementById('regions_div'));" +
                "        chart.draw(data, options);"+
                "      }"+
                "    </script>"+
                "  </body>"+
                "</html>";


        webView.loadData(html, "text/html", "UTF-8");
    }

}