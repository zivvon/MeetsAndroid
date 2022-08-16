package com.example.meets.Dto;

import com.google.gson.annotations.SerializedName;

public class requestDto {
//    {
//        "email": "test13@email.com",
//            "name": "test13",
//            "profileUrl": "https://lh3.googleusercontent.com/a/AItbvmkXhjMMGAvBGq0sYXoppX80njinx9QdfN7GW7hg=s96-c"
//    }

    private String email;
    private String name;
    private String profileUrl;

    public requestDto(String email, String name, String profileUrl){
        this.email = email;
        this.name = name;
        this.profileUrl = profileUrl;
    }

    public String getEmail(){
        return email;
    }

    public String getName(){
        return name;
    }

    public String getProfileUrl(){
        return profileUrl;
    }
}
