package com.example.meets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    LinearLayout meetsView;
    Button createMeets;

    Button moveHome;
    Button moveCal;
    Button moveMypg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //하단탭 버튼 설정
        moveHome = findViewById(R.id.homeBtn);
        moveCal = findViewById(R.id.calBtn);
        moveMypg = findViewById(R.id.myBtn);

        //모임 생성
        meetsView = findViewById(R.id.meetsView);
        createMeets = findViewById(R.id.createMeets);

        createMeets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createMeets();
            }
        });
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

    private void createMeets(){
        TextView textViewNm = new TextView(getApplicationContext());
        textViewNm.setText("새로운 모임 추가");
        textViewNm.setTextSize(12);
        textViewNm.setTypeface(null, Typeface.BOLD);
        textViewNm.setId(0);
        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        textViewNm.setLayoutParams(param);
        textViewNm.setBackgroundColor(Color.rgb(255, 255, 255));
        meetsView.addView(textViewNm);
    }
}