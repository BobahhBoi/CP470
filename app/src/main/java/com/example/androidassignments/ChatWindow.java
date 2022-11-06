package com.example.androidassignments;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ChatWindow extends AppCompatActivity {
    protected static final String ACTIVITY_NAME = "ChatWindow";
    public static Short message;
    Button send_button = null;
    EditText chat_text = null;
    ListView list_view = null;
    ChatAdapter messageAdapter = null;
    ArrayList<String> messages = new ArrayList<String>();
    ChatDatabaseHelper db;
    SQLiteDatabase sqldb;
    static String TABLE_NAME = "Messages";

    @SuppressLint("Range")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_window);

        db = new ChatDatabaseHelper(this);
        sqldb = db.getWritableDatabase();
        Cursor cursor = sqldb.rawQuery("SELECT * from Messages", null);
        cursor.moveToFirst();

        while(!cursor.isAfterLast()){

            Log.i(ACTIVITY_NAME, "SQL_MESSAGE:" + cursor.getString(cursor.getColumnIndex(ChatDatabaseHelper.KEY_MESSAGE)));
            Log.i(ACTIVITY_NAME, "Cursor's column count=" + cursor.getColumnCount());
            messages.add(cursor.getString(cursor.getColumnIndex(ChatDatabaseHelper.KEY_MESSAGE)));
            cursor.moveToNext();

        }
        for(int i = 0;i<cursor.getColumnCount();i++){
            Log.i(ACTIVITY_NAME, "Column Name: " +  cursor.getColumnName(i));
        }

        send_button = findViewById(R.id.sendButton);
        chat_text = findViewById(R.id.editText);
        list_view = findViewById(R.id.chatView);

        //in this case, “this” is the ChatWindow, which is-A Context object
        messageAdapter =new ChatAdapter( this );
        list_view.setAdapter (messageAdapter);
    }
    public void addMessage(View view) {
        String chat_message = chat_text.getText().toString();
        ContentValues cvals = new ContentValues();
        cvals.put(ChatDatabaseHelper.KEY_MESSAGE,chat_message);
        sqldb.insert(ChatDatabaseHelper.TABLE_NAME, null, cvals);
        messages.add(chat_message);
        messageAdapter.notifyDataSetChanged();
        chat_text.setText("");
    }

    private class ChatAdapter extends ArrayAdapter<String> {
        public ChatAdapter(Context ctx) {
            super(ctx, 0);
        }

        public int getCount() {
            return messages.size();
        }

        public String getItem(int position) {
            return messages.get(position);
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = ChatWindow.this.getLayoutInflater();
            View result = null;

            if (position % 2 == 0) {
                result = inflater.inflate(R.layout.chat_row_incoming, null);
            } else {
                result = inflater.inflate(R.layout.chat_row_outgoing, null);
            }

            TextView message = (TextView) result.findViewById(R.id.message_text);
            message.setText(getItem(position));
            return result;
        }
    }
    public void onDestroy(){
        super.onDestroy();
        sqldb.close();
        Log.i(ACTIVITY_NAME, "In onDestroy()");

    }
}