package team1100.scouting2017.tabFragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.NumberPicker;

import team1100.scouting2017.R;

public class TeleopFragment extends Fragment {
    public TeleopFragment() {
        // Required empty public constructor
    }

    public static TeleopFragment newInstance() {
        TeleopFragment fragment = new TeleopFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragView = inflater.inflate(R.layout.fragment_teleop, container, false);

        NumberPicker highTele = (NumberPicker) fragView.findViewById(R.id.high_tele_picker);
        NumberPicker lowTele = (NumberPicker) fragView.findViewById(R.id.low_tele_picker);
        NumberPicker gearTele = (NumberPicker) fragView.findViewById(R.id.gear_tele_picker);

        String[] displayValuesTele = new String[601];
        for(int i = 0; i<displayValuesTele.length;i++){
            displayValuesTele[i] = Integer.toString(i);
        }

        highTele.setMinValue(0);
        highTele.setMaxValue(600);
        highTele.setWrapSelectorWheel(false);
        highTele.setDisplayedValues(displayValuesTele);
        highTele.setValue(0);

        lowTele.setMinValue(0);
        lowTele.setMaxValue(600);
        lowTele.setWrapSelectorWheel(false);
        lowTele.setDisplayedValues(displayValuesTele);
        lowTele.setValue(0);

        String[] gearDisplayValues = new String[13];
        for(int i =0; i<gearDisplayValues.length;i++){
            gearDisplayValues[i] = Integer.toString(i);
        }
        gearTele.setMinValue(0);
        gearTele.setMaxValue(12);
        gearTele.setWrapSelectorWheel(false);
        gearTele.setDisplayedValues(gearDisplayValues);
        gearTele.setValue(0);

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
        Activity v = getActivity();
        try{
            NumberPicker highFuel = (NumberPicker)v.findViewById(R.id.high_tele_picker);
            NumberPicker lowFuel = (NumberPicker) v.findViewById(R.id.low_tele_picker);
            NumberPicker gear = (NumberPicker) v.findViewById(R.id.gear_tele_picker);
            CheckBox climb = (CheckBox) v.findViewById(R.id.climb_box);

            data[0] = Integer.toString(highFuel.getValue());
            data[1] = Integer.toString(lowFuel.getValue());
            data[2] = Integer.toString(gear.getValue());
            data[3] = String.valueOf(climb.isChecked());

        }catch (Exception e){
            if(pauseData!=null){
                data = this.pauseData;
            }
        }

        return data;
    }

    private static String[] pauseData;

    @Override
    public void onPause(){
        super.onPause();
        pauseData = new String[4];
        Activity v = getActivity();

        NumberPicker highFuel = (NumberPicker)v.findViewById(R.id.high_tele_picker);
        NumberPicker lowFuel = (NumberPicker) v.findViewById(R.id.low_tele_picker);
        NumberPicker gear = (NumberPicker) v.findViewById(R.id.gear_tele_picker);
        CheckBox climb = (CheckBox) v.findViewById(R.id.climb_box);

        pauseData[0] = Integer.toString(highFuel.getValue());
        pauseData[1] = Integer.toString(lowFuel.getValue());
        pauseData[2] = Integer.toString(gear.getValue());
        pauseData[3] = String.valueOf(climb.isChecked());
    }

    @Override
    public void onResume(){
        super.onResume();
        if(pauseData!=null){
            Activity v = getActivity();

            NumberPicker highFuel = (NumberPicker)v.findViewById(R.id.high_tele_picker);
            NumberPicker lowFuel = (NumberPicker) v.findViewById(R.id.low_tele_picker);
            NumberPicker gear = (NumberPicker) v.findViewById(R.id.gear_tele_picker);
            CheckBox climb = (CheckBox) v.findViewById(R.id.climb_box);

            highFuel.setValue(Integer.parseInt(this.pauseData[0]));
            lowFuel.setValue(Integer.parseInt(this.pauseData[1]));
            gear.setValue(Integer.parseInt(this.pauseData[2]));
            climb.setChecked(Boolean.parseBoolean(this.pauseData[3]));
        }
    }
    public void clearData(){
        try{
            Activity v = getActivity();

            NumberPicker highFuel = (NumberPicker)v.findViewById(R.id.high_tele_picker);
            NumberPicker lowFuel = (NumberPicker) v.findViewById(R.id.low_tele_picker);
            NumberPicker gear = (NumberPicker) v.findViewById(R.id.gear_tele_picker);
            CheckBox climb = (CheckBox) v.findViewById(R.id.climb_box);

            highFuel.setValue(0);
            lowFuel.setValue(0);
            gear.setValue(0);
            climb.setChecked(false);
        }catch (Exception e){
            for(int i =0; i<3; i++)pauseData[i]=Integer.toString(0);
            pauseData[3] = String.valueOf(false);
        }
    }
}
