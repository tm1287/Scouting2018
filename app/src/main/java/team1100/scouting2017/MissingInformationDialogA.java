package team1100.scouting2017;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.widget.EditText;

/**
 * Created by 1100Admin on 1/30/2017.
 */

public class MissingInformationDialogA extends DialogFragment {
    public MissingInformationDialogA(){
        //Required Empty Constructor
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final EditText input = new EditText(getActivity());
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);
        builder.setTitle("Enter Scouter Name");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String value = input.getText().toString();
                if(!value.equals("")){
                    ((MainActivity)getActivity()).matchVerifyB(value);
                }else ((MainActivity)getActivity()).matchVerifyA();
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
