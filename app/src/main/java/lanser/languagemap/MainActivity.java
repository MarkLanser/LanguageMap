package lanser.languagemap;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;

public class MainActivity extends AppCompatActivity  {

    private WebView webView;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set the toolbar

        Toolbar myToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        //create the webview
        webView = (WebView) findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);

        //TODO modularize following variable
        //TODO customize following html code
        String html = "<html><head>" +
                "<script type='" + "text/javascript" + "' src='" + "https://www.google.com/jsapi" + "'></script>" +
                "<script type='" + "text/javascript" + "'>" +
                "google.load('" + "visualization" + "', '" + "1" + "', {packages:['" + "geochart" + "']});" +
                "google.setOnLoadCallback(drawRegionsMap);" +
                ""+
                //TEST DATA FOR COLOR AND CHART INTERACTION
                //TODO add country / value array from business logic
                "      function drawRegionsMap() {"+
                "        var data = google.visualization.arrayToDataTable(["+
                "          ['Country', 'Language'],"+
                "          ['Germany', 50],"+
                "          ['United States', 100],"+
                "          ['GB', 100],"+
                "          ['South Africa', 100],"+
                "          ['Canada', 100],"+
                "          ['France', 40],"+
                "          ['Australia', 100],"+
                "          ['Russia', 0]"+
                "        ]);"+
                ""+
                "        var options = {colors: ['black', '#00853f'], enableRegionInteractivity: false};" +
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
                // TODO add handler
                return true;

            case R.id.menu_statistics:
                // TODO add handler
                return true;

            default:
                // The action was not recognized.
                return super.onOptionsItemSelected(item);

        }
    }

}