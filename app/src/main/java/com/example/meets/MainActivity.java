package com.example.meets;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    //로그인 다이얼로그
    Dialog login_dialog;

    LinearLayout meetsView;
    LinearLayout moim;
    Button createMeets;

    Button moveHome;
    Button moveCal;
    Button moveMypg;

    ImageView imageview;
    TextView textview;

    TextView username;

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
                Intent intent = new Intent(getApplicationContext(), MakeMoimFormActivity.class);
                startActivityForResult(intent, 1234); //1234 : 결과값 주는 activity 구분 위한 사용자정의 코드
            }
        });

        imageview = (ImageView)findViewById(R.id.imageview);
        textview = (TextView)findViewById(R.id.textview);

        //로그인 다이얼로그
        login_dialog = new Dialog(MainActivity.this);
        login_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        login_dialog.setContentView(R.layout.login_dialog);

        // 버튼: 커스텀 다이얼로그 띄우기
        findViewById(R.id.login_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                //showLoginDialog();
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        //생성된 모임
        moim = findViewById(R.id.moim);

        moim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MoimActivity.class);
                startActivity(intent);
            }
        });

        //로그인 후 유저 정보 받아오기
        Intent intent = getIntent(); //전달할 데이터를 받을 Intent
        String userName = intent.getStringExtra("userName");

        username = findViewById(R.id.userName);

        if(TextUtils.isEmpty(userName)){
            username.setText("로그인");
        }else{
            username.setText(userName + "님");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1234 && resultCode == RESULT_OK){
            String myData = data.getStringExtra("string");
            textview.setText(myData);

            byte[] byteArray = data.getByteArrayExtra("image");
            Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);

            imageview.setImageBitmap(bitmap);

        }else{
            textview.setText("No Data");
        }
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

//    private void createMeets(){
//        TextView textViewNm = new TextView(getApplicationContext());
//        textViewNm.setText("새로운 모임 추가");
//        textViewNm.setTextSize(15);
//        textViewNm.setTypeface(null, Typeface.BOLD);
//        textViewNm.setId(0);
//        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
//                LinearLayout.LayoutParams.WRAP_CONTENT);
//        textViewNm.setLayoutParams(param);
//        textViewNm.setBackgroundColor(Color.rgb(255, 255, 255));
//        meetsView.addView(textViewNm);
//    }

    //로그인 다이얼로그
    public void showLoginDialog(){
        login_dialog.getWindow().setGravity(Gravity.BOTTOM);

        // Dialog 사이즈 조절 하기
        WindowManager.LayoutParams params = login_dialog.getWindow().getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        login_dialog.getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);

        login_dialog.show();

        Button closeBtn = login_dialog.findViewById(R.id.closeBtn);
        closeBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                login_dialog.dismiss();
            }
        });
    }
}