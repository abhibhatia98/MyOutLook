package com.example.myoutlook;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

public class VerifyCredentials extends AsyncTask {
    private Context context;
    static String otp;
    private String email,password,userName;
    private ProgressDialog p;

    VerifyCredentials(Context context, String userName, String email, String password) {
        this.context = context;
        this.userName = userName;
        this.email = email;
        this.password = password;
        p = new ProgressDialog(context);
        String msg = "Verifying Your credentials...";
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
        MailSender mailSend = new MailSender(email,password,generateOtp());
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

        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getPackageName()+".my_pref_file", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("loginNeed",false);
        editor.putString("username",userName);
        editor.putString("email",email);
        editor.putString("password",password);
        editor.apply();
        long startTime = System.currentTimeMillis();
        while(!MailReader.check && (System.currentTimeMillis()- startTime)/1000 <10){}
        if(MailReader.check){
            MailReader.check = false;
            Intent intent = new Intent(context, PINActivity.class);
            context.startActivity(intent);
        }else
            Toast.makeText(context,"Sorry,Your credentials are not verified",Toast.LENGTH_SHORT).show();
        p.hide();

    }

    @Override
    protected void onProgressUpdate(Object[] values) {

        super.onProgressUpdate(values);
        Toast.makeText(context,".....",Toast.LENGTH_SHORT).show();


    }

    private String generateOtp() {
        int randomPin   =(int)(Math.random()*9000)+1000;
        otp = String.valueOf(randomPin);
        return otp;
    }

}
