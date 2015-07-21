package com.example.royball.bline;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

/**
 * Created by royball on 7/19/2015.
 */
public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener{

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        //Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), this, hour, minute,
                true); //That true might not work... Original: DateFormat.is24HourFormat(getActivity()));

    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        //Do something with the time chosen by user
        TextView setStartTime = (TextView) getActivity().findViewById(R.id.blackout_creation_start_time_text);
        TextView setEndTime = (TextView) getActivity().findViewById(R.id.blackout_creation_end_time_text);

        String givenHour, givenMinute;
        if(hourOfDay < 10) {
            givenHour = "0" + hourOfDay;
        } else {
            givenHour = String.valueOf(hourOfDay);
        }

        if(minute < 10) {
            givenMinute = "0" + minute;
        } else {
            givenMinute = String.valueOf(minute);
        }

        if(setStartTime.getText().equals(" ")) {
            setStartTime.setText(givenHour + ":" + givenMinute);
        } else {
            setEndTime.setText(givenHour + ":" + givenMinute);
        }
    }
}
