package com.example.saurabhpandey.dbinternalex;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


public class loadData extends ActionBarActivity {
    EditText outputText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_data);
        outputText = (EditText) findViewById(R.id.savedusername);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_load_data, menu);
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

    public void loadInternalCache(View view) {

        File folder = getCacheDir();
        File myFile = new File(folder, "InternalCache.txt");
        String data = readData(myFile);


        if (data != null) {

            outputText.setText(data);
        } else {
            outputText.setText("No Data Was returned");
        }
    }


    public void loadExternalCache(View view) {

        File folder = getExternalCacheDir();
        File myFile = new File(folder, "ExternalCache.txt");
        String data = readData(myFile);


        if (data != null) {

            outputText.setText(data);
        } else {
            outputText.setText("No Data Was returned");
        }
    }


    public void loadPrivateExternal(View view) {
        File folder = getExternalFilesDir("Nixxmare");
        File myFile = new File(folder, "PrivateExternal.txt");
        String data = readData(myFile);


        if (data != null) {

            outputText.setText(data);
        } else {
            outputText.setText("No Data Was returned");
        }
    }

    public void loadPublicExternal(View view) {

        File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
        File myFile = new File(folder, "PublicExternal.txt");
        String data = readData(myFile);


        if (data != null) {

            outputText.setText(data);
        } else {
            outputText.setText("No Data Was returned");
        }
    }

    public void loadPreviousScreen(View view) {

        Intent intent = new Intent(loadData.this, MainActivity.class);
        startActivity(intent);
    }

    public String readData(File myFile) {
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(myFile);
            int read = -1;
            StringBuffer stringBuffer = new StringBuffer();
            while ((read = fileInputStream.read()) != -1) {

                stringBuffer.append((char) read);
            }
            return stringBuffer.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
    }
}