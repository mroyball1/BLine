package com.example.royball.bline;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by royball on 7/21/2015.
 */
public class ExceptionList extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.exception_creation_layout);

        //Build array of contacts
        //Real code for getting contacts:

        Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null,null,null, null);
        String[] listTest = new String[phones.getCount()];
        for (int i = 0; i < phones.getCount(); i++) {
            phones.moveToNext();
            listTest[i] = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
        }

        //phones.close();

        ListAdapter theAdapter = new ExceptionAdapter(this, listTest);
        ListView theListView = (ListView) findViewById(R.id.exception_list);
        theListView.setAdapter(theAdapter);

    }

    public void toMainActivity(View view) {

        Intent goingBack = new Intent();

        setResult(RESULT_OK, goingBack); //This is what actually sends it back

        finish();

    }

}
