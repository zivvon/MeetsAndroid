package com.example.meets;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;


import androidx.appcompat.app.AppCompatActivity;

public class PlusMoimActivity extends AppCompatActivity {

    //모임 날짜 및 시간 조율 다이얼로그
    Dialog schedule_dialog;

    Button date_fix_btn;
    Button date_arr_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plusmoim);

        Button btn_finish = (Button) findViewById(R.id.btn_finish);
        btn_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MakeMoimActivity.class);
                startActivity(intent);
            }
        });

        //모임 날짜 및 시간 조율 다이얼로그
        schedule_dialog = new Dialog(PlusMoimActivity.this);
        schedule_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        schedule_dialog.setContentView(R.layout.schedule_dialog);

        // 버튼: 커스텀 다이얼로그 띄우기
        findViewById(R.id.date_fix_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                //showTodoDialog();
                Intent intent = new Intent(getApplicationContext(), MoimScheduleActivity.class);
                startActivity(intent);
            }
        });

        // 버튼: 커스텀 다이얼로그 띄우기
        findViewById(R.id.date_arr_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                //showTodoDialog();
                Intent intent = new Intent(getApplicationContext(), MoimScheduleActivity.class);
                startActivity(intent);
            }
        });
    }

}
