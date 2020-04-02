package com.example.myoutlook;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

public class VerifyCredentials extends AsyncTask {
    private Activity act;
    private String msg= "Verifying Your credentials...",email,password,userName;
    ProgressDialog p;
    private long startTime;
    public VerifyCredentials(Activity act, String userName,String email, String password) {
        this.act = act;
        this.userName = userName;
        this.email = email;
        this.password = password;
        p = new ProgressDialog(act);
        p.setMessage(msg);
        p.setIndeterminate(false);
        p.setCancelable(false);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        p.show();
    }
    @Override
    protected Object doInBackground(Object[] objects) {
        MyPasswordAuthenticator.generateOtp();
        MailSender mailSend = new MailSender(email,password,MyPasswordAuthenticator.otp);
        new Thread(mailSend).start();
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
       MailReader mailRead = new MailReader();
        new Thread(mailRead).start();
        return null;
    }

    @Override
    protected void onPostExecute(Object o) {

        act.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                SharedPreferences sharedPreferences = act.getSharedPreferences(act.getPackageName()+".my_pref_file", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("username",userName);
                editor.putString("email",email);
                editor.putString("password",password);
                editor.apply();
                startTime = System.currentTimeMillis();
                while(!MailReader.check && (System.currentTimeMillis()-startTime)/1000 <10){


                }
                if(MailReader.check){
                    MailReader.check = false;
                    Intent intent = new Intent(act,OTPActivity.class);
                    act.startActivity(intent);
                }else
                    Toast.makeText(act,"Sorry,Your credentials are not verified",Toast.LENGTH_SHORT).show();
                Log.e("verify","After loop");


            }
        });
        p.hide();

    }

    @Override
    protected void onProgressUpdate(Object[] values) {

        super.onProgressUpdate(values);
        Toast.makeText(act,".....",Toast.LENGTH_SHORT).show();


    }

}
