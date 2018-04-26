package lanser.languagemap;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public class LanguageActivity extends AppCompatActivity {

    //get all the supported languages from strings.xml and initiate empty array
    //for handling clicked checkboxes (languages)
    String[] languages = {};
    final ArrayList<String> clickedLanguages = new ArrayList<>();

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_languages);

        //set the toolbar
        Toolbar myToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        //get the linear layout for adding checkboxes dynamically
        LinearLayout layout = findViewById(R.id.LanguageLinearLayout);

        languages = getResources().getStringArray(R.array.languagesArray);

        //dynamically add checkboxes to layout
        for(String s : languages) {
            //TODO set checkbox to checked if its in the shared preferences set

            final CheckBox chk = new CheckBox(this);
            chk.setText(s);

            chk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (clickedLanguages.contains(chk.getText())) {
                        clickedLanguages.remove(chk.getText());
                    } else clickedLanguages.add(chk.getText().toString());
                }
            });

            layout.addView(chk);
        }
    }

    @Override
    public void onPause() {
        super.onPause();

        //persist the data of which language is checked in shared preferences
        Set<String> set = new HashSet<String>(clickedLanguages);
        SharedPreferences pref = getApplicationContext().getSharedPreferences("ClickedLanguages", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.clear();
        editor.putStringSet("setLanguages", set);
        editor.commit();

        //TODO remove following statements when setting languages works
        //debug log
        Set<String> someStringSet = pref.getStringSet("setLanguages", set);
        Log.v("test , ", someStringSet.toString());
    }

}



