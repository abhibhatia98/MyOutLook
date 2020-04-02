package com.example.myoutlook;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.os.SystemClock;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Objects;

import javax.mail.internet.InternetAddress;

public class MainActivity extends Activity {
    public EditText userNameEdt,emailEdt;
    private EditText passwordEdt;
    private EditText firstDigitPinEdt, secondDigitPinEdt, thirdDigitPinEdt, fourthDigitPinEdt;
    Button loginBtn;
    MyPasswordAuthenticator pa;
    LinearLayout loginPage;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!getEditTextData(emailEdt).equals("") && !getEditTextData(passwordEdt).equals("")){

                    VerifyCredentials verifyCredentials =new VerifyCredentials(MainActivity.this,getEditTextData(userNameEdt),getEditTextData(emailEdt),getEditTextData(passwordEdt));
                    verifyCredentials.execute();
                }
                else
                    Toast.makeText(MainActivity.this,"Please Enter your Login id and Password",Toast.LENGTH_SHORT).show();
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



