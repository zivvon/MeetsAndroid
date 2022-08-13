package com.example.meets.Dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserResponseDto {
    @SerializedName("email")
    @Expose
    public String email;

    @SerializedName("name")
    @Expose
    public String name;

    @SerializedName("profileUrl")
    @Expose
    public String profileUrl;

    @SerializedName("userNo")
    @Expose
    public Integer userNo;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    public Integer getUserNo() {
        return userNo;
    }

    public void setUserNo(Integer userNo) {
        this.userNo = userNo;
    }

    @Override
    public String toString() {
        return "UserResponseDto{" +
                "email=" + email +
                ", name='" + name + '\'' +
                ", profileUrl='" + profileUrl + '\'' +
                ", userNo='" + userNo + '\'' +
                '}';
    }
}
