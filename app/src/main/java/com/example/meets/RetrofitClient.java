package com.example.meets;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static RetrofitClient instance = null;
    private static InitMyApi initMyApi;
    //사용하고 있는 서버 BASE 주소
    private final static String baseUrl = "http://ec2-43-200-32-178.ap-northeast-2.compute.amazonaws.com:8080/";

    private RetrofitClient() {
        //로그를 보기 위한 Interceptor
//        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
//        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//        OkHttpClient client = new OkHttpClient.Builder()
//                .addInterceptor(interceptor)
//                .build();

        //retrofit 설정
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        initMyApi = retrofit.create(InitMyApi.class);

    }

    public static RetrofitClient getInstance() {
        if (instance == null) {
            instance = new RetrofitClient();
        }
        return instance;
    }

    public static InitMyApi getRetrofitAPI() {
        return initMyApi;
    }

//    RetrofitClient() {
//        //로그를 보기 위한 Interceptor
////        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
////        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
////        OkHttpClient client = new OkHttpClient.Builder()
////                .addInterceptor(interceptor)
////                .build();
//
//        //retrofit 설정
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(baseUrl)
//                .addConverterFactory(GsonConverterFactory.create())
//                //.client(client) //로그 기능 추가
//                .build();
//
//        initMyApi = retrofit.create(initMyApi.class);
//    }
}
