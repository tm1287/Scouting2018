package team1100.scouting2017.MenuItems;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.io.FileOutputStream;

import team1100.scouting2017.Dialogs.DeletionPasswordDialog;
import team1100.scouting2017.Dialogs.EnterTeamListDialog;
import team1100.scouting2017.Dialogs.EventSelectionDialog;
import team1100.scouting2017.MainActivity;
import team1100.scouting2017.R;

public class Settings extends AppCompatActivity {

    private static int number = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    public void deleteData(View view){
        DeletionPasswordDialog dialog = new DeletionPasswordDialog();
        dialog.show(getFragmentManager(), "DELETE_PASS");
    }

    public void setTeamList(View view){
        EnterTeamListDialog dialog = new EnterTeamListDialog();
        dialog.show(getFragmentManager(), "TEAM_ENTRY");
    }

    public void clearFile(){
        String filename = "scoutingData";
        FileOutputStream outputStream;
        try {
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write("".getBytes());
            outputStream.close();
        } catch (Exception e) {
            Snackbar.make(findViewById(android.R.id.content),"Error Clearing File",Snackbar.LENGTH_LONG).show();
            e.printStackTrace();
        }
        MainActivity.clearDataStore();
        Snackbar.make(findViewById(android.R.id.content),"Data Cleared Successfully",Snackbar.LENGTH_LONG).show();
    }
    public void writeTeams(String[] teams){
        String filename = "teamList";
        FileOutputStream outputStream;
        try {
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            for(String t: teams){
                outputStream.write((t+"\n").getBytes());
            }
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void cancelPasswordMessage(){
        Snackbar.make(findViewById(android.R.id.content),"Hey! What are you trying to do!", Snackbar.LENGTH_LONG).show();
    }
    public void selectEvent(View view){
        EventSelectionDialog dialog = new EventSelectionDialog();
        dialog.show(getFragmentManager(), "EVENT_GET");
    }
}