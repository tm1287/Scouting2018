package team1100.scouting2017.Dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.text.InputType;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import team1100.scouting2017.MenuItems.Settings;

/**
 * Created by 1100Admin on 2/6/2017.
 */

public class EnterTeamListDialog extends DialogFragment{
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final EditText input = new EditText(getActivity());
        input.setHeight(300);
        input.setText(getTeamList());
        builder.setView(input);
        builder.setTitle("Enter List of Teams");
        builder.setPositiveButton("ENTER", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String[] teams = input.getText().toString().split("\\\\r\\\\n|\\\\n|\\\\r");;;//Josh can take my escape characters and pierce his urethra with them.
                ((Settings)getActivity()).writeTeams(teams);
            }
        });

        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                cancel();
            }
        });

        return builder.create();
    }
    public void cancel(){
        //Empty method cause IDGAF
    }
    public String getTeamList(){
        String filename = "teamList";
        List<String> teams = new ArrayList<>();
        try{
            InputStream inputStream = getActivity().openFileInput(filename);
            if(inputStream != null){
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String line = "";
                while ((line=bufferedReader.readLine())!=null){
                    teams.add(line);
                }
            }
            inputStream.close();
        }catch (Exception e){
        }
        String list = "";
        for(String t: teams){
            list+= t + "\n";
        }
        return list;
    }
}