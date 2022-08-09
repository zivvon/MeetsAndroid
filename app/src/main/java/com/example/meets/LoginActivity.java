package com.example.meets;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;

import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.NameValuePair;
import cz.msebera.android.httpclient.client.ClientProtocolException;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.entity.UrlEncodedFormEntity;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;
import cz.msebera.android.httpclient.message.BasicNameValuePair;

public class LoginActivity extends AppCompatActivity{

    private WebView mWebView;
    private String myUrl = "http://ec2-43-200-32-178.ap-northeast-2.compute.amazonaws.com:8080/user/login";// 접속 URL (내장HTML의 경우 왼쪽과 같이 쓰고 아니면 걍 URL)

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

                mWebView.loadUrl(myUrl);//웹뷰 실행
                //mWebView.setWebChromeClient(new WebChromeClient());//웹뷰에 크롬 사용 허용//이 부분이 없으면 크롬에서 alert가 뜨지 않음
                //mWebView.setWebViewClient(new WebViewClientClass());//새창열기 없이 웹뷰 내에서 다시 열기//페이지 이동 원활히 하기위해 사용
            }
        });

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {//뒤로가기 버튼 이벤트
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {//웹뷰에서 뒤로가기 버튼을 누르면 뒤로가짐
            mWebView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private class WebViewClientClass extends WebViewClient {//페이지 이동
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Log.d("check URL",url);
            view.loadUrl(url);
            return true;
        }
    }
//
//    @Override
//    protected void onStart()
//    {
//        super.onStart();
//        // Check for existing Google Sign In account, if the user is already signed in
//        // the GoogleSignInAccount will be non-null.
//        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
//        updateUI(account);
//    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
//        if (requestCode == RC_SIGN_IN) {
//            // The Task returned from this call is always completed, no need to attach
//            // a listener.
//            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
//            handleSignInResult(task);
//        }
//    }
//
//    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
//        try {
//            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
//
//            // Signed in successfully, show authenticated UI.
//            updateUI(account);
//        } catch (ApiException e) {
//            // The ApiException status code indicates the detailed failure reason.
//            // Please refer to the GoogleSignInStatusCodes class reference for more information.
//            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
//            updateUI(null);
//        }
//    }

//    private void updateUI(GoogleSignInAccount account) {
//        if (account != null){
//            personName = account.getDisplayName();
//            personGivenName = account.getGivenName();
//            personFamilyName = account.getFamilyName();
//            personEmail = account.getEmail();
//            personId = account.getId();
//            account.getPhotoUrl();
//
//            //postData();
//
//            Toast toast = Toast.makeText(getApplicationContext(), personName + "님 환영합니다!",Toast.LENGTH_SHORT);
//            toast.show();
//
//            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//            startActivity(intent);
//        }
//    }
//
//    private void signIn() {
//        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
//        startActivityForResult(signInIntent, RC_SIGN_IN);
//    }
//
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.googleLoginBtn:
//                signIn();
//                postData();
//                break;
//            // ...
//        }
//    }

//    private void postData() {
//        HttpClient client = new DefaultHttpClient();
//        HttpPost post = new HttpPost("http://ec2-43-200-32-178.ap-northeast-2.compute.amazonaws.com:8080/user/login");
//
//        ArrayList<NameValuePair> nameValues = new ArrayList<NameValuePair>();
//
//        try{
//            //Post로 넘길 값들
//            nameValues.add(new BasicNameValuePair("email", URLDecoder.decode("테스트용 이메일", "UTF-8")));
//            nameValues.add(new BasicNameValuePair("name", URLDecoder.decode("테스트용 이름", "UTF-8")));
//            nameValues.add(new BasicNameValuePair("profileUrl", URLDecoder.decode("테스트용 url", "UTF-8")));
//
//            //HttpPost에 넘길 값들을 set하기
//            post.setEntity(
//                    new UrlEncodedFormEntity(nameValues, "UTF-8"));
//        } catch (UnsupportedEncodingException e) {
//            Log.e("Insert Log", e.toString());
//        }
//
//        try{
//            //URL 실행
//            HttpResponse response = client.execute(post);
//            //통신값 숫자 확인하기...! 제발 200 나와라
//            Log.i("서버 상태", "response.getStatusCode:" + response.getStatusLine().getStatusCode());
//        } catch (ClientProtocolException e) {
//            e.printStackTrace();
//        } catch (MalformedURLException e) {
//            e.printStackTrace();;
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}