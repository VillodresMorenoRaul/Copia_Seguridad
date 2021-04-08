package com.example.seleccionarfechaencalendario;


import android.app.Activity;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.TextView;

public class MainActivity extends Activity {

    CalendarView calender;
    TextView textview;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calender = (CalendarView)findViewById(R.id.calendarView1);
        textview = (TextView)findViewById(R.id.textView1);

        calender.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month,
                                            int dayOfMonth) {
                // TODO Auto-generated method stub

                textview.setText("Date is : " + dayOfMonth +" / " + (month+1) + " / " + year);

            }
        });
    }
}