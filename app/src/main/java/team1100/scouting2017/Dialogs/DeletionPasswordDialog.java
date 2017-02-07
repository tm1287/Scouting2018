package team1100.scouting2017.Dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.widget.EditText;
import team1100.scouting2017.MenuItems.Settings;

/**
 * Created by 1100Admin on 2/4/2017.
 */

public class DeletionPasswordDialog extends DialogFragment {
    final int password = -488707853;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final EditText input = new EditText(getActivity());
        input.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
        builder.setView(input);
        builder.setTitle("Enter Deletion Password");
        builder.setPositiveButton("ENTER", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String value = input.getText().toString();
                if(value.hashCode()==password){
                    ((Settings)getActivity()).clearFile();
                }
                else cancel();
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
        ((Settings)getActivity()).cancelPasswordMessage();
    }
}
