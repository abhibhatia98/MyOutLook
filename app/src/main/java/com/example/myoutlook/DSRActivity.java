package com.example.myoutlook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Calendar;

public class DSRActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{
    Toolbar toolbar;
    Button dateBtn1,taskBtn1;
    Button dateBtn2,taskBtn2;
    TextView todayTxt,nextdayTxt;
    String currentDateString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dsr);
        initUI();
        setUpToolbar();

        dateBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");
                todayTxt.setText(currentDateString);
            }
        });
        dateBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");
                nextdayTxt.setText(currentDateString);
            }

        });

    }

    private void initUI() {
        View myLayout1 = findViewById( R.id.id1);
        View myLayout2 = findViewById( R.id.id2);

        dateBtn1 = myLayout1.findViewById(R.id.btn_date);
        taskBtn1 = myLayout1.findViewById(R.id.btn_add_task);
        todayTxt = myLayout1.findViewById(R.id.data_today_id);
        dateBtn1.setBackgroundResource(R.drawable.ic_calender);
        taskBtn1.setBackgroundResource(R.drawable.ic_add);


        dateBtn2 = myLayout2.findViewById(R.id.btn_date);
        taskBtn2 = myLayout2.findViewById(R.id.btn_add_task);
        nextdayTxt = myLayout2.findViewById(R.id.data_today_id);
        dateBtn2.setBackgroundResource(R.drawable.ic_calender);
        taskBtn2.setBackgroundResource(R.drawable.ic_add);
    }

    private void setUpToolbar(){
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("ADD DSR");
       // toolbar.inflateMenu(R.menu.menu_main);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());

    }
}
