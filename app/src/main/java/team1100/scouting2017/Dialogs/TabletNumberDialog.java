package team1100.scouting2017.Dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.widget.EditText;

import team1100.scouting2017.MainActivity;
import team1100.scouting2017.MenuItems.Settings;

/**
 * Created by 1100Admin on 2/11/2017.
 */
@Deprecated
public class TabletNumberDialog extends DialogFragment{
    public TabletNumberDialog(){

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final EditText input = new EditText(getActivity());
        input.setInputType(InputType.TYPE_CLASS_NUMBER);
        builder.setView(input);
        builder.setTitle("Enter Tablet Number");
        builder.setPositiveButton("ENTER", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int value = Integer.parseInt(input.getText().toString());
                if(value>=1&&value<=6){
                    ((Settings)getActivity()).setTabletNumber(value);
                }
                else ((Settings)getActivity()).promptTabletNumber(null);
            }
        });

        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        return builder.create();
    }
}
