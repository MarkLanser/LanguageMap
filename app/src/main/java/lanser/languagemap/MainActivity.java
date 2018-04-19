package lanser.languagemap;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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
        //TODO customize following html code            function animateWorkPanels(percentage) {
        String html = "<html><head>" +
                "<script type='" + "text/javascript" + "' src='" + "https://www.google.com/jsapi" + "'></script>" +
                "<script type='" + "text/javascript" + "'>" +
                "google.load('" + "visualization" + "', '" + "1" + "', {packages:['" + "geochart" + "']});" +
                "google.setOnLoadCallback(drawRegionsMap);" +
                " function drawRegionsMap() {" +
                "  var data = google.visualization.arrayToDataTable([" +
                "['Country', 'Popularity']," + "..." +
                "]);" +
                "var options = {colors: ['#CB96CE', '#871F7B']};" +
                "var chart = new google.visualization.GeoChart(document.getElementById('regions_div'));" +
                "chart.draw(data, options);" +
                "}" +
                "</script>" +
                "</head>" +
                "<body>" +
                "<div id='" + "regions_div" + "' style='" + "width:100%; height: 100%;" + "'></div>" +
                "</body>" +
                "</html>";

        webView.loadData(html, "text/html", "UTF-8");
    }

}