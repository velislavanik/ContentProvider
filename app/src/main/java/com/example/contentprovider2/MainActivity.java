package com.example.contentprovider2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickAddItem(View view) {
    // Adding item
        ContentValues values = new ContentValues();
        values.put(ContentProvider.NAME,
                ((EditText)findViewById(R.id.edit1)).getText().toString());

        values.put(ContentProvider.GRADE,
                ((EditText)findViewById(R.id.edit2)).getText().toString());

        Uri uri = getContentResolver().insert(
                ContentProvider.CONTENT_URI, values);

        Toast.makeText(getBaseContext(),
                uri.toString(), Toast.LENGTH_LONG).show();
        ((EditText) findViewById(R.id.edit1)).setText("");
        ((EditText) findViewById(R.id.edit2)).setText("");
    }

    public void onClickDeleteItem(View view) {
        // Deleting item
        Uri contentUri = Uri.parse("content://com.example.contentProvider2.ContentProvider");



    }

    public void onClickSearchForItem(View view) {
        // Searching for item
    }
    @SuppressLint("Range")
    public void onClickRetrieveAll(View view) {
        // Retrieve student records
      //  String URL = "content://com.example.contentprovider2.ContentProvider";
        Uri contentUri = Uri.parse("content://com.example.contentProvider2.ContentProvider");

        Cursor cursor = getContentResolver().query(contentUri , null, null, null, "name");
        if(cursor!=null) {
            cursor.moveToFirst();
            // Loop in the cursor to get each row.
            do {
                // Get column 1 value.
                int column1Index = cursor.getColumnIndex("column1");
                String column1Value = cursor.getString(column1Index);
                // Get column 2 value.
                int column2Index = cursor.getColumnIndex("column2");
                String column2Value = cursor.getString(column2Index);
                Toast.makeText(this,
                        cursor.getString(cursor.getColumnIndex(ContentProvider._ID)) +
                                ", " +  cursor.getString(cursor.getColumnIndex( ContentProvider.NAME)) +
                                ", " + cursor.getString(cursor.getColumnIndex( ContentProvider.GRADE)),
                        Toast.LENGTH_SHORT).show();
            } while (cursor.moveToNext());
        }
     else {
            Toast.makeText(this, "Nqma", Toast.LENGTH_LONG).show();
        }
         cursor.close();

    }
}