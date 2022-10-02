package com.example.androidassignments;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.Toast;

public class ListItemsActivity extends AppCompatActivity {
    //CONSTANTS
    protected static final String ACTIVITY_NAME = "ListItemsActivity";
    private final static int CAPTURE_IMAGE = 1;
    private ImageButton image_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_items);
        Log.i(ACTIVITY_NAME, "In onCreate()");

        //Image button
        image_button = findViewById(R.id.id_imageButton);
        image_button.setOnClickListener(v -> openCameraApp());

        //Switch button
        Switch switchButton = findViewById(R.id.id_switch);
        switchButton.setOnCheckedChangeListener((buttonView, isChecked) -> {
            switchOnOff(isChecked);
        });

        //CheckBox
        CheckBox checkBox = findViewById(R.id.id_checkBox);
        checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            checkBoxChecked();
        });
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
    private void openCameraApp() {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAPTURE_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        //If picture was taken
        if (requestCode == CAPTURE_IMAGE) {
            //Get the picture
            if (resultCode == RESULT_OK && intent.hasExtra("data")) {
                //Store the picture
                Bitmap picture = (Bitmap) intent.getExtras().get("data");
                //If picture is not empty, replace image button
                if (picture != null) {
                    image_button.setImageBitmap(picture);
                }
            }
        }
    }

    protected void switchOnOff(boolean isChecked){
        CharSequence text;
        int duration;

        //if switch is on
        if (isChecked){
            duration = Toast.LENGTH_SHORT;
            text = "Switch is On";
        }
        //if switch is off
        else{
            duration = Toast.LENGTH_LONG;
            text = "Switch is Off";
        }
        //display toast depending if switch is on/off
        Toast toast = Toast.makeText(this ,text ,duration); //this is the ListActivity
        toast.show(); //display your message box
    }

    public void print(String text){
        Toast.makeText(this,text, Toast.LENGTH_LONG).show();
    }
    protected void checkBoxChecked(){
        AlertDialog.Builder builder = new AlertDialog.Builder(ListItemsActivity.this);
// 2. Chain together various setter methods to set the dialog characteristics
        builder.setMessage(R.string.dialog_message) //Add a dialog message to strings.xml

                .setTitle(R.string.dialog_title)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User clicked OK button
                        Intent resultIntent = new Intent(  );
                        resultIntent.putExtra("Response", "Here is my response");
                        setResult(Activity.RESULT_OK, resultIntent);
                        finish();
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                })
                .show();
    }

}