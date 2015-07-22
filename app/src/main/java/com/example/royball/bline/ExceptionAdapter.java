package com.example.royball.bline;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

/**
 * Created by royball on 7/22/2015.
 */
public class ExceptionAdapter extends ArrayAdapter<String> {
    public ExceptionAdapter(Context context, String[] resource) {
        super(context, R.layout.exception_adapter_layout, resource);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater myInflator = LayoutInflater.from(getContext());

        View myView = myInflator.inflate(R.layout.exception_adapter_layout, parent, false);

        String thisString = getItem(position);

        TextView contactTextView = (TextView)myView.findViewById(R.id.contact_id_textview);
        contactTextView.setText(thisString);

        return myView;
    }
}
