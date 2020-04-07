package com.example.myoutlook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

public class PINActivity extends AppCompatActivity {
    private EditText firstDigitpinEdt, secondDigitpinEdt, thirdDigitpinEdt, fourthDigitpinEdt;
    String truepin;
    int test = 1;
    int pressTimeThird = 0 , pressTimeSecond = 0;
    private int test1 = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);





        firstDigitpinEdt = (EditText)findViewById(R.id.first_id);
        secondDigitpinEdt = (EditText)findViewById(R.id.second_id);
        thirdDigitpinEdt = (EditText)findViewById(R.id.third_id);
        fourthDigitpinEdt = (EditText)findViewById(R.id.fourth_id);

        //keyboard open it self

        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        firstDigitpinEdt.requestFocus();

        pinDigitListener();

    }

    private void pinDigitListener(){
        firstDigitpinEdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (firstDigitpinEdt.getText().toString().length() == 1) {
                    secondDigitpinEdt.requestFocus();
                }
            }
        });

        secondDigitpinEdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!getEditTextData(thirdDigitpinEdt).matches("")) {
                    secondDigitpinEdt.requestFocus();
                    test1 = 0;
                }

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(test1==0) {thirdDigitpinEdt.requestFocus(); test1=1;}else {
                    if (getEditTextData(secondDigitpinEdt).length() == 1) {
                        thirdDigitpinEdt.requestFocus();
                    } else
                        secondDigitpinEdt.requestFocus();
                }
            }
        });

        secondDigitpinEdt.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(keyCode == KeyEvent.KEYCODE_DEL) {
                    if(getEditTextData(secondDigitpinEdt).length() ==1){
                        pressTimeSecond=0;

                    }else {
                        if(pressTimeSecond==0){
                            secondDigitpinEdt.requestFocus();
                            pressTimeSecond++;
                        }else {
                            firstDigitpinEdt.requestFocus();
                        }
                    }
                }
                return false;
            }
        });

        thirdDigitpinEdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Log.e("pinActivity","before");
                if (!getEditTextData(thirdDigitpinEdt).matches("")) {
                    thirdDigitpinEdt.requestFocus();
                    test = 0;

                }
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Log.e("pinActivity","on");

            }

            @Override
            public void afterTextChanged(Editable editable) {
                Log.e("pinActivity","After");

                if(test==0) {thirdDigitpinEdt.requestFocus(); test=1;}else {

                    if (getEditTextData(thirdDigitpinEdt).length() == 1) {
                        fourthDigitpinEdt.requestFocus();
                    } else
                        secondDigitpinEdt.requestFocus();
                }
            }
        });



       thirdDigitpinEdt.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                //You can identify which key pressed buy checking keyCode value with KeyEvent.KEYCODE_
                if(keyCode == KeyEvent.KEYCODE_DEL) {
                    if(getEditTextData(thirdDigitpinEdt).length() ==1)
                        pressTimeThird=0;
                    else {
                        if(pressTimeThird==0){
                            thirdDigitpinEdt.requestFocus();
                            pressTimeThird++;
                            //secondDigitpinEdt.getText().clear();
                        }else {
                            secondDigitpinEdt.requestFocus();

                        }

                    }
                }
                return false;
            }
        });

        fourthDigitpinEdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (thirdDigitpinEdt.getText().toString().length() ==0 ) {
                    thirdDigitpinEdt.requestFocus();
                }
                String enterPIN = getEditTextData(firstDigitpinEdt)+getEditTextData(secondDigitpinEdt)+
                        getEditTextData(thirdDigitpinEdt)+getEditTextData(fourthDigitpinEdt);

                SharedPreferences sharedPreferences = getSharedPreferences(getPackageName()+".my_pref_file", Context.MODE_PRIVATE);
                String pin = sharedPreferences.getString("pin","N/A");

                if(enterPIN.length()==4 && pin.equals("N/A")) {
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("pin",enterPIN);
                        editor.apply();
                        Intent intent = new Intent(PINActivity.this,DSRActivity.class);
                        startActivity(intent);
                }else if(enterPIN.length()==4 && enterPIN.equals(pin)){
                    Intent intent = new Intent(PINActivity.this,DSRActivity.class);
                    startActivity(intent);
                }else
                    Toast.makeText(PINActivity.this,"Please Enter complete pin",Toast.LENGTH_SHORT).show();
                }
            });

        fourthDigitpinEdt.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                //You can identify which key pressed buy checking keyCode value with KeyEvent.KEYCODE_
                if(keyCode == KeyEvent.KEYCODE_DEL) {
                    thirdDigitpinEdt.requestFocus();
                }
                return false;
            }
        });






    }


    private String getEditTextData(EditText editText) {
        return editText.getText().toString();
    }





}
