package team1100.scouting2017.tabFragments;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import team1100.scouting2017.R;



public class InfoFragment extends Fragment {
    public InfoFragment() {
        // Required empty public constructor
    }

    public static InfoFragment newInstance() {
        InfoFragment fragment = new InfoFragment();
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
        return inflater.inflate(R.layout.fragment_info, container, false);
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
        String[] data = new String[5];
        Activity v = getActivity();
        try{
            CheckBox noShow = (CheckBox) v.findViewById(R.id.no_show_box);
            CheckBox broken = (CheckBox) v.findViewById(R.id.broken_box);
            CheckBox disabled = (CheckBox) v.findViewById(R.id.disabled_box);
            CheckBox penalty = (CheckBox) v.findViewById(R.id.penalty_box);
            CheckBox pilot = (CheckBox) v.findViewById(R.id.pilot_box);

            data[0] = String.valueOf(noShow.isChecked());
            data[1] = String.valueOf(broken.isChecked());
            data[2] = String.valueOf(disabled.isChecked());
            data[3] = String.valueOf(penalty.isChecked());
            data[4] = String.valueOf(pilot.isChecked());
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
        this.pauseData = new String[5];
        Activity v = getActivity();

        CheckBox noShow = (CheckBox) v.findViewById(R.id.no_show_box);
        CheckBox broken = (CheckBox) v.findViewById(R.id.broken_box);
        CheckBox disabled = (CheckBox) v.findViewById(R.id.disabled_box);
        CheckBox penalty = (CheckBox) v.findViewById(R.id.penalty_box);
        CheckBox pilot = (CheckBox) v.findViewById(R.id.pilot_box);

        pauseData[0] = String.valueOf(noShow.isChecked());
        pauseData[1] = String.valueOf(broken.isChecked());
        pauseData[2] = String.valueOf(disabled.isChecked());
        pauseData[3] = String.valueOf(penalty.isChecked());
        pauseData[4] = String.valueOf(pilot.isChecked());
    }

    @Override
    public void onResume(){
        super.onResume();
        if(this.pauseData!=null){
            Activity v = getActivity();

            CheckBox noShow = (CheckBox) v.findViewById(R.id.no_show_box);
            CheckBox broken = (CheckBox) v.findViewById(R.id.broken_box);
            CheckBox disabled = (CheckBox) v.findViewById(R.id.disabled_box);
            CheckBox penalty = (CheckBox) v.findViewById(R.id.penalty_box);
            CheckBox pilot = (CheckBox) v.findViewById(R.id.pilot_box);

            noShow.setChecked(Boolean.parseBoolean(pauseData[0]));
            broken.setChecked(Boolean.parseBoolean(pauseData[1]));
            disabled.setChecked(Boolean.parseBoolean(pauseData[2]));
            penalty.setChecked(Boolean.parseBoolean(pauseData[3]));
            pilot.setChecked(Boolean.parseBoolean(pauseData[4]));
        }
    }
    public void clearData(){
        try{
            Activity v = getActivity();

            CheckBox noShow = (CheckBox) v.findViewById(R.id.no_show_box);
            CheckBox broken = (CheckBox) v.findViewById(R.id.broken_box);
            CheckBox disabled = (CheckBox) v.findViewById(R.id.disabled_box);
            CheckBox penalty = (CheckBox) v.findViewById(R.id.penalty_box);
            CheckBox pilot = (CheckBox) v.findViewById(R.id.pilot_box);

            noShow.setChecked(false);
            broken.setChecked(false);
            disabled.setChecked(false);
            penalty.setChecked(false);
            pilot.setChecked(false);
        }catch(Exception e){
            for(int i = 0; i<pauseData.length; i++)pauseData[i] = String.valueOf(false);
        }
    }
}