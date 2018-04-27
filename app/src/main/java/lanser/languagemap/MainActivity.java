package lanser.languagemap;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import controller.LanguageController;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

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

        //create the webview
        webView = (WebView) findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);

        //draw the map
        getData();
        drawMap(webView);
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
                Intent intent = new Intent(MainActivity.this, LanguageActivity.class);
                startActivity(intent);
                return true;

            case R.id.menu_statistics:
                // TODO add handler
                return true;

            default:
                // The action was not recognized.
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        getData();
        drawMap(webView);
    }

    /** gets the correct data for the languages selected */
    public void getData() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("ClickedLanguages", Context.MODE_PRIVATE);
        Set<String> setLanguages = pref.getStringSet("setLanguages", set);

        try {
            controller.clearGlobalDataset();
            for (String l : setLanguages) {
                //TODO fix for german and portuguese
                String path = l.toUpperCase() + ".csv";
                controller.parseCSV(getApplicationContext().getAssets().open(path), l);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** draws the worldmap, uses data from the controller */
    public void drawMap(WebView webView) {
        //TODO customize following html code
        String data = controller.getFormatString(controller.globalData);

        String html = "<html><head>" +
                "<script type='" + "text/javascript" + "' src='" + "https://www.google.com/jsapi" + "'></script>" +
                "<script type='" + "text/javascript" + "'>" +
                "google.load('" + "visualization" + "', '" + "1" + "', {packages:['" + "geochart" + "']});" +
                "google.setOnLoadCallback(drawRegionsMap);" +
                ""+
                "      function drawRegionsMap() {"+
                "        var data = google.visualization.arrayToDataTable(" + data + ");"+
                ""+
                "        var options = {colors: ['#293F50', '#8daba5'], " +
                "                       enableRegionInteractivity: false, " +
                "                       datalessRegionColor: '#293F50', " +
                "                       defaultColor: '#293F50' };" +
                ""+
                "        var chart = new google.visualization.GeoChart(document.getElementById('regions_div'));"+
                ""+
                "        chart.draw(data, options);"+
                "      }"+
                "    </script>"+
                "  </head>"+
                "  <body>"+
                "       <div id='" + "regions_div" + "' style='" + "width:100%; height: 100%;" + "'></div>" +
                "  </body>"+
                "</html>";

        webView.loadData(html, "text/html", "UTF-8");
    }

}