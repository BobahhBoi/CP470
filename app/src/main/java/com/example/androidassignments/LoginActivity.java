package com.example.androidassignments;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.util.Patterns;


public class LoginActivity extends AppCompatActivity {
    //CONSTANTS
    protected static final String ACTIVITY_NAME = "LoginActivity";
    private EditText emailEdit, passwordEdit;
    private SharedPreferences sharedPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Log.i(ACTIVITY_NAME, "In onCreate()");

        sharedPrefs = getSharedPreferences("myPreferences", MODE_PRIVATE);
        emailEdit = findViewById(R.id.email);
        emailEdit.setText(sharedPrefs.getString("DefaultEmail","email@domain.com"));
        passwordEdit = findViewById(R.id.password);

        //Login Button
        Button login_button = findViewById(R.id.loginButton);
        login_button.setOnClickListener(v -> login());
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
    private void login(){
        String email = emailEdit.getText().toString();
        String password = passwordEdit.getText().toString();

        //Gives toast if email is invalid
        if (!isValidEmail(email)) {
            Toast.makeText(this,R.string.invalid_email, Toast.LENGTH_LONG).show();
            return;
        }

        //Gives toast if password field is empty
        if (password.isEmpty()) {
            Toast.makeText(this,R.string.invalid_password, Toast.LENGTH_LONG).show();
            return;
        }

        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putString("email", email);
        editor.apply();

        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public static boolean isValidEmail(String email) {
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }
}