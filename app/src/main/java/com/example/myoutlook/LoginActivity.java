package com.example.myoutlook;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private EditText userNameEdt,emailEdt;
    private EditText passwordEdt;
    Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("welcome","come to login Activity");

        setContentView(R.layout.activity_login);
        initUI();

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!getEditTextData(emailEdt).equals("") && !getEditTextData(passwordEdt).equals("") && !getEditTextData(userNameEdt).equals("")){

                    VerifyCredentials verifyCredentials =new VerifyCredentials(LoginActivity.this,getEditTextData(userNameEdt),getEditTextData(emailEdt),getEditTextData(passwordEdt));
                    verifyCredentials.execute();
                }
                else
                    Toast.makeText(LoginActivity.this,"Please Enter your Login id and Password",Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void initUI() {
        userNameEdt = (EditText)findViewById(R.id.user_name_id);
        emailEdt = (EditText)findViewById(R.id.email_id);
        passwordEdt = (EditText) findViewById(R.id.password_id);
        loginBtn = (Button)findViewById(R.id.loginbtn_id);


        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        userNameEdt.requestFocus();
    }

    private String getEditTextData(EditText editText) {

       /*
       This function reads the EditText data and  return  data in string format

       input:EditText
       output:String Data
       */

        String temp = "";
        return (temp+editText.getText().toString().trim());
    }
}
