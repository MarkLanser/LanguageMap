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

    String[] languages = {};

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_languages);

        //set the toolbar
        Toolbar myToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        addCheckboxes();
    }

    public void addCheckboxes() {
        //get the linear layout for adding checkboxes dynamically
        LinearLayout layout = findViewById(R.id.LanguageLinearLayout);
        layout.removeAllViews();

        //get all the supported languages from strings.xml
        languages = getResources().getStringArray(R.array.languagesArray);

        //Get the set setLanguages from shared preferences
        Set<String> set = new HashSet<>();
        final SharedPreferences pref = getApplicationContext().getSharedPreferences("ClickedLanguages", Context.MODE_PRIVATE);
        final Set<String> someStringSet = pref.getStringSet("setLanguages", set);

        //dynamically add checkboxes to layout
        for(String s : languages) {
            final CheckBox chk = new CheckBox(this);
            chk.setText(s);

            //check the checkbox if the language is previously checked (and in setLanguages)
            if (someStringSet.contains(chk.getText())) {
                chk.setChecked(true);
            }

            chk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SharedPreferences.Editor editor = pref.edit();
                    editor.clear();

                    //if the language is previously checked, remove from shared preferences,
                    //otherwise add it
                    if (someStringSet.contains(chk.getText())) {
                        someStringSet.remove(chk.getText());
                        editor.putStringSet("setLanguages", someStringSet);
                        //debug statement (temporarily)
                        Log.d("TEST ONCLICK", someStringSet.toString());
                        editor.commit();
                    } else {
                        someStringSet.add(chk.getText().toString());
                        editor.putStringSet("setLanguages", someStringSet);
                        //debug statement (temporarily)
                        Log.d("TEST ONCLICK", someStringSet.toString());
                        editor.commit();
                    }
                }
            });
            layout.addView(chk);
        }
    }
}



