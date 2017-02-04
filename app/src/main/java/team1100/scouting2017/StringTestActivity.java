package team1100.scouting2017;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.TextView;

public class StringTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_string_test);

        Intent intent = getIntent();
        String[] message = intent.getStringArrayExtra(MainActivity.EXTRA_MESSAGE);
        TextView textView = new TextView(this);
        textView.setTextSize(40);
        String text = "";
        for(String d :message){
            text+=d;
        }
        textView.setText(text);
        ViewGroup layout = (ViewGroup) findViewById(R.id.activity_string_test);
        layout.addView(textView);

    }
}
