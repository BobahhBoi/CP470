package com.example.androidassignments;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //CONSTANTS
    protected static final String ACTIVITY_NAME = "MainActivity";
    protected static final int REQUEST_CODE = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(ACTIVITY_NAME, "In onCreate()");

        //listItemsActivity button
        Button listItems = findViewById(R.id.button);
        listItems.setOnClickListener(v -> openListItemsActivity());
    }
    protected void onResume() {
        super.onResume();
        Log.i(ACTIVITY_NAME, "In onResume()");
    }
    protected void onStart() {
        super.onStart();
        Log.i(ACTIVITY_NAME, "In onStart()");
    }
    protected void onPause() {
        super.onPause();
        Log.i(ACTIVITY_NAME, "In onPause()");
    }
    protected void onStop() {
        super.onStop();
        Log.i(ACTIVITY_NAME, "In onStop()");
    }
    protected void onDestroy() {
        super.onDestroy();
        Log.i(ACTIVITY_NAME, "In onDestroy()");
    }
    public void onClick(View v) {
        Log.i(ACTIVITY_NAME, "User clicked Start Chat");
    }
    private void openListItemsActivity(){
        Intent intent = new Intent(MainActivity.this, ListItemsActivity.class);
        startActivityForResult(intent,REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int responseCode, Intent data) {
        super.onActivityResult(requestCode, responseCode, data);

        if (requestCode == REQUEST_CODE && responseCode == Activity.RESULT_OK) {
            Log.i(ACTIVITY_NAME, "Returned to MainActivity.onActivityResult");
            String messagePassed = data.getStringExtra("Response");
            Toast.makeText(this,getString(R.string.main_response) + messagePassed, Toast.LENGTH_LONG).show();
        }
    }
    public void startChat(View view) {
        Log.i(ACTIVITY_NAME, "User clicked Start Chat");
        Intent intent = new Intent(MainActivity.this, ChatWindow.class);
        startActivityForResult(intent, REQUEST_CODE);
    }
    public void startTestToolbar(View view){

        Log.i(ACTIVITY_NAME,"User clicked Start Toolbar");

        Intent intent = new Intent(MainActivity.this, TestToolbar.class);

        startActivityForResult(intent,REQUEST_CODE);

    }

    public void startWeather(View view){

        Log.i(ACTIVITY_NAME,"User clicked Start Weather");

        Intent intent = new Intent(MainActivity.this, WeatherForecast.class);

        startActivityForResult(intent,REQUEST_CODE);

    }
}