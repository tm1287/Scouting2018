package team1100.scouting2017.Dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import team1100.scouting2017.MenuItems.Settings;

/**
 * Created by 1100Admin on 2/12/2017.
 */

public class EventSelectionDialog extends DialogFragment {
    private final String[] newHampshireTeams = ("78,95,31,138,238,319,467,509,811,885," +
            "1058,1073,1100,1247,1289,1307,1512,1517,1729,1831,2084,2876,3323,3467,3566" +
            ",3958,4473,4908,4929,5422,5459,5506,5687,5735,5902,6172,6324,6328,6763").split(",");
    private final String[] rhodeIslandTeams = ("69,78,88,121,125,126,157,176,190,467,1099,1100," +
            "1124,1153,1277,1350,1757,1768,1786,1973,2064,2079,2168,2262,2877,3466,3525,3719,3780," +
            "4048,4097,4151,4176,4796,5000,5112,5846,5856,6617,6620,6731").split(",");

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        RadioGroup input = new RadioGroup(getActivity());
        RadioButton nh = new RadioButton(getActivity());
        nh.setText("Granite State");
        RadioButton ri = new RadioButton(getActivity());
        ri.setText("Rhode Island");
        RadioButton other = new RadioButton(getActivity());
        other.setText("Manual");
        input.addView(nh, 0);
        input.addView(ri, 1);
        input.addView(other,2);
        builder.setView(input);
        builder.setTitle("Choose an Event:");
        builder.setPositiveButton("ENTER", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int choice = input.getCheckedRadioButtonId();
                switch (choice){
                    case 0:
                        ((Settings)getActivity()).writeTeams(newHampshireTeams);
                        break;
                    case 1:
                        ((Settings)getActivity()).writeTeams(rhodeIslandTeams);
                        break;
                    default:
                        break;
                }
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
