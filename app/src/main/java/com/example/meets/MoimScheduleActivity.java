package com.example.meets;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.content.ContextCompat;

public class MoimScheduleActivity extends AppCompatActivity {

    SwitchCompat switchOnOff;
    TextView tvSwitchYes;
    TextView tvSwitchNo;

    CalendarView calendarView;
    TextView textview;

    EditText startTimeTv;
    EditText endTimeTv;

    Button closeBtn;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedule_dialog);

        switchOnOff = findViewById(R.id.switchOnOff);
        tvSwitchYes = findViewById(R.id.tvSwitchYes);
        tvSwitchNo = findViewById(R.id.tvSwitchNo);

        switchOnOff.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                int white = ContextCompat.getColor(getApplicationContext(), R.color.white);
                int black = ContextCompat.getColor(getApplicationContext(), R.color.black);

                if (isChecked) {
                    tvSwitchYes.setTextColor(black);
                    tvSwitchNo.setTextColor(white);
                } else {
                    tvSwitchYes.setTextColor(white);
                    tvSwitchNo.setTextColor(black);
                }
            }
        });

        calendarView = (CalendarView) findViewById(R.id.calendarView);
        textview = findViewById(R.id.textview);

        calendarView.setMinDate(System.currentTimeMillis());
        calendarView.setMaxDate(System.currentTimeMillis() + (24 * 60 * 60 * 1000) * 14);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int dayOfMonth) {
                // todo
                month += 1;
                textview.setText(String.format("%d년 %d월 %d일", year, month, dayOfMonth));
            }
        });

        endTimeTv = findViewById(R.id.endTimeTv);
        startTimeTv = findViewById(R.id.startTimeTv);

        int initHour = 0;
        int initMin = 0;

        startTimeTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog dialog = new TimePickerDialog(MoimScheduleActivity.this, android.R.style.Theme_Holo_Light_Dialog_NoActionBar, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                        startTimeTv.setText(hourOfDay + "시 " + minute + "분 ");
                    }
                }, initHour, initMin, false);
                dialog.setTitle("시작시간");
                dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                dialog.show();
            }
        });

        endTimeTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog dialog = new TimePickerDialog(MoimScheduleActivity.this, android.R.style.Theme_Holo_Light_Dialog_NoActionBar, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                        endTimeTv.setText(hourOfDay + "시 " + minute + "분 ");
                    }
                }, initHour, initMin, false);
                dialog.setTitle("종료시간");
                dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                dialog.show();
            }
        });

        //페이지 닫기
        closeBtn = findViewById(R.id.closeBtn);
        closeBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), PlusMoimActivity.class);
                startActivity(intent);
            }
        });
    }

}
