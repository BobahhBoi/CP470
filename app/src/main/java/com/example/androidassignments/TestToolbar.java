package com.example.androidassignments;

import android.content.Context;
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
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.androidassignments.databinding.ActivityTestToolbarBinding;

public class TestToolbar extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityTestToolbarBinding binding;

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
            case (R.id.action_one):
                Log.d("Toolbar", "Option 1 selected");

                if (message==null) {
                    message = "You selected item 1";
                }

                Snackbar.make(fab, message, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                break;

            case(R.id.action_two):
                Log.d("Toolbar", "Option 2 selected");
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(R.string.dialog_title2);
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

            case (R.id.About):
                Log.d("Toolbar", "About selected");
                Toast toast = Toast.makeText(TestToolbar.this ,"Version 1.0, by Alex Lau", Toast.LENGTH_LONG);
                toast.show();
                break;

        }
        return true;

    }
}