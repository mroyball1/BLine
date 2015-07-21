package com.example.royball.bline;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    private int MAX_BLACKOUTS = 6;
    private Blackout[] blackouts = new Blackout[MAX_BLACKOUTS];
    private AudioManager myAudioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        //Begin test code for list view//
        String[] listTest = {"First Item", "Second Item", "Third Item"};  //First make an array
        ListAdapter theAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listTest);
        //A ListAdapter takes an array and converts it into a form that a list view can accept
        //It's arguments are the context, the layout of the item, and the array to be displayed
        ListView theListView = (ListView) findViewById(R.id.jobList);
        theListView.setAdapter(theAdapter);

        //catch clicks
        theListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedText = "You selected " + String.valueOf(parent.getItemAtPosition(position));
                Toast.makeText(MainActivity.this, selectedText, Toast.LENGTH_SHORT).show();
            }
        }); */
        /*
        Use this as a template! There's a lot here that I can use later
         */
        //End test code for list view//

        for (int i = 0; i < MAX_BLACKOUTS; i++) {
            blackouts[i] = new Blackout();
        }

        ListAdapter theAdapter = new BlackoutAdapter(this, blackouts);

        ListView theListView = (ListView) findViewById(R.id.jobList);
        theListView.setAdapter(theAdapter);

        myAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

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
        } else if (id == R.id.menu_option_exit) {
            //HERE IS WHERE THE LOGIC FOR THE MENU ITEMS GOES
            //for example close the app (based on the else if above)
            finish();
            return true;

        }

        return super.onOptionsItemSelected(item);
    }

    public void toBlackoutCreation(View view) {

        Intent getTitleScreenIntent = new Intent(this, BlackoutBuilder.class);

        final int result = 1;

        startActivityForResult(getTitleScreenIntent, result);

    }

    public void toExceptionList(View view) {

        Intent getTitleScreenIntent = new Intent(this, ExceptionList.class);

        final int result = 1;

        startActivity(getTitleScreenIntent);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        ListView listOfBlackouts = (ListView) findViewById(R.id.jobList);

        String returnedName = data.getStringExtra("userTitle");
        String returnedStartTime = data.getStringExtra("userStartTime");
        String returnedEndTime = data.getStringExtra("userEndTime");
        //Format of userStartTime: givenHour + ":" + givenMinute
        //Break down that string
        String startHours = returnedStartTime.substring(0, 2);
        String startMinutes = returnedStartTime.substring(4, 5);
        String endHours = returnedEndTime.substring(0, 2);
        String endMinutes = returnedEndTime.substring(4, 5);

        for (int i = 0; i < blackouts.length; i++) {
            if (blackouts[i].getStartHour() == 99) {
                blackouts[i].setTitle(returnedName);
                blackouts[i].setStartHour(Integer.parseInt(startHours));
                blackouts[i].setStartMinute(Integer.parseInt(startMinutes));
                blackouts[i].setEndHour(Integer.parseInt(endHours));
                blackouts[i].setEndMinute(Integer.parseInt(endMinutes));
                break;
            }
        }

        Blackout.checkInBlackout(blackouts, myAudioManager); //Check to see if system is currently in a blackout

        listOfBlackouts.invalidateViews(); //Update view

    }
}
