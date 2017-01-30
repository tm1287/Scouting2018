package team1100.scouting2017.tabFragments;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import team1100.scouting2017.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MatchFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MatchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MatchFragment extends Fragment {
    public MatchFragment() {
        // Required empty public constructor
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     *
     * @return A new instance of fragment MatchFragment.
     */
    public static MatchFragment newInstance() {
        MatchFragment fragment = new MatchFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View fragView = inflater.inflate(R.layout.fragment_match, container, false);

        Spinner spinner = (Spinner) fragView.findViewById(R.id.position_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.pos_dropdown_options, R.layout.spinner_item);
        spinner.setAdapter(adapter);

        EditText match = (EditText) fragView.findViewById(R.id.match_number);
        EditText team = (EditText)fragView.findViewById(R.id.team_number);

        match.setText("");
        team.setText("");

        return fragView;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }
    @Override
    public void onDetach() {
        super.onDetach();
    }

    public String[] getData(){
        String[] data = new String[4];
        try{
            Activity v = getActivity();
            EditText name = (EditText)v.findViewById(R.id.scouter_name);
            EditText match = (EditText)v.findViewById(R.id.match_number);
            EditText number = (EditText)v.findViewById(R.id.team_number);
            Spinner position_spin = (Spinner)v.findViewById(R.id.position_spinner);

            int position =  position_spin.getSelectedItemPosition();


            data[0] = name.getText().toString();
            data[1] = match.getText().toString();
            data[2] = number.getText().toString();
            data[3] = getPositionLabel(position);
        }catch (Exception e){
            if(pauseData!=null){
                data = this.pauseData;
            }
        }
        return data;
    }

    public String getPositionLabel(int position){
        String text;
        switch (position){
            case 1:
                text = "Red 1";
                break;
            case 2:
                text = "Red 2";
                break;
            case 3:
                text = "Red 3";
                break;
            case 4:
                text = "Blue 1";
                break;
            case 5:
                text = "Blue 2";
                break;
            case 6:
                text = "Blue 3";
                break;
            default:
                text = "Red 1";
                break;
        }
        return text;
    }

    private static String[] pauseData;

    @Override
    public void onPause(){
        super.onPause();
        Activity v = getActivity();
        this.pauseData = new String[4];

        EditText name = (EditText)v.findViewById(R.id.scouter_name);
        EditText match = (EditText)v.findViewById(R.id.match_number);
        EditText number = (EditText)v.findViewById(R.id.team_number);
        Spinner position_spin = (Spinner)v.findViewById(R.id.position_spinner);

        int position = position_spin.getSelectedItemPosition();

        pauseData[0] = name.getText().toString();
        pauseData[1] = match.getText().toString();
        pauseData[2] = number.getText().toString();
        pauseData[3] = Integer.toString(position);
    }
    @Override
    public void onResume(){
        super.onResume();
        Activity v = getActivity();
        if(pauseData!=null){
        EditText name = (EditText)v.findViewById(R.id.scouter_name);
        EditText match = (EditText)v.findViewById(R.id.match_number);
        EditText number = (EditText)v.findViewById(R.id.team_number);
        Spinner position_spin = (Spinner)v.findViewById(R.id.position_spinner);

        name.setText(this.pauseData[0]);
        match.setText(this.pauseData[1]);
        number.setText(this.pauseData[2]);
        position_spin.setSelection(Integer.parseInt(this.pauseData[3]));
        }
    }

    public void clearData(){
        try{
            Activity v = getActivity();
            EditText match = (EditText)v.findViewById(R.id.match_number);
            EditText number = (EditText)v.findViewById(R.id.team_number);

            match.setText(Integer.toString(Integer.parseInt(match.getText().toString())+1));
            number.setText("");
        }catch(Exception e){
            pauseData[1] = Integer.toString(Integer.parseInt(pauseData[1])+1);
            pauseData[2] = "";
        }
    }
}
