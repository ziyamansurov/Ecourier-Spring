package com.courier.ecourier.dto.request;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class ReqCourier {
    private Long id;

    private String name;
    private String surname;
    private String address;

    private byte[] image;

    private String birthday;

    private int rating;

    private String phonenumb;

    private String email;
}
