package com.example.myoutlook;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.hololo.tutorial.library.Step;
import com.hololo.tutorial.library.TutorialActivity;

public  class WelcomeScreen extends TutorialActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("welcome","come to welcome Screen");


        SharedPreferences sharedPreferences =getSharedPreferences(getPackageName()+".my_pref_file", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("welcomeShow",false);
        editor.apply();


        addFragment(new Step.Builder().setTitle("")
                .setContent("")
                .setBackgroundColor(Color.parseColor("#cccccccc")) // int background color
                .setDrawable(R.drawable.welcome1) // int top drawable
                .setSummary("")
                .build());

        addFragment(new Step.Builder().setTitle("")
                .setContent("")
                .setBackgroundColor(Color.parseColor("#cccccccc")) // int background color
                .setDrawable(R.drawable.welcome2) // int top drawable
                .setSummary("")
                .build());
       /* addFragment(new Step.Builder().setTitle("")
                .setContent("")
                .setBackgroundColor(Color.parseColor("#cccccccc")) // int background color
                .setDrawable(R.drawable.welcome3) // int top drawable
                .setSummary("Add Your Task")
                .build());*/
       /* addFragment(new Step.Builder().setTitle("")
                .setContent("")
                .setBackgroundColor(Color.parseColor("#cccccccc")) // int background color
                .setDrawable(R.drawable.welcome4) // int top drawable
                .setSummary("Add Your Task")
                .build());
        addFragment(new Step.Builder().setTitle("")
                .setContent("")
                .setBackgroundColor(Color.parseColor("#cccccccc")) // int background color
                .setDrawable(R.drawable.welcome5) // int top drawable
                .setSummary("Add Your Task")
                .build());
        addFragment(new Step.Builder().setTitle("")
                .setContent("")
                .setBackgroundColor(Color.parseColor("#cccccccc")) // int background color
                .setDrawable(R.drawable.welcome6) // int top drawable
                .setSummary("Add Your Task")
                .build());*/
        addFragment(new Step.Builder().setTitle("")
                .setContent("")
                .setBackgroundColor(Color.parseColor("#cccccccc")) // int background color
                .setDrawable(R.drawable.welcome7) // int top drawable
                .setSummary("Add Your Task")
                .build());


    }

    @Override
    public void currentFragmentPosition(int position) {

    }

    @Override
    public void finishTutorial() {
        startActivity(new Intent(this,MainActivity.class));



    }
}
