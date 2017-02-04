package team1100.scouting2017.Dialogs.Verification;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.widget.EditText;

import team1100.scouting2017.MainActivity;

/**
 * Created by 1100Admin on 1/30/2017.
 */

public class MissingInformationDialogB extends DialogFragment {
    public MissingInformationDialogB(){
        //Required Empty Constructor
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final EditText input = new EditText(getActivity());
        input.setInputType(InputType.TYPE_CLASS_NUMBER);
        builder.setView(input);
        builder.setTitle("Enter Match Number");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String value = input.getText().toString();
                if(!value.equals("")&&Integer.parseInt(value)<150){
                    ((MainActivity)getActivity()).matchVerifyC(value);
                }else ((MainActivity)getActivity()).matchVerifyB(null);
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
}
