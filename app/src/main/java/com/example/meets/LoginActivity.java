package com.example.meets;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import android.view.KeyEvent;

import android.view.View;

import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.example.meets.Dto.UserResponseDto;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {
    //Data mapping
//    private RetrofitClient retrofitClient;
//    private initMyApi initMyApi;

    public Context mContext;

    private WebView mWebView;

    private String myUrl = "http://ec2-43-200-32-178.ap-northeast-2.compute.amazonaws.com:8080/user/login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_dialog);

        findViewById(R.id.googleLoginBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 웹뷰 셋팅
                mWebView = (WebView) findViewById(R.id.webView);//xml 자바코드 연결
                mWebView.getSettings().setJavaScriptEnabled(true);//자바스크립트 허용

                //구글 승인 오류 우회하기
                mWebView.getSettings().setUserAgentString("Mozilla/5.0 AppleWebKit/535.19 Chrome/56.0.0 Mobile Safari/535.19");
                mWebView.setWebChromeClient(new WebChromeClient());//웹뷰에 크롬 사용 허용
                mWebView.setWebViewClient(new WebViewClientClass());//새창열기 없이 웹뷰 내에서 다시 열기//페이지 이동 원활히 하기위해 사용
                mWebView.loadUrl(myUrl);//웹뷰 실행
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) { //뒤로가기 버튼 이벤트
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {//웹뷰에서 뒤로가기 버튼을 누르면 뒤로가짐
            mWebView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private class WebViewClientClass extends WebViewClient { //페이지 이동
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (url.endsWith("/user")) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
//                builder.setTitle("Meets 로그인 성공 알림");
//                builder.setMessage("Meets에 오신 것을 환영합니다.");
//
//                // Yes 버튼 및 이벤트 생성
//                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                        startActivity(intent);
//                        LoginActivity.this.finish();
//                    }
//                });
//                //Cancel 버튼 및 이벤트 생성
//                builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        //Pass
//                    }
//                });
//
//                AlertDialog dialog = builder.create();
//                dialog.show();

                //로그인 통신
                LoginResponse();
                return true;
            } else {
                view.loadUrl(url);
                return true;
            }
        }
    }

    //Data Mapping
    //서버 200이면 다시 프론트 화면으로 돌려놓기
    public void LoginResponse() {
        //retrufit 생성
//        retrofitClient = RetrofitClient.getInstance();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ec2-43-200-32-178.ap-northeast-2.compute.amazonaws.com:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        initMyApi initmyapi = retrofit.create(initMyApi.class);

        Call<UserResponseDto> call = initmyapi.getLoginResponse();

        call.enqueue(new Callback<UserResponseDto>() {
            @Override
            public void onResponse(Call<UserResponseDto> call, Response<UserResponseDto> response) {
                //서버 연동 성공
                //if (response.isSuccessful() && response.body() != null) {
                if (response.isSuccessful()) {
                    //자바 객체로 변환된 JSON데이터 저장
                    UserResponseDto data = response.body();

                    //받은 토근 저장
                    //String token = result.getToken();

                    //user 정보
                    String userEmail = data.getEmail();
                    String userName = data.getName();
                    String userProfileUrl = data.getProfileUrl();
                    Integer userNo = data.getUserNo();

                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                    builder.setTitle("Meets 로그인 성공 알림");
                    builder.setMessage(userName + "님! Meets에 오신 것을 환영합니다.");

                    // Yes 버튼 및 이벤트 생성
                    builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            LoginActivity.this.finish();
                        }
                    });
                    //Cancel 버튼 및 이벤트 생성
                    builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //Pass
                        }
                    });

                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                    builder.setTitle("알림")
                            .setMessage("서버 연동 실패...")
                            .setPositiveButton("확인", null)
                            .create()
                            .show();
                }
            }

            @Override
            public void onFailure(Call<UserResponseDto> call, Throwable t) {
                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                builder.setTitle("알림")
                        .setMessage("서버 통신 실패...")
                        .setPositiveButton("확인", null)
                        .create()
                        .show();
            }
        });

//        initMyApi.getLoginResponse().enqueue(new Callback<UserResponseDto>() {
//            @Override
//            public void onResponse(Call<UserResponseDto> call, Response<UserResponseDto> response) {
//                Log.d("retrofit", "Data fetch success");
//
//                //서버 연동 성공
//                if (response.isSuccessful() && response.body() != null) {
//                    //자바 객체로 변환된 JSON데이터 저장
//                    UserResponseDto data = response.body();
//
//                    //받은 토근 저장
//                    //String token = result.getToken();
//
//                    //user 정보
//                    String userEmail = data.getEmail();
//                    String userName = data.getName();
//                    String userProfileUrl = data.getProfileUrl();
//                    Integer userNo = data.getUserNo();
//
//                    Toast.makeText(LoginActivity.this, userName + "님 환영합니다.", Toast.LENGTH_LONG).show();
//
//                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
//                    builder.setTitle("로그인 성공 알림")
//                            .setMessage(userName + "님 맞으신가요?!")
//                            .setPositiveButton("확인", null)
//                            .create()
//                            .show();
//
////                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
////                    builder.setTitle("Meets 로그인 성공 알림");
////                    builder.setMessage(userName + "님! Meets에 오신 것을 환영합니다.");
////
////                    // Yes 버튼 및 이벤트 생성
////                    builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
////                        @Override
////                        public void onClick(DialogInterface dialog, int which) {
////                            Intent intent = new Intent(mContext.getApplicationContext(), MainActivity.class);
////                            intent.putExtra("userName", userName);
////                            startActivity(intent);
////                            LoginActivity.this.finish();
////                        }
////                    });
////                    //Cancel 버튼 및 이벤트 생성
////                    builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
////                        @Override
////                        public void onClick(DialogInterface dialog, int which) {
////                            //Pass
////                        }
////                    });
////
////                    AlertDialog dialog = builder.create();
////                    dialog.show();
//                }
//
//            }

//            @Override
//            public void onFailure(Call<UserResponseDto> call, Throwable t) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
//                builder.setTitle("알림")
//                        .setMessage("서버 통신 실패...")
//                        .setPositiveButton("확인", null)
//                        .create()
//                        .show();
//            }
        //});
    }
}