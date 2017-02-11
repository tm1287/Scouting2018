package team1100.scouting2017.Dialogs.Verification;

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

import team1100.scouting2017.MainActivity;

/**
 * Created by 1100Admin on 1/30/2017.
 */

public class MissingInformationDialogC extends DialogFragment {
    public MissingInformationDialogC(){
        //Required Empty Constructor
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final EditText input = new EditText(getActivity());
        input.setInputType(InputType.TYPE_CLASS_NUMBER);
        builder.setView(input);
        builder.setTitle("Enter Team Number");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String value = input.getText().toString();
                if(!value.equals("")&&Integer.parseInt(value)<7000&&teamOnList(value)){
                    ((MainActivity)getActivity()).matchVerifyD(value);
                }else ((MainActivity)getActivity()).matchVerifyC(null);
            }
        });

        builder.setNegativeButton("CANCEL SUBMIT", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ((MainActivity)getActivity()).cancelSubmit();
            }
        });

        return builder.create();
    }

    private boolean teamOnList(String team){
        boolean onList = false;
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
            onList = true;
            //Snackbar.make(getView(), "Team list error: accepting all numbers." , Snackbar.LENGTH_LONG).show();
        }
        if(teams.size()==0){
            onList = true;
            //Snackbar.make(getView(), "Team list empty! Accepting all numbers.", Snackbar.LENGTH_LONG).show();
        }
        if(teams.contains(team)){
            onList = true;
        }
        return onList;
    }

}
