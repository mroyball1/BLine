package com.example.royball.bline;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.support.v4.app.FragmentActivity;
import android.widget.TextView;

/**
 * Created by royball on 7/19/2015.
 */
public class BlackoutBuilder extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.blackout_creation_layout);
    }

    public void toMainActivity(View view) {

        EditText userTitleET = (EditText) findViewById(R.id.user_entered_title);
        TextView userStartTimeTX = (TextView) findViewById(R.id.blackout_creation_start_time_text);
        TextView userEndTimeTX = (TextView) findViewById(R.id.blackout_creation_end_time_text);

        String userTitle = String.valueOf(userTitleET.getText());
        String userStartTime = String.valueOf(userStartTimeTX.getText());
        String userEndTime = String.valueOf(userEndTimeTX.getText());

        Intent goingBack = new Intent();

        goingBack.putExtra("userTitle", userTitle);
        goingBack.putExtra("userStartTime", userStartTime);
        goingBack.putExtra("userEndTime", userEndTime);

        setResult(RESULT_OK, goingBack); //This is what actually sends it back

        finish();

    }

    public void showTimePickerDialog(View view) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getFragmentManager(), "timePicker");
    }
}
