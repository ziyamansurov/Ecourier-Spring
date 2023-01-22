package com.courier.ecourier.dto.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RespUser {
    private Long userId;
    private String username;
    private String fullName;
    private String token;
}
