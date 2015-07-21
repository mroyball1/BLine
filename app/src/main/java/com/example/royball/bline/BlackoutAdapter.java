package com.example.royball.bline;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by royball on 7/19/2015.
 */
public class BlackoutAdapter extends ArrayAdapter<Blackout> {
    public BlackoutAdapter(Context context, Blackout[] resource) {
        super(context, R.layout.blackout_adapter_layout, resource);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater myInflator = LayoutInflater.from(getContext());

        View myView = myInflator.inflate(R.layout.blackout_adapter_layout, parent, false);

        Blackout blackout = getItem(position);

        TextView titleTextView = (TextView) myView.findViewById(R.id.blackout_title_view);
        if (blackout.getStartHour() == 99) {
            titleTextView.setText("");
        } else {
            titleTextView.setText(blackout.getTitle());
        }

        TextView timeTextView = (TextView) myView.findViewById(R.id.blackout_start_view);

        if (blackout.getStartHour() == 99) {
            timeTextView.setText("");
        } else {
            timeTextView.setText("Start Time: " + blackout.getStartHourString() + ":" + blackout.getStartMinuteString()
                    + "         End Time: " + blackout.getEndHourString() + ":" + blackout.getEndMinuteString());
        }

        return myView;
    }
}
