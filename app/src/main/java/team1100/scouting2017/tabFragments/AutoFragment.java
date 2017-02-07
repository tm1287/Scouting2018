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

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AutoFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AutoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AutoFragment extends Fragment {
    public AutoFragment() {
        // Required empty public constructor
    }
    public static AutoFragment newInstance() {
        AutoFragment fragment = new AutoFragment();
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragView = inflater.inflate(R.layout.fragment_auto, container, false);

        NumberPicker highAuto = (NumberPicker) fragView.findViewById(R.id.high_auto_picker);
        NumberPicker lowAuto = (NumberPicker) fragView.findViewById(R.id.low_auto_picker);
        NumberPicker gearAuto = (NumberPicker) fragView.findViewById(R.id.gear_auto_picker);
        String[] displayValuesA = new String[111];
        for(int i = 0; i<displayValuesA.length;i++){
            displayValuesA[i] = Integer.toString(i);
        }

        highAuto.setMinValue(0);
        highAuto.setMaxValue(110);
        highAuto.setWrapSelectorWheel(false);
        highAuto.setDisplayedValues(displayValuesA);
        highAuto.setValue(0);

        lowAuto.setMinValue(0);
        lowAuto.setMaxValue(110);
        lowAuto.setWrapSelectorWheel(false);
        lowAuto.setDisplayedValues(displayValuesA);
        lowAuto.setValue(0);

        gearAuto.setMinValue(0);
        gearAuto.setMaxValue(3);
        gearAuto.setWrapSelectorWheel(false);
        String[] gearDisplayValues = {"0","1","2","3"};
        gearAuto.setDisplayedValues(gearDisplayValues);
        gearAuto.setValue(0);

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
        Activity v = getActivity();
        String[] data = new String[4];
        try{
            NumberPicker highFuel = (NumberPicker) v.findViewById(R.id.high_auto_picker);
            NumberPicker lowFuel = (NumberPicker) v.findViewById(R.id.low_auto_picker);
            NumberPicker gear = (NumberPicker) v.findViewById(R.id.gear_auto_picker);
            CheckBox line = (CheckBox) v.findViewById(R.id.line_auto_box);

            data[0] = Integer.toString(highFuel.getValue());
            data[1] = Integer.toString(lowFuel.getValue());
            data[2] = Integer.toString(gear.getValue());
            data[3] = String.valueOf(line.isChecked());
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
        Activity v = getActivity();
        this.pauseData = new String[4];

        NumberPicker highFuel = (NumberPicker) v.findViewById(R.id.high_auto_picker);
        NumberPicker lowFuel = (NumberPicker) v.findViewById(R.id.low_auto_picker);
        NumberPicker gear = (NumberPicker) v.findViewById(R.id.gear_auto_picker);
        CheckBox line = (CheckBox) v.findViewById(R.id.line_auto_box);

        pauseData[0] = Integer.toString(highFuel.getValue());
        pauseData[1] = Integer.toString(lowFuel.getValue());
        pauseData[2] = Integer.toString(gear.getValue());
        pauseData[3] = String.valueOf(line.isChecked());
    }
    @Override
    public void onResume(){
        super.onResume();
        if(this.pauseData!=null){
            Activity v = getActivity();

            NumberPicker highFuel = (NumberPicker) v.findViewById(R.id.high_auto_picker);
            NumberPicker lowFuel = (NumberPicker) v.findViewById(R.id.low_auto_picker);
            NumberPicker gear = (NumberPicker) v.findViewById(R.id.gear_auto_picker);
            CheckBox line = (CheckBox) v.findViewById(R.id.line_auto_box);

            highFuel.setValue(Integer.parseInt(this.pauseData[0]));
            lowFuel.setValue(Integer.parseInt(this.pauseData[1]));
            gear.setValue(Integer.parseInt(this.pauseData[2]));
            line.setChecked(Boolean.parseBoolean(this.pauseData[3]));
        }
    }
    public void clearData(){
        try{
            Activity v = getActivity();

            NumberPicker highFuel = (NumberPicker) v.findViewById(R.id.high_auto_picker);
            NumberPicker lowFuel = (NumberPicker) v.findViewById(R.id.low_auto_picker);
            NumberPicker gear = (NumberPicker) v.findViewById(R.id.gear_auto_picker);
            CheckBox line = (CheckBox) v.findViewById(R.id.line_auto_box);

            highFuel.setValue(0);
            lowFuel.setValue(0);
            gear.setValue(0);
            line.setChecked(false);
        }catch(Exception e){
            for(int i =0; i<3; i++)pauseData[i]=Integer.toString(0);
            pauseData[3] = String.valueOf(false);
        }
    }
}
