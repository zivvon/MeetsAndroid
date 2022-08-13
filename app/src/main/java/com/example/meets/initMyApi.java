package com.example.meets;

import com.example.meets.Dto.UserResponseDto;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface initMyApi {
    //@통신 방식("통신 API명")
    @GET("user")
    Call<UserResponseDto> getLoginResponse();
}
