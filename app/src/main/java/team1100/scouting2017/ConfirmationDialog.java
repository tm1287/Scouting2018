package team1100.scouting2017;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.VoiceInteractor;
import android.content.DialogInterface;
import android.os.Bundle;

/**
 * Created by 1100Admin on 1/23/2017.
 */

public class ConfirmationDialog extends DialogFragment {
    private String yesButton;
    private String noButton;
    private String message;

    private boolean input;

    public ConfirmationDialog(){
        yesButton = "";
        noButton = "";
        message = "";
        input = false;
    }

    public void setDialogs(String yesButton, String noButton, String message){
        this.yesButton=yesButton;
        this.noButton=noButton;
        this.message=message;
    }

    public boolean getValue(){
        System.out.println("Value retrieved");
        return input;
    }

    public void setInput(boolean value){
        input = value;
        System.out.println("Value Set");
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(message)
                .setPositiveButton(yesButton, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        ((MainActivity)getActivity()).doSubmit();
                    }
                })
                .setNegativeButton(noButton, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        ((MainActivity)getActivity()).doThanks();
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}