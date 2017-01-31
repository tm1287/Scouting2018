package team1100.scouting2017;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import team1100.scouting2017.tabFragments.AutoFragment;
import team1100.scouting2017.tabFragments.InfoFragment;
import team1100.scouting2017.tabFragments.MatchFragment;
import team1100.scouting2017.tabFragments.TeleopFragment;


public class MainActivity extends FragmentActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    public List<String> fragments = new Vector<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        fragments.add(MatchFragment.class.getName());
        fragments.add(AutoFragment.class.getName());
        fragments.add(TeleopFragment.class.getName());
        fragments.add(InfoFragment.class.getName());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        for(int i =0; i<4; i++)tabLayout.getTabAt(i);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Create confirmation dialog to send
                ConfirmationDialog confirmationDialog = new ConfirmationDialog();
                confirmationDialog.setDialogs("Yes, I'm sure.", "No! Finna double check.", "Are you sure all data is completed and correct?");
                confirmationDialog.show(getFragmentManager(), "P_");
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Sends the data. Called by a positve response to the confirmation dialog.
     */
    public void doSubmit() {
        System.out.println("Starting doSubmit");
        String filename = "scoutingData";
        System.out.println("Calling getMatchVaues()");
        String[] data = getMatchValues();
        if (data==null){
            Snackbar.make(findViewById(android.R.id.content), "Null data: Submit cancelled", Snackbar.LENGTH_LONG).show();
            return;
        }
        //write to file
        System.out.println("Starting file write");
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(getBaseContext().openFileOutput(filename,
                    Context.MODE_PRIVATE));
            String dataLine = "";
            //Put array in a single line
            for(int i =0; i<data.length; i++){
                dataLine+=data[i]+"~";//Tilde will be used to split string for data storage
            }
            //trim ending ~
            dataLine = dataLine.substring(0,dataLine.length()-1);
            //Write to file
            System.out.println("Writing data");
            outputStreamWriter.write(dataLine);
            outputStreamWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //Attempt to send data to server
        //TODO SEND THIS LIST retrieveData();
        System.out.println("Finished collection and write, testing retrieval and display");
        testStrings(retrieveData());

    }
    public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    public void testStrings(String[] data){
        System.out.println("Launching Activity to Display Data");
        Intent intent = new Intent(this, StringTestActivity.class);
        String message = data[0];
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
    private String[] getMatchValues() {
        System.out.println("Attempting to get match values");
        System.out.println("Getting fragment manager");
        FragmentManager fm = getSupportFragmentManager();
        System.out.println("Getting list of tab fragments");
        List<Fragment> tabs = fm.getFragments();
        System.out.println("Getting Match tab");
        MatchFragment match = (MatchFragment) tabs.get(0);
        System.out.println("Getting Auto tab");
        AutoFragment auto = (AutoFragment) tabs.get(1);
        System.out.println("Getting Tele tab");
        TeleopFragment tele = (TeleopFragment) tabs.get(2);
        System.out.println("Getting Info tab");
        InfoFragment info = (InfoFragment) tabs.get(3);
        System.out.println("Getting match data");
        String[] matchData = match.getData();
        System.out.println("Getting auto data");
        String[] autoData = auto.getData();
        System.out.println("Getting tele data");
        String[] teleData = tele.getData();
        System.out.println("Getting info data");
        String[] infoData = info.getData();
        //Concatonate data from all the various fragments
        System.out.println("Compiling data into array");
        String[] values = new String[matchData.length+autoData.length+teleData.length+infoData.length];
        for(int i = 0; i<values.length; i++){
            if(i<matchData.length){
                values[i] = matchData[i];
            }
            else if(i<matchData.length+autoData.length){
                values[i] = autoData[i-matchData.length];
            }
            else if(i<matchData.length+autoData.length+teleData.length){
                values[i] = teleData[i-(matchData.length+autoData.length)];
            }
            else{
                values[i] = infoData[i-(matchData.length+autoData.length+teleData.length)];
            }
        }
        System.out.println("Resetting tabs");

        match.clearData();
        auto.clearData();
        tele.clearData();
        info.clearData();

        System.out.println("Returning data values");
        return values;
    }

    public String[]  retrieveData() {
        System.out.println("Retrieving data");
        //read from file
        String filename = "scoutingData";
        List<String> data = new ArrayList<>();
        try{
            System.out.println("Creating input stream");
            InputStream inputStream = getBaseContext().openFileInput(filename);

            if(inputStream != null){
                System.out.println("Input stream != null");
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String line = "";
                System.out.println("About to read");
                while ((line=bufferedReader.readLine())!=null){
                    System.out.println("Adding data: " + line);
                    data.add(line);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("Returning data array");
        String[] ret = new String[data.size()];
        for(int i =0; i<ret.length; i++){
            ret[i] = data.get(i);
        }
        return ret;
    }

    /**
       Called if user responds negatively to the confirmation prompt
     */
    public void doThanks() {
        Snackbar.make(findViewById(android.R.id.content), "Zach thanks you for your diligence", Snackbar.LENGTH_LONG).show();
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            return Fragment.instantiate(getBaseContext(), fragmentsA.get(position));

        }

        public List<String> fragmentsA;


        public SectionsPagerAdapter(FragmentManager fm){
            super(fm);
            fragmentsA = fragments;
        }

        @Override
        public int getCount() {
            // Show total pages.
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "MATCH";
                case 1:
                    return "AUTO";
                case 2:
                    return "TELEOP";
                case 3:
                    return "INFO";
            }
            return null;
        }
    }

    public void matchVerifyA(){
        MatchFragment match = (MatchFragment) getSupportFragmentManager().getFragments().get(0);
        String name = match.getData()[0];
        if(name.equals("")||name.equals(null)){
            MissingInformationDialogA dialogA = new MissingInformationDialogA();
            dialogA.show(getFragmentManager(), "VER_A");
        }
        else matchVerifyB(null);
    }
    public void matchVerifyB(String name){
        MatchFragment match = (MatchFragment) getSupportFragmentManager().getFragments().get(0);
        if(name!=null)match.setName(name);
        String matchNumber = match.getData()[1];
        if(matchNumber.equals("")||matchNumber.equals(null)){
            MissingInformationDialogB dialogB = new MissingInformationDialogB();
            dialogB.show(getFragmentManager(), "VER_B");
        }
        else matchVerifyC(null);
    }
    public void matchVerifyC(String matchNumber){
        MatchFragment match = (MatchFragment) getSupportFragmentManager().getFragments().get(0);
        if(matchNumber!=null)match.setMatch(matchNumber);
        String teamNumber = match.getData()[2];
        if(teamNumber.equals("")||teamNumber.equals(null)){
            MissingInformationDialogC dialogC = new MissingInformationDialogC();
            dialogC.show(getFragmentManager(), "VER_C");
        }else matchVerifyD(null);
    }
    public void matchVerifyD(String team){
        MatchFragment match = (MatchFragment) getSupportFragmentManager().getFragments().get(0);
        if(team!=null)match.setNumber(team);
        doSubmit();
    }

    public void cancelSubmit(){
        Snackbar.make(findViewById(android.R.id.content), "Submit Canceled.", Snackbar.LENGTH_LONG).show();
    }
}