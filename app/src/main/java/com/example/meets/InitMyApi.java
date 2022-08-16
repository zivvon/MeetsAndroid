package com.example.meets;

import com.example.meets.Dto.UserResponseDto;
import com.example.meets.Dto.requestDto;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface InitMyApi {
    //@통신 방식("통신 API명")
    @GET("user")
    Call<UserResponseDto> getLoginResponse();

    @POST("user/login")
    Call<requestDto> postUserInfo(@Body requestDto requestDto);
}
