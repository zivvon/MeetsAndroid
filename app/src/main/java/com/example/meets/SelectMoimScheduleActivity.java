package com.example.meets;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.content.ContextCompat;

public class SelectMoimScheduleActivity extends AppCompatActivity {
    SwitchCompat switchOnOff;
    TextView tvSwitchYes;
    TextView tvSwitchNo;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_moim_schedule);

        switchOnOff = findViewById(R.id.switchOnOff);
        tvSwitchYes = findViewById(R.id.tvSwitchYes);
        tvSwitchNo = findViewById(R.id.tvSwitchNo);

        switchOnOff.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                int white = ContextCompat.getColor(getApplicationContext(), R.color.white);
                int black = ContextCompat.getColor(getApplicationContext(), R.color.black);

                if(isChecked){
                    tvSwitchYes.setTextColor(black);
                    tvSwitchNo.setTextColor(white);
                }else{
                    tvSwitchYes.setTextColor(white);
                    tvSwitchNo.setTextColor(black);
                }
            }
        });

    }
}
