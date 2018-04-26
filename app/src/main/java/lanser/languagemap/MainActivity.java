package lanser.languagemap;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import controller.LanguageController;

public class MainActivity extends AppCompatActivity  {

    private WebView webView;

    LanguageController controller = new LanguageController();

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set the toolbar
        Toolbar myToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        //create the webview
        webView = (WebView) findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);

        //TODO customize following html code
        String html = "<html><head>" +
                "<script type='" + "text/javascript" + "' src='" + "https://www.google.com/jsapi" + "'></script>" +
                "<script type='" + "text/javascript" + "'>" +
                "google.load('" + "visualization" + "', '" + "1" + "', {packages:['" + "geochart" + "']});" +
                "google.setOnLoadCallback(drawRegionsMap);" +
                ""+
                "      function drawRegionsMap() {"+
                //TODO insert method from language controller when implemented
                "        var data = google.visualization.arrayToDataTable(" + controller.english + ");"+
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
        //TODO draw map again when shared preferences clickedLanguages changed
    }

}