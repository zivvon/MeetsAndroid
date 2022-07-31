package com.example.meets;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MypgActivity extends AppCompatActivity {

    Button moveHome;
    Button moveCal;
    Button moveMypg;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypg);

        //하단탭 버튼 설정
        moveHome = findViewById(R.id.homeBtn);
        moveCal = findViewById(R.id.calBtn);
        moveMypg = findViewById(R.id.myBtn);
    }

    public void moveHome(View v){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    public void moveCal(View v){
        Intent intent = new Intent(getApplicationContext(), CalendarActivity.class);
        startActivity(intent);
    }

    public void moveMypg(View v){
        Intent intent = new Intent(getApplicationContext(), MypgActivity.class);
        startActivity(intent);
    }
}
