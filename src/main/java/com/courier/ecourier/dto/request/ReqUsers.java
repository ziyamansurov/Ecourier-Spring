package com.courier.ecourier.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Data;

import java.util.Date;

@Data

public class ReqUsers {
    private String username;
    private String password;
    private ReqToken reqToken;
    @JsonProperty("id")
    private Long usersId;
    private String name;
    private String surname;
    private Date birthday;
    private String email;
    private String user_address;
    //private byte[] photo;
//    private Long   customerId;
//    private String username;
//    private String password;
//    private String name;
//    private String surname;
//    private String address;
//    private Date dob;
//    private String pin;
//    private String seria;
//    @JsonProperty(value = "token")
//    private ReqToken reqToken;
}
