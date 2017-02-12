package team1100.scouting2017.tabFragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import team1100.scouting2017.R;

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

    public static String getPositionLabel(int position){
        String text;
        switch (position){
            case 0:
                text = "Red 1";
                break;
            case 1:
                text = "Red 2";
                break;
            case 2:
                text = "Red 3";
                break;
            case 3:
                text = "Blue 1";
                break;
            case 4:
                text = "Blue 2";
                break;
            case 5:
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
    public void setMatch(String match){
        try{
            pauseData[1] = match;
        }catch (Exception e){
            pauseData = new String[4];
            pauseData[1] = match;
        }
        try{
            Activity v = getActivity();
            EditText box = (EditText) v.findViewById(R.id.match_number);
            box.setText(match);
        }catch (Exception e){}
    }
    public void setNumber(String number){
        try{
            pauseData[2] = number;
        }catch (Exception e){
            pauseData = new String[4];
            pauseData[2] = number;
        }
        try{
            Activity v = getActivity();
            EditText box = (EditText) v.findViewById(R.id.team_number);
            box.setText(number);
        }catch (Exception e){}
    }
    public void setName(String name){
        try{
            pauseData[0] = name;
        }catch (Exception e){
            pauseData = new String[4];
            pauseData[0] = name;
        }
        try{
            Activity v = getActivity();
            EditText box = (EditText) v.findViewById(R.id.scouter_name);
            box.setText(name);
        }catch (Exception e){}
    }

    public int getPosition(){
        try{
            Activity v = getActivity();
            Spinner position_spin = (Spinner)v.findViewById(R.id.position_spinner);
            int position = position_spin.getSelectedItemPosition();
            return position;
        }catch (Exception e){
            return Integer.parseInt(pauseData[3]);
        }
    }

    public boolean teamOnList(String team){
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
                    teams.add(line.replace("\n",""));
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