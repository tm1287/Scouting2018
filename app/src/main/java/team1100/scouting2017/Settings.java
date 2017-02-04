package team1100.scouting2017;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    public void deleteData(View view){
        Snackbar.make(findViewById(android.R.id.content),"AAAAAHHHHHHH", Snackbar.LENGTH_LONG);
    }

}