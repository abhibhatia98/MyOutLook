package com.example.myoutlook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class temp extends AppCompatActivity {
    TextView data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp);
        Toast.makeText(this,"Set up Done",Toast.LENGTH_LONG).show();
        data = (TextView)findViewById(R.id.txt_id);

    }

    public void load(View view) {

        SharedPreferences sharedPreferences = getSharedPreferences(getPackageName()+".my_pref_file", Context.MODE_PRIVATE);
        String userName = sharedPreferences.getString("username","N/A");
        String email = sharedPreferences.getString("email","N/A");
        String password = sharedPreferences.getString("password","N/A");
        String pin = sharedPreferences.getString("pin","N/A");
        String msg = "username :"+userName+"\n"+"Email : "+email+"\n"+"Password : "+password+"\n"+"Pin : "+pin;
        data.setText(msg);


    }
}
