package com.example.myoutlook;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = getSharedPreferences(getPackageName()+".my_pref_file", Context.MODE_PRIVATE);
        boolean firstTime = sharedPreferences.getBoolean("welcomeShow",true);
        boolean loginNeed = sharedPreferences.getBoolean("loginNeed",true);
        Log.e("welcome","First time: "+firstTime);
        Log.e("welcome","login need"+loginNeed);

        if(firstTime)
           startActivity(new Intent(this,WelcomeScreen.class));
        else if(loginNeed)
            startActivity(new Intent(this,LoginActivity.class));
        else
            startActivity(new Intent(this,PINActivity.class));



    }
}



