package com.example.saurabhpandey.dbinternalex;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


public class MainActivity extends ActionBarActivity {
    EditText editText;

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Really Exit?")
                .setMessage("Are you sure you want to exit?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        MainActivity.super.onBackPressed();
                    }
                }).create().show(); }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.username);
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

    public void message(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();

    }


    public void saveInternalCache(View view) {

        String data = editText.getText().toString();

        File folder = getCacheDir();
        File myFile = new File(folder, "InternalCache.txt");
        writedata(myFile, data);
    }

    public void saveExternalCache(View view) {
        String data = editText.getText().toString();
        File folder = getExternalCacheDir();
        File myFile = new File(folder, "ExternalCache.txt");
        writedata(myFile, data);
    }

    public void savePrivateCache(View view) {
        String data = editText.getText().toString();
        File folder = getExternalFilesDir("Nixxmare");
        File myFile = new File(folder, "PrivateExternal.txt");
        writedata(myFile, data);
    }

    public void savePublicExternal(View view) {
        String data = editText.getText().toString();
        File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
        File myFile = new File(folder, "PublicExternal.txt");
        writedata(myFile, data);
    }

    public void nextScreen(View view) {
        Intent intent = new Intent(MainActivity.this, loadData.class);
        startActivity(intent);
    }

    private void writedata(File myFile, String data) {

        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(myFile);
            fileOutputStream.write(data.getBytes());
            message(data + " was written successfully" + myFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileOutputStream != null) {
                try {

                    fileOutputStream.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


        }
    }
}
