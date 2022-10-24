package com.example.androidassignments;

import android.content.DialogInterface;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

public class TestToolbar extends AppCompatActivity {

    //Static variables for snackbar
    static String message;
    static EditText input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_toolbar);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "floating button has been clicked", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override

    public boolean onCreateOptionsMenu(Menu m) {
        getMenuInflater().inflate(R.menu.toolbar_menu, m );
        return true;
    }
    public boolean onOptionsItemSelected (MenuItem item){
        int id=item.getItemId();
        FloatingActionButton fab = findViewById(R.id.fab);

        switch (id) {
            //Action one displays a snackbar with either the default message
            //or a message inputted by the user in action 3
            case (R.id.action_one):
                Log.d("Toolbar", "Option 1 selected");

                if (message==null) {
                    message = "You selected item 1";
                }

                Snackbar.make(fab, message, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                break;

            //Action two asks the user if they would want to stay on the current page
            //or return to the select menu
            case(R.id.action_two):
                Log.d("Toolbar", "Option 2 selected");
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(R.string.dialog_message2);
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        finish();

                    }
                });

                builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {


                    }
                });
                AlertDialog dialog=builder.create();
                dialog.show();



                break;

            //Action three asks the user to enter a message
            //The message will then be displayed when pressing action one
            case(R.id.action_three):
                Log.d("Toolbar", "Option 3 selected");

                final AlertDialog.Builder m_builder = new AlertDialog.Builder(this);
                LayoutInflater inflater = this.getLayoutInflater();

                final View v= inflater.inflate(R.layout.custom_layout, null);
                m_builder.setView(v)

                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {

                                input= v.findViewById(R.id.New_Message);
                                Editable value = input.getText();
                                message=value.toString();
                            }
                        })
                        .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                            }
                        });

                AlertDialog dialog_m=m_builder.create();
                dialog_m.show();

                break;
            //When the user presses options > about
            //Will display app version and author (Alex Lau)
            case (R.id.About):
                Log.d("Toolbar", "About selected");
                Toast aboutToast = Toast.makeText(TestToolbar.this ,"Version 1.0, by Alex Lau", Toast.LENGTH_LONG);
                aboutToast.show();
                break;

        }
        return true;

    }
}