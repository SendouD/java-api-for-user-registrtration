package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class otppassword {
    private int id;
    private String otp;
    private long sessiontime;

    public otppassword(@JsonProperty("id") int id, @JsonProperty("otp")  String otp,  @JsonProperty("sessiontime")  long sessiontime) {
        this.id = id;
        this.otp = otp;
        this.sessiontime = sessiontime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public long getSessiontime() {
        return sessiontime;
    }

    public void setSessiontime(long sessiontime) {
        this.sessiontime = sessiontime;
    }
}
